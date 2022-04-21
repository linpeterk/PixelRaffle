package com.revature.pixelraffle.ui.screens.peter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.pixelraffle.R
import com.revature.pixelraffle.ui.MakeGoogleMap
import com.revature.pixelraffle.ui.theme.graySurface
import com.revature.pixelraffle.ui.theme.light_orange
import com.revature.pixelraffle.viewmodel.TheViewModel
import de.charlex.compose.BottomDrawerScaffold
import de.charlex.compose.BottomDrawerValue
import de.charlex.compose.rememberBottomDrawerScaffoldState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NearbyScreens(vModel: TheViewModel,
              navController: NavController
){

    var peekHeight = remember { mutableStateOf(200.dp) }
    val scope = rememberCoroutineScope()
    //de.charlex.compose.BottomDrawerState(initialValue = BottomDrawerValue.Expanded, drawerTopInset = 0))
    //val state = rememberBottomDrawerScaffoldState(1)
    val state = rememberBottomDrawerScaffoldState()

    var buttonText = remember{mutableStateOf("Show Map")}




    // state.bottomDrawerState.confirmStateChange(BottomDrawerValue.Expanded)

    BottomDrawerScaffold(
        //scaffoldState = rememberBottomDrawerState(5)
        scaffoldState = state  ,
        drawerModifier = Modifier.fillMaxSize(),
        drawerGesturesEnabled = true,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(buttonText.value,
                    color= Color.White
                ) },
                backgroundColor= Color.Red,
                modifier = Modifier.wrapContentSize(),
                onClick = {
                    if (state.bottomDrawerState.isCollapsed) {

                        Log.d(TAG, "iscollapsed ${state.bottomDrawerState.isCollapsed}")
                        scope.launch {
                            state.bottomDrawerState.expand()

                        }
                        buttonText.value = "Show Map"
                    } else {
                        Log.d(TAG, "isnotcollapsed")
                        scope.launch {
                            state.bottomDrawerState.collapse()


                        }
                        buttonText.value = "Expand"
                    }


                }
            )
        },

        floatingActionButtonPosition= FabPosition.End,
        isFloatingActionButtonDocked = false,
        drawerPeekHeight = peekHeight.value,
        drawerBackgroundColor = Color.Transparent,  //Transparent drawer for custom Drawer shape
        drawerElevation = 0.dp,

        drawerContent = {

            //initalize values by fixing peekheight
//            scope.launch {
//                state.bottomDrawerState.expand()
//
//            }

            LaunchedEffect (state.bottomDrawerState) {
                state.bottomDrawerState.expand()

            }

            Surface(                    //To add Padding to Drawer
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .fillMaxWidth()

                ,

                ) {
                // shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                Surface(
                    color = Color.White
                ) {
                    Column(modifier = Modifier) {
                        Row(
                            modifier = Modifier
                                //  .weight(1f)
                                .fillMaxWidth()
                                //     .border(3.dp, Color.Red)
                                .background(Color.White)

                        ) {
                            // CategoriesBar(vModel = vModel, vModel.categoriesTask, mode = 1, navController = navcontroller)

                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Go back", modifier = Modifier
                                .size(40.dp)

                                .clickable {
//                                    navController.navigate(NavScreens.Gig.route) {
//                                        popUpTo(NavScreens.Gig.route)
//                                    }
                                })
                            Spacer(Modifier.padding(end=120.dp))
                            Column(modifier = Modifier
                                .padding(2.dp),
                                horizontalAlignment = Alignment.CenterHorizontally)
                            {
                                Image(painter = painterResource(id = R.drawable.facebook), contentDescription = "Post Task",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(
                                            color = light_orange,
                                            shape = RoundedCornerShape(13.dp)
                                        )
                                        .clickable {
//                                            navController.navigate(NavScreens.PostTask.route) {
//                                                popUpTo(NavScreens.PostTask.route)
//                                            }
                                        })
                                Text(text = "Post Task", color = graySurface, fontSize = 14.sp)
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth().height(70.dp),
                                contentAlignment = Alignment.CenterEnd

                            ){
                                Card(elevation = 3.dp, modifier = Modifier.padding(10.dp)) {
                                    dropDownMenu(vModel = vModel)
                                }
                            }


                        }



                 //       LazyScrollTaskBoard(vModel = vModel, navcontroller = navController, state, buttonText)

                    }


                }


            }
        }
    ){
        Box(modifier=Modifier
            .padding(bottom = 200.dp)) {
            MakeGoogleMap(vModel = vModel, mode = 1, navController = navController)
            //    TopBar()
        }
    }

}

@Composable
fun dropDownMenu(vModel: TheViewModel){
    var expanded by remember { mutableStateOf(false) }
    val sortItems = listOf("Sortby: Default", "Sortby: Distance", "Sortby: $/hr", "SortBy: Date")
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier) {
        Text(sortItems[selectedIndex],modifier = Modifier.width(130.dp).padding(3.dp)
            .clickable(onClick = { expanded = true }).background(
                Color.White),
            textAlign = TextAlign.Center
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(
                Color.White)
        ) {
            sortItems.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false

                 //   vModel.sortBy(index) //index determines which sorting methods


                }) {
                    Text(text = s )
                }
            }
        }
    }

}