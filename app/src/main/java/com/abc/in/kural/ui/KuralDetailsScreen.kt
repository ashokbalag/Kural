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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import com.abc.`in`.kural.ui.theme.Brown
import com.abc.`in`.kural.ui.theme.WhiteLight
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon

@Composable
fun KuralDetailsScreen(
    viewModel: KuralViewModel,
    //filterData: Kural,
    onNavigateToNextScreen: (Kural) -> Unit,
    modifier: Modifier = Modifier
){

    val filterData = viewModel.kural.value!!
        Box(
            modifier = Modifier.fillMaxSize().background(Brown)
        ) {
            // Background image

            // Full-screen image
            Image(
                painter = painterResource(id = R.drawable.fi), // Replace with your image resource
                contentDescription = null, // Provide a description if needed
                modifier = Modifier.fillMaxSize().alpha(0.1f),
                contentScale = ContentScale.FillHeight
            )
            FloatingActionButton(
                onClick = { onNavigateToNextScreen(filterData) },
                modifier = Modifier.padding(16.dp).align(Alignment.TopEnd),
            ) {
                Icon(Icons.Filled.Share, "Floating action button.")
            }
            Column(
                 modifier = Modifier.padding(8.dp).align(Alignment.BottomCenter),
               // modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.Bottom,
                //horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier.size(256.dp).align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(R.drawable.thiruvalluval),
                    contentDescription = null // Decorative image, no content description
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(WhiteLight)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Column() {
                            Text(
                                text = filterData.line1,
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    FontWeight.Bold
                                ),

                            )
                            Text(
                                text = filterData.line2,
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    FontWeight.Bold
                                ),

                            )
                            Divider(modifier = Modifier.padding(top = 5.dp),color= Color.Gray, thickness =1.dp)
                            Text(
                                text = filterData.line_en.toString(),
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    FontWeight.Bold,
                                    lineHeight = 24.sp),

                                )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Column() {
                            Text(
                                text = filterData.desc,
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    FontWeight.Bold,
                                    lineHeight = 24.sp,
                                ),
                            )
                            Divider(
                                modifier = Modifier.padding(top = 5.dp),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                            Text(
                                text = filterData.desc_en.toString(),
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    FontWeight.Bold,
                                    lineHeight = 24.sp,
                                ),

                                )
                        }

                    }
                }

            }


        }


}



