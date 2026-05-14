package com.example.gramasuvidhanexus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEDE7F6))
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Grama Suvidha Nexus",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A148C)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Enter Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7C4DFF)
                    ),

                    shape = RoundedCornerShape(30.dp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {

                    Text(
                        text = "Continue",
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                ProjectCard(
                    title = "Smart Road Development",
                    progress = "82% Completed"
                )

                Spacer(modifier = Modifier.height(20.dp))

                ProjectCard(
                    title = "Water Supply Management",
                    progress = "71% Completed"
                )
            }
        }
    }
}

@Composable
fun ProjectCard(title: String, progress: String) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFDCCFF3)
        )
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = title,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            LinearProgressIndicator(
                progress = 0.8f,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF7C4DFF)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = progress,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { },

                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E35B1)
                )
            ) {

                Text("Navigate")
            }
        }
    }
}
