package com.example.myapplication.screens
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.myapplication.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import com.example.myapplication.R.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.compose.AsyncImage
import com.example.myapplication.api.GroupApiService
import com.example.myapplication.model.Group

@Composable
fun GroupPage(navController: NavHostController, group: Group, modifier: Modifier = Modifier) {

    Column {
        Text(
            text = group.name,
            modifier = modifier.fillMaxWidth(),
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(roboto_condensed_bold)),
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(440.dp)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(android.graphics.Color.parseColor("#104210"))),
        ){
            if(group.groupImage != null){
                AsyncImage(
                    model = group.groupImage,
                    contentDescription = "GroupImage",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Fit
                )
            }else{
                Text(
                    text = group.name,
                    color = Color.White,
                    modifier = modifier.align(Alignment.Center),
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(roboto_condensed_bold)),
                    textAlign = TextAlign.Center

                )
            }

        }
        Text(
            text = "Group members",
            modifier = modifier,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(roboto_condensed_regular))
        )
        LazyRow(  contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            items(group.memberIDs.size){  i ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color(0xFF8BC34A), RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = group.memberIDs.get(i),
                            contentDescription = "Member icon",
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

            }
    }
        }
            TextButton(
                onClick = { }, modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "Add Member",
                    modifier = modifier,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(roboto_condensed_regular))
                )            }

        Box(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(android.graphics.Color.parseColor("#88C25F"))),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 25.dp),
            ) {
                Text(
                    text = "Total Ammount",
                    modifier = modifier,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(roboto_condensed_regular)),
                    color = Color.White,

                )
                Text(
                    text = "400 Kr",
                    modifier = modifier.padding(horizontal = 300.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(roboto_condensed_regular)),
                    color = Color.White,


                    )
            }
            Spacer(Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(7) { index ->
                    ActivityItem()
                }
            }
        }
        Button(onClick = {navController.navigate("transaction")},modifier = Modifier.padding(horizontal = 135.dp).padding(vertical = 25.dp),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#88C25F"))
        )) {
            Text("Make Request")
        }

    }

}

@Composable
fun ActivityItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#104210")),
        ),
        elevation = CardDefaults.cardElevation(6.dp)
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

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("Group member requested", color = Color.White)
                Text("200 kr", color = Color.White)
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text("You owe", color = Color.White)
                Text("40 kr", color = Color.White)

                Spacer(Modifier.height(6.dp))

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                    )
                ) {
                    Text("Pay")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GroupScreen(
    navController: NavHostController,
    api: GroupApiService,
    refreshTrigger: Boolean,
    id: Int
) {
    NotificationPermissionRequester()

    var group by remember { mutableStateOf<Group?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }



    LaunchedEffect(id) {
        try {
            group = api.getGroup(id)
            println("group: $group")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    group?.let { safeGroup ->
        GroupPage(
            navController = navController,
            group = safeGroup
        )
    }
}




