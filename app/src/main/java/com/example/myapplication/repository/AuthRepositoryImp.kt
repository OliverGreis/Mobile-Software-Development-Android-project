package com.example.myapplication.repository
import android.app.Activity
import android.content.Context
import android.util.Log
import kotlinx.coroutines.suspendCancellableCoroutine
import com.example.myapplication.model.User
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.jwt.JWT
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.example.myapplication.R
import com.example.myapplication.data.remote.DeviceTokenApi
import com.example.myapplication.data.remote.KtorClientProvider
import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.resume




class AuthRepositoryImp(private val context: Context): AuthRepository {
    private val auth0 = Auth0.getInstance(
        context.getString(R.string.com_auth0_client_id),
        context.getString(R.string.auth0_domain))

    private val deviceTokenApi = DeviceTokenApi()


    override suspend fun login(activity: Activity): User = suspendCancellableCoroutine { cont ->
        WebAuthProvider.login(auth0)
            .withScheme(context.getString(R.string.auth0_scheme))
            .withScope("openid profile email")
            .start(activity, object : Callback<Credentials, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                    cont.resumeWithException(error)
                }

                override fun onSuccess(result: Credentials) {
                    val idToken = result.idToken
                    val user = parseUserFromIdToken(idToken)

                    // TODO: PLACE UNTIL PROPER SIGNUP HAS BEEN CREATED
                    CoroutineScope(Dispatchers.IO).launch {
                        createuser(
                            userName = user.email,
                            mail = user.email,
                            userId = user.id,
                            password = "1234" // Or some other placeholder, since password is not available
                        )
                    }


                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val fcmToken = FirebaseMessaging.getInstance().token.await()
                            deviceTokenApi.testConnection()
                            deviceTokenApi.registerDeviceToken(
                                userId = user.id,
                                token = fcmToken
                            )
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                    }

                    cont.resume(user)
                }
            })


    }

    override suspend fun logout(activity: Activity) {
        WebAuthProvider.logout(auth0)
            .withScheme(context.getString(R.string.auth0_scheme))
            .start(activity, object: Callback<Void?, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                    throw error
                }

                override fun onSuccess(result: Void?) {
                }
            })

    }

    private fun parseUserFromIdToken(idToken: String): User {
        val jwt = JWT(idToken)
        return User(
            id = jwt.subject ?: "",
            name = jwt.getClaim("name").asString() ?: "",
            email = jwt.getClaim("email").asString() ?: ""
        )

    }




    // :TODO This is a hack solution, for demonstration purposes only
    private val client = KtorClientProvider.client
    private val baseUrl = KtorClientProvider.baseURL
    suspend fun createuser(userName: String, mail: String, userId: String, password: String) {
        val trimmedUserId = userId.substringAfter("|")
        try{
            val response: HttpResponse = client.post("$baseUrl/api/users/create/$userName/$mail/$password")
            Log.w("FCM", "registerDeviceToken success: $response")
            client.put("$baseUrl/api/user/setuserid/$userName/$trimmedUserId")
        }catch (e: Exception){
            Log.e("FCM", "Error calling api/user/create", e)
        }
    }

}