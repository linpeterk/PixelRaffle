package com.revature.pixelraffle.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.revature.pixelraffle.data.listColors
import com.revature.pixelraffle.ui.theme.graySurface

@Composable
fun MakeColorBar(myColor: MutableState<Color>){

    Card(modifier = Modifier.fillMaxWidth().padding(10.dp), backgroundColor = graySurface) {
        LazyRow(horizontalArrangement = Arrangement.Center) {


            itemsIndexed(listColors) { index, items->
                Button(modifier = Modifier
                    .height(40.dp).width(50.dp).padding(top=2.dp, bottom=2.dp),
                    shape = RectangleShape,
                    colors= ButtonDefaults.buttonColors(items),
                    onClick = { myColor.value = items }

                    //  .border(2.dp, Color.Blue)
                ) {
                    // Text(text="hi")
                }
            }
        }

    }
}