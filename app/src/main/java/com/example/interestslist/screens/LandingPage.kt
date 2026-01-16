package com.example.interestslist.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interestslist.R
import com.example.interestslist.ui.theme.InterestsListTheme

@Composable
fun LandingPage(modifier: Modifier = Modifier) {
    val listOfInterest = listOf("Football", "Basketball", "Rugby", "Tennis", "Cricket", "Swimming", "Cycling", "Running", "Yoga", "Boxing", "Golf", "Hockey", "Volleyball", "Badminton")
    Column(
        modifier = modifier
            .fillMaxSize() ,
        verticalArrangement = Arrangement.Center
    ) {
        PersonalizeText(modifier = modifier)
        InterestsOptions(modifier= modifier.weight(1f)
            .fillMaxWidth(), optionsList = listOfInterest)
    }
}

@Composable
private fun InterestsOptions(modifier: Modifier, optionsList: List<String>) {
    val context = LocalContext.current
    val selectedItems = remember { mutableStateOf(setOf<String>()) }

    LazyColumn(modifier = modifier) {
        items(optionsList) { option ->
            val isSelected = selectedItems.value.contains(option)
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF03A9F4) else Color.Transparent,
                animationSpec = tween(durationMillis = 300),
                label = "background color"
            )
            val borderColor by animateColorAsState(
                targetValue = if (isSelected) backgroundColor else Color.Gray,
                animationSpec = tween(durationMillis = 300),
                label = "border color"
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor)
                    .border(width = 1.5.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
                    .clickable {
                        selectedItems.value = if (isSelected) {
                            selectedItems.value - option
                        } else {
                            selectedItems.value + option
                        }
                    }
                    .padding(12.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = option,
                    fontSize = 23.sp,
                    color = if (isSelected) Color.White else Color.Black
                )
                AnimatedVisibility(
                    visible = isSelected,
                    enter = scaleIn(
                        animationSpec = tween(
                            durationMillis = 3000,
                            delayMillis = 0
                        )
                    ),
                    exit = scaleOut(animationSpec = spring())
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.Blue
                    )
                }
            }
        }

        item {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                onClick = {
                    Toast.makeText(context, "Btn is Clicked", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    color = colorResource(R.color.white)
                )
            }
        }
    }
}

@Composable
private fun PersonalizeText(modifier: Modifier) {
    Column(
        modifier = modifier.padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        Text(
            text = "Personalize your experience",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black),
            lineHeight = 32.sp,
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Choose Your Interest",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LandingPagePreview() {
    InterestsListTheme {
        LandingPage()
    }
}