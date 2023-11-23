package com.abc.`in`.kural.ui

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.abc.`in`.kural.R
import com.abc.`in`.kural.model.Adhikaram
import com.abc.`in`.kural.navigation.NavigationInfo.NavigationScreen
import com.abc.`in`.kural.ui.theme.Brown
import com.abc.`in`.kural.viewmodel.KuralViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.OutputStreamWriter
import androidx.lifecycle.lifecycleScope
import android.util.Log

@Composable
fun HomeScreen(
    viewModel: KuralViewModel,
    navController: NavController) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize().background(Brown)) {

        Image(
            painter = painterResource(id = R.drawable.bg_one), // Replace with your image resource
            contentDescription = null, // Provide a description if needed
            modifier = Modifier.fillMaxWidth().fillMaxHeight().alpha(0.3f),
            contentScale = ContentScale.FillHeight
        )
       // Text(text = "Tamil")
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            //.padding(horizontal = 16.dp),
            // horizontalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeCard(navController, NavigationScreen.Thirukkural.name, R.drawable.thiruvalluval, "திருக்குறள்")
            HomeCard(navController, NavigationScreen.Aathisudi.name, R.drawable.avvai, "ஆத்திச்சூடி")
        }
    }
}

//  fun saveJsonToFile(context: Context, data: String, filename: String) {
//    Log.e("Data::::",data.toString())
//    try {
//        val outputStream = OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE))
//
//            outputStream.write(data)
//
//
//            outputStream.close()
//            val file = File(context.filesDir, filename)
//            val filePath = file.absolutePath
//            println("File path: $filePath")
//
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//}

@Composable
fun HomeCard(navController: NavController, destination: String, imageResource: Int, title: String) {
    Card(
        modifier = Modifier
            //.fillMaxWidth()
            .clickable { navController.navigate(destination) }
            .padding(16.dp)
            .animateContentSize() // For animated size changes
            .clip(RoundedCornerShape(16.dp))
            .shadow(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "ImageButton",
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(8.dp)) // Clip the image with rounded corners
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicText(
                text = title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}