    package com.example.myapplication.screens

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.R.font.roboto_condensed_regular
import com.example.myapplication.R.font.roboto_condensed_bold
import com.example.myapplication.Controller.Group
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.myapplication.Controller.GroupApiService
import java.nio.charset.StandardCharsets
import java.net.URLEncoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

    @Composable
fun Home(modifier: Modifier = Modifier,navController: NavHostController,groups: List<Group>) {

    Column(){
    Spacer(Modifier.height(100.dp))

    Text(
        text = "Groups:",
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(roboto_condensed_bold)),
        color = Color.Black,
        modifier = Modifier.padding(horizontal = 20.dp)
        )
    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(horizontal = 20.dp)
            .width(380.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(android.graphics.Color.parseColor("#88C25F"))),
    ){
        GroupList(groups = groups) { group ->
            navController.navigate("group/${group.id}")
        }
    }

    Text(
        text = "Activity:",
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(roboto_condensed_bold)),
        color = Color.Black,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(horizontal = 20.dp)
            .width(380.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(android.graphics.Color.parseColor("#88C25F"))),
    ){
        LazyColumn(modifier = Modifier.height(300.dp)) {
            items(7) { index ->
                HomeActivityItem(onClick = { navController.navigate("activity") })
            }
        }
}
}
}


@Composable
fun HomeActivityItem(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .width(400.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#88C25F")),
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {


        Row() {
        Text("+25,00 kr", color = Color.White)

        Spacer(Modifier.width(75.dp))



            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text("From Vaccation Time", color = Color.White)
                Text("Member Name", color = Color.White)

                Spacer(Modifier.height(6.dp))

            }
        }
    }
}


@Composable
fun GroupItem(
    onClick: () -> Unit,
    groupName: String,
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .width(400.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#88C25F")),
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier.size(55.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(R.drawable.profile_picture),
                        contentDescription = null,
                        modifier = Modifier.size(55.dp)
                    )
                }
            }

            Spacer(Modifier.width(20.dp))


            Text(
                text = groupName,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(roboto_condensed_regular)),
                color = Color.White,

                )
            Spacer(Modifier.width(75.dp))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Missing",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(roboto_condensed_regular)),
                    color = Color.White,
                )
                Text(
                    text = "Payment",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(roboto_condensed_regular)),
                    color = Color.White,

                    )

                Spacer(Modifier.height(6.dp))

            }
        }
    }
}

    @Composable
    fun GroupList(groups: List<Group>, onItemClick: (Group) -> Unit) {
        LazyColumn {
            items(groups) { group ->
                GroupItem(groupName = group.name, onClick = { onItemClick(group) })
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun HomeScreen(navController: NavHostController, api: GroupApiService,refreshTrigger: Boolean ) {
        NotificationPermissionRequester()
        var groups by remember { mutableStateOf<List<Group>>(emptyList()) }


        LaunchedEffect(refreshTrigger) {
            try {
                groups = api.getGroups()
                println("Fetched groups: $groups")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        Home(navController = navController, groups = groups)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun NotificationPermissionRequester() {
            val context = LocalContext.current
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { }
            )

            LaunchedEffect(Unit) {
                val granted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PermissionChecker.PERMISSION_GRANTED

                if (!granted) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
        }
    }