package com.example.myapplication.repository
import android.app.Activity
import android.content.Context
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
import com.google.firebase.messaging.FirebaseMessaging
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
}