/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abc.`in`.kural.ui


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abc.`in`.kural.model.Kural
import com.abc.`in`.kural.model.Athisudi
import com.abc.`in`.kural.navigation.NavigationInfo.NavigationScreen
import com.abc.`in`.kural.repository.KuralRepository
import com.abc.`in`.kural.viewmodel.KuralViewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
///**
// * enum values that represent the screens in the app
// */

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    //currentScreen: String,//NavigationScreen,
    currentScreen: NavigationScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title),
       // title = { Text(text=stringResource(R.string.my_app_name),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()) },
        actions = {
            IconButton(onClick = { /* Handle action 2 */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = { /* Handle action 3 */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notification")
            }
            IconButton(onClick = { /* Handle action 1 */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }


        },
        //backgroundColor = Color.Blue, // Replace with your desired background color
        modifier = Modifier.fillMaxWidth(),

        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
       // modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"//stringResource(R.string.back_button)
                    )
                }
            }else{
                IconButton(onClick = { /* Handle navigation drawer icon click */ }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            }
        }
    )
}
@Composable
fun rememberKuralViewModel(): KuralViewModel {
    val context = LocalContext.current

    val repository = remember {
        KuralRepository(context) // Instantiate your repository here
    }

    return remember {
        KuralViewModel(repository) // Provide the repository to the ViewModel
    }
}

//@Composable
//fun KuralApp(
//    mViewModel: KuralViewModel = rememberKuralViewModel(),
//    navController: NavHostController = rememberNavController()
//) {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    //val dataList = viewModel.
//    // Get current back stack entry
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    // Get the name of the current screen
//    val currentScreen = NavigationScreen.valueOf(
//        //NavigationScreen.Start.name
//        backStackEntry?.destination?.route ?: NavigationScreen.Start.name
//    )
//    val mToolbarVisible = currentScreen==NavigationScreen.Start
//
//    Scaffold(
//        modifier = Modifier,
//            topBar = {
//                AppBar(
//                    currentScreen = currentScreen,
//                    canNavigateBack = navController.previousBackStackEntry != null,
//                    navigateUp = { navController.navigateUp() }
//                )
//            }
//
//    ) { innerPadding ->
////
//        NavHost(
//            navController = navController,
//            startDestination = NavigationScreen.Start.name,
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable(route = NavigationScreen.Start.name) {
//                SplashScreen(navController)
//            }
//            composable(route = NavigationScreen.Home.name) {
//                HomeScreen(navController)
//            }
//
////            composable(route = NavigationScreen.Adhikaaram.name) {
////
////                AdhikaramScreen(
////                        viewModel = mViewModel,
////                        onNavigateToNextScreen = {data ->
////                            Log.e("dataname",data.toString())
////                           // navController.navigate("Athikaaram",argument("data" to data))
////                            navController.navigate("Athikaaram")
////                        },
////                        modifier = Modifier
////                            .fillMaxSize()
////                            .padding(16.dp)
////                    )
////            }
//
//            composable("Adhikaaram"){
//
//                    mViewModel.filteredIyalData("அறத்துப்பால்")
//
//                    IyalScreen(
//                        viewModel = mViewModel,
//                        filterData = "அறத்துப்பால்",//mKey,
//                        onNavigateToNextScreen = { mAthikaramDetails ->
//                            navController.navigate(NavigationScreen.TenKuralkal.name)
//                        },
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(16.dp)
//                    )
//
//
//            }
//
//            composable(NavigationScreen.TenKuralkal.name){
//
//                mViewModel.filteredPaalData("பாயிரவியல்")
//
//                PaalScreen(
//                    viewModel = mViewModel,
//                    filterData = "பாயிரவியல்",//mKey,
//                    onNavigateToNextScreen = { mAthikaramDetails ->
//                        navController.navigate(NavigationScreen.Kural.name)
//                    },
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp)
//                )
//
//
//            }
//
//            composable(NavigationScreen.Kural.name){
//
//                mViewModel.filteredKuralData("கடவுள் வாழ்த்து")
//
//                KuralScreen(
//                    viewModel = mViewModel,
//                    filterData = "கடவுள் வாழ்த்து",//mKey,
//                    onNavigateToNextScreen = { mAthikaramDetails ->
//                        //  navController.navigate(NavigationScreen.TenKuralkal.name)
//                    },
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp)
//                )
//
//
//            }
//
//
//
////            composable(
////                route = "${NavigationScreen.TenKuralkal.name}/{athikaramDetails}",
////                arguments = listOf(navArgument("athikaramDetails") { type = NavType.ParcelableType(AthikaramDetails::class.java) })
////            ) { backStackEntry ->
////               // val navBackStackEntry by navController.currentBackStackEntryAsState()
////                val mDataList:  List<AthikaramDetails> = backStackEntry.arguments?.getParcelableArrayList<AthikaramDetails>("athikaramDetails") ?: emptyList()
////
////                FourthScreen(
////                    dataList = mDataList,
////                    onNavigateToNextScreen = {athikaramSubDetails ->
//////                        val bundle = bundleOf("athikaramSubDetails" to athikaramSubDetails)
//////                        navController.navigate(NavigationScreen.Kural.name,bundle)
////
////                        val bundle = bundleOf("athikaramSubDetails" to athikaramSubDetails)
////                        navController.navigate(NavigationScreen.Kural.name+"/{$bundle}")
////                    },
////                    modifier = Modifier
////                        .fillMaxSize()
////                        .padding(16.dp)
////                )
////            }
////
////           composable(
////                    route = "${NavigationScreen.Kural.name}/{athikaramSubDetails}",
////                    arguments = listOf(navArgument("athikaramSubDetails") { type = NavType.ParcelableType(AthikaramSubDetails::class.java) })
////                ) {backStackEntry ->
////
////               val mDataList:  List<AthikaramSubDetails> = backStackEntry.arguments?.getParcelableArrayList<AthikaramSubDetails>("athikaramSubDetails") ?: emptyList()
////
////                FivthScreen(
////                    dataList = mDataList,
////                    onNavigateToNextScreen = {kuralkal ->
//////                        val bundle = bundleOf("kuralkal" to kuralkal)
//////                        navController.navigate(NavigationScreen.Five.name,bundle)
////                        val bundle = bundleOf("kuralkal" to kuralkal)
////                        navController.navigate(NavigationScreen.Five.name+"/{$bundle}")
////                    },
////                    modifier = Modifier
////                        .fillMaxSize()
////                        .padding(16.dp)
////                )
////            }
////
////            composable(
////                    route = "${NavigationScreen.Six.name}/{kuralkal}",
////                    arguments = listOf(navArgument("kuralkal") { type = NavType.ParcelableType(AthikaramSubDetails::class.java) })
////                ) {backStackEntry ->
////
////                    val mDataList:  List<Kural> = backStackEntry.arguments?.getParcelableArrayList<Kural>("kuralkal") ?: emptyList()
////
////                SixScreen(
////                    dataList = mDataList,
////                    onNavigateToNextScreen = {kuralData ->
//////                        val bundle = bundleOf("kuralData" to kuralData)
//////                        navController.navigate(NavigationScreen.Six.name,bundle)
////
////                        val bundle = bundleOf("kuralData" to kuralData)
////                        navController.navigate(NavigationScreen.Six.name+"/{$bundle}")
////                    },
////                    modifier = Modifier
////                        .fillMaxSize()
////                        .padding(16.dp)
////                )
////            }
////
////
////                composable(
////                    route = "${NavigationScreen.Seven.name}/{kuralData}",
////                    arguments = listOf(navArgument("kuralData") { type = NavType.ParcelableType(Kural::class.java) })
////                ) {backStackEntry ->
////
////                    val mDataList:  Kural? = backStackEntry.arguments?.getParcelable("kuralData")
////
////                SevenScreen(
////                    dataList = mDataList!!,
////                    onNavigateToNextScreen = { kuralData ->
////                        navController.navigate(NavigationScreen.Athikaaram.name)
////                    },
////                    modifier = Modifier
////                        .fillMaxSize()
////                        .padding(16.dp)
////                )
////            }
////        thirukkuralState?.let { data ->
////            AthikaramList(data.athikaram)
////        }
////        LazyColumn(
////            modifier = Modifier.fillMaxSize()
////        ) {
////            items(itemsList) { index ->
////                Card(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(8.dp)
////                ) {
////                    Text("Item $index")
////                }
////            }
////        }
//        }
//
//    }
//}


@Composable
fun KuralApp(
    mViewModel: KuralViewModel = rememberKuralViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    //    // Get current back stack entry

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Home.name
    ) {

        composable(NavigationScreen.Aathisudi.name){
            AadhisudiScreen(
                viewModel = mViewModel,
                onNavigateToNextScreen = { data ->
                    share(context,null,data)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )

        }
        composable(route = NavigationScreen.Home.name) {
                HomeScreen(viewModel = mViewModel,navController)
            }

        composable(route = NavigationScreen.Thirukkural.name) {
            AdhikaramScreen(
                        viewModel = mViewModel,
                        onNavigateToNextScreen = {
                            navController.navigate(NavigationScreen.Iyal.name+"/$it")
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
        }


            composable(NavigationScreen.Iyal.name+"/{Iyal}",
                arguments = listOf(
                    navArgument("Iyal") {
                        type = NavType.StringType
                    }
                )
                ){
                val param = it.arguments?.getString("Iyal") ?: ""
                    mViewModel.filteredIyalData(param)
                    IyalScreen(
                        viewModel = mViewModel,
                        filterData = param,//mKey,
                        onNavigateToNextScreen = {
                            navController.navigate(NavigationScreen.Paal.name+"/$it")
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
            }


            composable(NavigationScreen.Paal.name+"/{Paal}",
                    arguments = listOf(
                        navArgument("Paal") {
                            type = NavType.StringType
                        }
                    )
                ){
                    val param = it.arguments?.getString("Paal") ?: ""
                    Log.e("kurallistdata",param)
                    mViewModel.filteredPaalData(param)

                PaalScreen(
                    viewModel = mViewModel,
                    filterData = param,//mKey,
                    onNavigateToNextScreen = {
                        navController.navigate(NavigationScreen.Kuralkal.name+"/$it")
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )


            }

        composable(NavigationScreen.Kuralkal.name+"/{kuralkal}",
            arguments = listOf(
                navArgument("kuralkal") {
                    type = NavType.StringType
                }
            )
        ){
            val param = it.arguments?.getString("kuralkal") ?: ""
            mViewModel.filteredKuralData(param)

            KuralScreen(
                viewModel = mViewModel,
                filterData = param,//mKey,
                onNavigateToNextScreen = {
                    navController.navigate(NavigationScreen.Kural.name)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )


        }

        composable(NavigationScreen.Kural.name){

           // val param = mViewModel.kural.value

                    KuralDetailsScreen(
                    viewModel = mViewModel,
                  //  filterData = param!!,//mKey,
                    onNavigateToNextScreen = {
                        share(context,it,null)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )


            }
    }

}


/**
 * Creates an intent to share order details
 */
private fun share(context: Context, kural: Kural?,athisudi: Athisudi?) {


    // Create an ACTION_SEND implicit intent with order details in the intent extras

    val sharedata : String = if(kural!=null)  {"""
                 குறள் : ${kural._id}
                        ${kural.line1}
                        ${kural.line2}
                        ${kural.desc}
                        ${kural.line_en}
                        ${kural.desc_en}
                                """.trimIndent()
                }
                else if(athisudi!=null){"""
                 ஆத்திச்சூடி : ${athisudi.number}
                        ${athisudi.poem}
                        ${athisudi.translation}
                         ${athisudi.meaning}
                                """.trimIndent()
                } else  ""

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        //putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, sharedata)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            "Share link:"//context.getString(R.string.new_cupcake_order)
        )
    )
}




//@Preview
//@Composable
//fun PreviewImageButtonWithName() {
//    // Creating a fake NavController for preview
//    val fakeNavController = object : NavController() {
//        // Implement methods if needed for the preview
//    }
//
//    HomeScreen(fakeNavController)
//}

//@Composable
//fun AthikaramList(items: List<Athikarankal>) {
//    LazyColumn {
//        items(items) { item ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                Text("Item ${item.name}")
//            }
//        }
//    }
//}