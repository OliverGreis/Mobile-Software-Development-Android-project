    package com.example.myapplication.screens

import android.Manifest
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.example.myapplication.model.Group
import com.example.myapplication.viewmodel.HomeViewModel

    @Composable
fun Home(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        groups: List<Group>,
        ) {

    Column(){

    Text(
        text = "Groups:",
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(roboto_condensed_bold)),
        color = Color.Black,
        modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
            Surface(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color(android.graphics.Color.parseColor("#88C25F")),
                tonalElevation = 2.dp,
                shadowElevation = 64.dp
            ) {
                GroupList(groups = groups) { group ->
                    navController.navigate("group")
                }
            }
        }
    Text(
        text = "Activity:",
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(roboto_condensed_bold)),
        color = Color.Black,
        modifier = Modifier.padding(horizontal = 20.dp),
    )
        Spacer(modifier = Modifier.height(8.dp))
    Surface(
        modifier = Modifier
            .height(300.dp)
            .padding(horizontal = 20.dp)
            .width(380.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color(android.graphics.Color.parseColor("#88C25F")),
        tonalElevation = 2.dp,
        shadowElevation = 16.dp
    ){
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(12.dp)

        ) {
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
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(6.dp),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.18f)),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#88C25F")),
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .heightIn(min = 50.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
        // Amount
        Text(
            modifier = Modifier.width(90.dp),
            text = "+25,00 kr",
            color = Color.White,
            fontWeight = FontWeight.Medium
            )
        // Description
        Text(
            modifier = Modifier.weight(1f),
            text ="New Year Party",
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
        // Date
        Text(
            text = "12.01.2023",
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
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
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(6.dp),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.18f)),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#88C25F")),
        ),


    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp)
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
                modifier = Modifier.weight(1f)
                )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color.White.copy(alpha = 0.18f)
                ) {
                    Text(
                        "Missing Payment",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(roboto_condensed_regular)),
                    )
                }

            }
        }
    }
}

    @Composable
    fun GroupList(groups: List<Group>, onItemClick: (Group) -> Unit) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(12.dp)

        ){
            items(groups) { group ->
                GroupItem(groupName = group.name, onClick = { onItemClick(group) })
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel, refreshTrigger: Boolean ) {
        NotificationPermissionRequester()
        var groups by remember { mutableStateOf<List<Group>>(emptyList()) }

        LaunchedEffect(refreshTrigger) {
            viewModel.loadGroups()
        }
        Home(navController = navController, groups = viewModel.groups)
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
//    @Preview(showBackground = true)
//    @Composable
//    fun HomeScreenPreview() {
//        val groups = listOf<Group>(
//            Group(1, "Group1", emptyList(), emptyList(), ""),
//            Group(2, "Group2", emptyList(), emptyList(), ""),
//            Group(3, "Group3", emptyList(), emptyList(), "")
//        )
//        Home(navController = NavHostController(LocalContext.current), groups = groups )
//    }