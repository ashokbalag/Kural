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

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.abc.`in`.kural.viewmodel.KuralViewModel
import com.abc.`in`.kural.model.Kural
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abc.`in`.kural.R
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
@Composable
fun KuralScreen(
    viewModel: KuralViewModel,
    filterData: String,
    onNavigateToNextScreen: (Kural) -> Unit,
    modifier: Modifier = Modifier
){

    val kuralList = viewModel.kuralData.collectAsState(initial = emptyList()).value

    Column {
            if(kuralList!=null && kuralList.isNotEmpty()) {
                LazyColumn {
                    items(kuralList) { item ->

                        Box(
                           // onClick = { onNavigateToNextScreen(item.name) },
                            modifier = Modifier
                                .fillMaxWidth()
                                //.padding(8.dp)
                                .clickable {
                                    viewModel.filteredKural(item)
                                    onNavigateToNextScreen(item)
                                }
                        ) {
                            // Background image
                            Image(
                                painter = painterResource(id = R.drawable.kural), // Replace with your image resource
                                contentDescription = "Background Image",
                                modifier = Modifier
                                    .fillMaxWidth() // Make width fill the screen
                                    .height(90.dp), // Set height to 200 pixels
                                contentScale = ContentScale.FillBounds
                                //.alpha(0.5f) // Adjust the alpha as needed for transparency
                            )

                            Column(
                                // modifier = Modifier.padding(16.dp),
                                modifier = Modifier.padding(start = 16.dp).height(90.dp),
                                verticalArrangement = Arrangement.Center,
                                //horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = item.line1,
                                    style = TextStyle(color = Color.Black, fontSize = 12.sp, FontWeight.Bold),
                                    modifier = Modifier.padding(start = 45.dp, end = 10.dp, bottom = 4.dp)
                                )
                                Text(
                                    text = item.line2,
                                    style = TextStyle(color = Color.Black, fontSize = 12.sp, FontWeight.Bold),
                                    modifier = Modifier.padding(start = 45.dp, bottom = 2.dp)
                                )
                            }

                        }
                    }
                }

        } else {
            // UI when data is not available or still loading
            CircularProgressIndicator()
        }
    }


}

