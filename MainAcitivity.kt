package com.example.gramasuvidhanexus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GramaSuvidhaApp()
        }
    }
}

@Composable
fun GramaSuvidhaApp() {

    var isKannada by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    var currentScreen by remember { mutableStateOf("login") }

    MaterialTheme {

        when (currentScreen) {

            "login" -> LoginScreen(
                isKannada = isKannada,
                onToggleLanguage = {
                    isKannada = !isKannada
                },
                userName = userName,
                onNameChange = {
                    userName = it
                },
                onContinue = {
                    currentScreen = "home"
                }
            )

            "home" -> HomeScreen(
                isKannada = isKannada,
                userName = userName
            )
        }
    }
}

@Composable
fun LoginScreen(
    isKannada: Boolean,
    onToggleLanguage: () -> Unit,
    userName: String,
    onNameChange: (String) -> Unit,
    onContinue: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF4A148C),
                        Color(0xFF7B1FA2),
                        Color(0xFFCE93D8)
                    )
                )
            )
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Grama Suvidha Nexus",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                onToggleLanguage()
            }
        ) {
            Text(
                if (isKannada)
                    "English"
                else
                    "ಕನ್ನಡ"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = {
                onNameChange(it)
            },
            label = {
                Text(
                    if (isKannada)
                        "ಹೆಸರು"
                    else
                        "Enter Name"
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

            }
        ) {
            Text(
                if (isKannada)
                    "ಚಿತ್ರ ಅಪ್‌ಲೋಡ್"
                else
                    "Upload Image"
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                onContinue()
            }
        ) {

            Text(
                if (isKannada)
                    "ಮುಂದುವರಿಸಿ"
                else
                    "Continue"
            )
        }
    }
}

@Composable
fun HomeScreen(
    isKannada: Boolean,
    userName: String
) {

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = {
                        selectedTab = 0
                    },
                    icon = {
                        Icon(Icons.Default.Home, null)
                    },
                    label = {
                        Text(
                            if (isKannada)
                                "ಮುಖಪುಟ"
                            else
                                "Home"
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                    },
                    icon = {
                        Icon(Icons.Default.Event, null)
                    },
                    label = {
                        Text(
                            if (isKannada)
                                "ಕಾರ್ಯಕ್ರಮ"
                            else
                                "Events"
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = {
                        selectedTab = 2
                    },
                    icon = {
                        Icon(Icons.Default.Call, null)
                    },
                    label = {
                        Text(
                            if (isKannada)
                                "ತುರ್ತು"
                            else
                                "Emergency"
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = {
                        selectedTab = 3
                    },
                    icon = {
                        Icon(Icons.Default.Person, null)
                    },
                    label = {
                        Text(
                            if (isKannada)
                                "ಪ್ರೊಫೈಲ್"
                            else
                                "Profile"
                        )
                    }
                )
            }
        }

    ) { padding ->

        when (selectedTab) {

            0 -> HomeContent(
                modifier = Modifier.padding(padding),
                isKannada = isKannada,
                userName = userName
            )

            1 -> EventScreen(
                modifier = Modifier.padding(padding),
                isKannada = isKannada
            )

            2 -> EmergencyScreen(
                modifier = Modifier.padding(padding),
                isKannada = isKannada
            )

            3 -> ProfileScreen(
                modifier = Modifier.padding(padding),
                isKannada = isKannada,
                userName = userName
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier,
    isKannada: Boolean,
    userName: String
) {

    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    val greeting = when {

        hour < 12 ->
            if (isKannada)
                "ಶುಭೋದಯ"
            else
                "Good Morning"

        hour < 17 ->
            if (isKannada)
                "ಶುಭ ಮಧ್ಯಾಹ್ನ"
            else
                "Good Afternoon"

        else ->
            if (isKannada)
                "ಶುಭ ಸಂಜೆ"
            else
                "Good Evening"
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        item {

            Text(
                text = "$greeting $userName",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(25.dp))

            GlassCard(
                title = if (isKannada)
                    "ಯೋಜನೆಗಳು"
                else
                    "Projects"
            )

            Spacer(modifier = Modifier.height(20.dp))

            GlassCard(
                title = if (isKannada)
                    "ಕಾರ್ಯಕ್ರಮಗಳು"
                else
                    "Events"
            )

            Spacer(modifier = Modifier.height(20.dp))

            ProjectCard(
                isKannada = isKannada,
                title = "Smart Road Development"
            )

            ProjectCard(
                isKannada = isKannada,
                title = "Water Supply Monitoring"
            )

            ProjectCard(
                isKannada = isKannada,
                title = "Solar Energy Installation"
            )
        }
    }
}

@Composable
fun GlassCard(title: String) {

    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x55FFFFFF)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProjectCard(
    isKannada: Boolean,
    title: String
) {

    val context = LocalContext.current

    var sliderPosition by remember {
        mutableStateOf(0.5f)
    }

    var issue by remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                }
            )

            Text(
                if (isKannada)
                    "ಪ್ರಗತಿ"
                else
                    "Progress"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row {

                repeat(5) {

                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = issue,
                onValueChange = {
                    issue = it
                },
                label = {
                    Text(
                        if (isKannada)
                            "ಸಮಸ್ಯೆ ಬರೆಯಿರಿ"
                        else
                            "Enter Issue"
                    )
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {

                    Toast.makeText(
                        context,
                        if (isKannada)
                            "ಯಶಸ್ವಿಯಾಗಿ ಸಲ್ಲಿಸಲಾಗಿದೆ"
                        else
                            "Submitted Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {

                Text(
                    if (isKannada)
                        "ಸಲ್ಲಿಸಿ"
                    else
                        "Submit"
                )
            }
        }
    }
}

@Composable
fun EventScreen(
    modifier: Modifier,
    isKannada: Boolean
) {

    LazyColumn(
        modifier = modifier.padding(20.dp)
    ) {

        item {

            ProjectCard(
                isKannada,
                "Health Camp"
            )

            ProjectCard(
                isKannada,
                "Farmers Meeting"
            )

            ProjectCard(
                isKannada,
                "Women Safety Workshop"
            )
        }
    }
}

@Composable
fun EmergencyScreen(
    modifier: Modifier,
    isKannada: Boolean
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        EmergencyItem("Police", "100")
        EmergencyItem("Ambulance", "108")
        EmergencyItem("Fire Force", "101")
        EmergencyItem("Women Helpline", "1091")
    }
}

@Composable
fun EmergencyItem(
    title: String,
    number: String
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {

                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:$number")
                )

                context.startActivity(intent)
            }
    ) {

        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(Icons.Default.Call, null)

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                "$title - $number",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun ProfileScreen(
    modifier: Modifier,
    isKannada: Boolean,
    userName: String
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            userName,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            if (isKannada)
                "ಸ್ಥಳ: ಬೆಂಗಳೂರು"
            else
                "Location: Bangalore"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            if (isKannada)
                "ಭಾಷೆ: ಕನ್ನಡ"
            else
                "Language: English"
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

            }
        ) {

            Text(
                if (isKannada)
                    "ಪ್ರೊಫೈಲ್ ಸಂಪಾದಿಸಿ"
                else
                    "Edit Profile"
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {

            }
        ) {

            Text(
                if (isKannada)
                    "ಲಾಗ್ ಔಟ್"
                else
                    "Logout"
            )
        }
    }
}