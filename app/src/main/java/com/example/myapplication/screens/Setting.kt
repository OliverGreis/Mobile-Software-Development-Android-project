package com.example.myapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R


data class SettingUiState(
    val notificationsEnabled: Boolean = true,
    val groupNotificationsEnabled: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    state: SettingUiState,
    onBack: () -> Unit,
    onToggleNotifications: (Boolean) -> Unit,
    onToggleGroupNotifications: (Boolean) -> Unit,
) {
    Column(Modifier.padding(start = 24.dp, end = 24.dp)) {
        //This is not going to be implemented
        Text("Language", color = colorResource(id = R.color.black),
            fontSize = 17.sp, style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold))

        // Not implemented but needed to follow hi-fi
        SwitchWithLabel(
            label = "Dark Mode",
            state = false,
            onStateChange = {},
            fontWeight = FontWeight.SemiBold
        )

        // General Notifications
        // Master Switch
        SwitchWithLabel(
            label = "Enable Notifications",
            state = state.notificationsEnabled,
            onStateChange = onToggleNotifications,
            fontWeight = FontWeight.SemiBold)

        // Group Notifications
        AnimatedVisibility(visible = state.notificationsEnabled) {
            Column(
                modifier = Modifier.padding(start = 24.dp)
            ) {
                SwitchWithLabel(
                    label = "Group Notifications",
                    state = state.groupNotificationsEnabled,
                    onStateChange = onToggleGroupNotifications,
                    fontWeight = FontWeight.Normal
                )
            }
        }






    }
}


@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen(
        state = SettingUiState(
            notificationsEnabled = true,
            groupNotificationsEnabled = true
        ),
        onBack = {},
        onToggleNotifications = {},
        onToggleGroupNotifications = {}
    )
}

@Composable
fun SwitchWithLabel(label: String, state: Boolean, onStateChange: (Boolean) -> Unit, fontWeight: FontWeight) {
    val interactionSource = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    role = Role.Switch,
                    onClick = {
                        onStateChange(!state)
                    }
                )
                .padding(horizontal = 0.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
        Text(label, modifier = Modifier.weight(1f), color = colorResource(id = R.color.black),
            fontSize = 17.sp, style = TextStyle(fontFamily = FontFamily.Default, fontWeight = fontWeight))
        Switch(
            checked = state,
            onCheckedChange = { onStateChange(it) }, colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFFFFFFF),
                checkedTrackColor = Color(0xFF104210),
                uncheckedThumbColor = Color(0xFFFFFFFF),
                uncheckedTrackColor = Color(0xFF88C25F),

                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent

            )
        )
    }
}
