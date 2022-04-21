package com.revature.pixelraffle.ui.screens.adama

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun DropDownList(){

    Column(modifier = Modifier.fillMaxWidth()
    ) {
        Row(){
            Text(text="Secret Questions")
        }
        Row(){
            var expanded by remember{ mutableStateOf(false)}
            val items = listOf("Question1", "Question2", "Question3", "Question4", "Question5")
            val disabledValue = "Question1"

            var selectedIndex by remember { mutableStateOf(0)}

            Box(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter)
            ){
                Text(items[selectedIndex],
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { expanded = true })
                        .background(Color.Transparent))
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    items.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = { selectedIndex = index
                            expanded = false
                        }) {
                            val disabledText = if (s == disabledValue){
                                ""
                            } else{
                                ""
                            }
                            Text(text = s +disabledText)
                        }
                    }
                }
            }
        }

    }


}