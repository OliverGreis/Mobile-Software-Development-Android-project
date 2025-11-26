package com.example.myapplication.screens
import android.R.id.title
import android.database.DatabaseErrorHandler
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.intFloatMapOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.LightGreen
import com.example.myapplication.ui.theme.DarkGreen
import com.example.myapplication.ui.theme.White
import com.example.myapplication.R
import com.example.myapplication.R.font.roboto_condensed_regular

@Composable
fun ProfilePage()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        userInfo()
        Spacer(modifier = Modifier.height(32.dp))
        CardSection()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun userInfo()
{
    Image(
        painter = painterResource(id = R.drawable.profile_picture),
        contentDescription = "Profile picture",
        modifier = Modifier.size(150.dp)
    )

    Text(
        text = "User name",
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(roboto_condensed_regular)),
        fontWeight = FontWeight.Normal,
        color = Color.Black
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    )
    {
        Column(horizontalAlignment = Alignment.Start)
        {
            Text(
                text = "Email",
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(LightGreen)
                    .width(160.dp)
                    .height(33.dp)
            )
            {
                Text(
                    text = "UserName@Email.com", //should be data from the database
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(roboto_condensed_regular)),
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        Column(horizontalAlignment = Alignment.Start)
        {
            Text(
                text = "Phone number",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(roboto_condensed_regular)),
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(LightGreen)
                    .width(120.dp)
                    .height(33.dp)
            )
            {
                Text(
                    text = "+45 12 34 56 78", // should be data from database
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(roboto_condensed_regular)),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))

    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = DarkGreen),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.width(160.dp)
    )
    {
        Text(
            "Edit profile",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(roboto_condensed_regular)),
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun CardSection()
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        Text(
            text = "Cards",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(roboto_condensed_regular)),
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = LightGreen)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Image(
                        painter = painterResource(id = R.drawable.card_icon),
                        contentDescription = "Card icon",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column()
                    {
                        Text(
                            text = "Visa/Debut card",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(roboto_condensed_regular)),
                            fontWeight = FontWeight.Normal,
                            color = White
                        )
                        Row()
                        {
                            Text(
                                text = "****1234", // should be data from database
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(roboto_condensed_regular)),
                                fontWeight = FontWeight.Normal,
                                color = White
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "expires" +"01/23", //second part should be data from database
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(roboto_condensed_regular)),
                                fontWeight = FontWeight.Normal,
                                color = White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = DarkGreen),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .width(100.dp)
                        .height(35.dp)
                )
                {
                    Text(
                        "Delete",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(roboto_condensed_regular)),
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = LightGreen),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(35.dp)
        ) {
            Text(
                "Add Card",
                color = White,
                fontFamily = FontFamily(Font(roboto_condensed_regular)),
                fontSize = 12.sp
            )
        }
    }
}

