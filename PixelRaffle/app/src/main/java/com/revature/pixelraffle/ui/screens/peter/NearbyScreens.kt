package com.revature.pixelraffle.ui.screens.peter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.revature.pixelraffle.R
import com.revature.pixelraffle.network.datamodel.ResponseGigType
import com.revature.pixelraffle.ui.MakeGoogleMap
import com.revature.pixelraffle.ui.cameraPositionState
import com.revature.pixelraffle.ui.googleHQ
import com.revature.pixelraffle.ui.navigation.BottomNavigationBar
import com.revature.pixelraffle.ui.theme.blue
import com.revature.pixelraffle.ui.theme.graySurface
import com.revature.pixelraffle.ui.theme.light_orange
import com.revature.pixelraffle.viewmodel.TheViewModel
import com.revature.pixelraffle.viewmodel.UserViewModel
import de.charlex.compose.BottomDrawerScaffold
import de.charlex.compose.BottomDrawerScaffoldState
import de.charlex.compose.BottomDrawerValue
import de.charlex.compose.rememberBottomDrawerScaffoldState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NearbyScreens(vModel: UserViewModel,
              navController: NavController
){
    vModel.getGigLists()
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
        bottomBar = {BottomNavigationBar(navController)},
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

//                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Go back", modifier = Modifier
//                                .size(40.dp)
//
//                                .clickable {
////                                    navController.navigate(NavScreens.Gig.route) {
////                                        popUpTo(NavScreens.Gig.route)
////                                    }
//                                })
                            //Spacer(Modifier.padding(end=120.dp))
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                            //    .border(2.dp, Color.Blue)
                                .height(70.dp),
                                contentAlignment = Alignment.CenterEnd)
                            {
                                Column(modifier = Modifier
                                    .padding(2.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally)
                                {
                                    Image(painter = painterResource(id = R.drawable.logo_only), contentDescription = "Local Events",
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
                                    Text(text = "Local Events", color = graySurface, fontSize = 14.sp)
                                }
                            }



                            Box(
                                modifier = Modifier
                                    .weight(0.6f)
                                    .fillMaxWidth()
                                 //   .border(2.dp, Color.Red)
                                    .height(70.dp),
                                contentAlignment = Alignment.CenterEnd

                            ){
                                Card(elevation = 3.dp, modifier = Modifier.padding(10.dp)) {
                                    dropDownMenu(vModel = vModel)
                                }
                            }


                        }



                        LazyScrollTaskBoard(vModel = vModel, navcontroller = navController, state, buttonText)

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
fun dropDownMenu(vModel: UserViewModel){
    var expanded by remember { mutableStateOf(false) }
    val sortItems = listOf("Sortby: Default", "Sortby: Distance", "Sortby: Date")
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier) {
        Text(sortItems[selectedIndex],modifier = Modifier
            .width(130.dp)
            .padding(3.dp)
            .clickable(onClick = { expanded = true })
            .background(
                Color.White
            ),
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

                    vModel.sortBy(index) //index determines which sorting methods


                }) {
                    Text(text = s )
                }
            }
        }
    }

}

var yourInterest:List<ResponseGigType>? = null

var baseInterests:List<ResponseGigType> = listOf(ResponseGigType(imageRes = "workinprogress"), ResponseGigType(imageRes = "workinprogress"), ResponseGigType(imageRes = "workinprogress"))

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyScrollTaskBoard(vModel: UserViewModel, navcontroller:NavController, state: BottomDrawerScaffoldState, buttonText : MutableState<String>){

    // val currentList = vModel.currentTaskList.observeAsState(arrayListOf())
 //   val currentList = vModel.getGigLists()

   // yourInterest = currentList?.ur


    val scope  = rememberCoroutineScope()

    // vModel.loginRequestLiveData
    LazyColumn(
        modifier = Modifier
            .background(graySurface)
            .padding(5.dp)
            .fillMaxSize()

    ){

        items(
            vModel.yourList
        ){ item->
            Spacer(modifier = Modifier.padding(3.dp))
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                ,

                elevation = 7.dp

            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){

//                    Image(painter = painterResource(id = vModel.getImageId(context = LocalContext.current,item.imageId?:"workinprogress") ), contentDescription = null,
//                        modifier = Modifier
//                            //    .border(2.dp, Color.Red)
//                            .padding(20.dp)
//                            .size(70.dp)
//                            .clickable {
//                                cameraPositionState?.position = CameraPosition.fromLatLngZoom(
//                                    LatLng(item.lat ?: 37.4198, item.lng ?: -122.0788), 14f
//                                )
//                                scope.launch { state.bottomDrawerState.collapse() }
//                                buttonText.value = "Expand"
//                            }
//
//                    )
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState())
//                        .clickable(onClick = {
//                            vModel.fetchTaskById(item.taskId)
////                            vModel.getReviews(2, 2)
//                            navcontroller.navigate(NavScreens.WhenJob.route + "/${item.taskId}")
//
//                        })
                    ){
                        Row() {
                            Text(
                                text = "${item.name ?: "No name Found"}",
                                fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .clickable {
                                        cameraPositionState?.position = CameraPosition.fromLatLngZoom(
                                            LatLng(item.lat ?: 37.4198, item.lng ?: -122.0788), 14f
                                        )
                                        scope.launch { state.bottomDrawerState.collapse() }
                                        buttonText.value = "Expand"
                                    }
                                , fontFamily = FontFamily.SansSerif,

                                )

                            Text(
                                text = "${item.time}",
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.End,
                                fontWeight = FontWeight.W500,
                                color = Color.Red
                            )

                        }
                        //    Text(text = "${item.person_name}", fontSize = 14.sp,  modifier = Modifier.fillMaxWidth(), fontFamily = FontFamily.SansSerif)
                        Row() {
                            Text(
                                text = "\nDescription: ",
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "\n${item.description ?: "Smith"}",
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                fontFamily = FontFamily.SansSerif
                            )

                        }
                        Row() {
                            Text(
                                text = "Location: ",
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "${item.address ?: "No Address Found"}",
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                fontFamily = FontFamily.SansSerif
                            )
                        }
//                        Row() {
//                            Text(
//                                text = "Date: ",
//                                fontSize = 14.sp,
//                                fontFamily = FontFamily.SansSerif,
//                                fontWeight = FontWeight.SemiBold
//                            )
//                            Text(
//                                text = "${item.name ?: "No Date Found"}",
//                                fontSize = 14.sp,
//                                modifier = Modifier.fillMaxWidth(),
//                                fontFamily = FontFamily.SansSerif
//                            )
//                        }
                        Row() {
                            Text(
                                text = "${vModel.distance(googleHQ,
                                    LatLng(item.lat?:googleHQ.latitude, item.lng?:googleHQ.longitude)
                                ) ?: "No Address Found"} miles",
                                fontSize = 14.sp,
                                modifier = Modifier.fillMaxWidth(),
                                fontFamily = FontFamily.SansSerif,
                                fontStyle = FontStyle.Italic,
                                color = blue

                            )
                        }

                        //          Spacer(modifier = Modifier.padding(5.dp))
//                        Column() {
////                            Text(
////                                text = "Description:",
////                                overflow = TextOverflow.Visible,
////                                fontSize = 14.sp,
////                                fontFamily = FontFamily.SansSerif,
////                                fontWeight = FontWeight.Bold
////                            )
////                            Text(
////                                text = "\n\"${item.description} \"",
////                                overflow = TextOverflow.Visible,
////                                fontSize = 14.sp,
////                                style = MaterialTheme.typography.h1
//
//
//                            )
//                        }
                    }
                }
            }

        }

    }
}