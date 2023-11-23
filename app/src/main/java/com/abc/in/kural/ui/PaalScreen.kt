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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abc.`in`.kural.R

import com.abc.`in`.kural.ui.theme.Brown
import com.abc.`in`.kural.viewmodel.KuralViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaalScreen(
    viewModel: KuralViewModel,
    filterData: String,
    onNavigateToNextScreen: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val paalList = viewModel.paalData.collectAsState(initial = emptyList()).value

    Column {
            if(paalList!=null && paalList.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize().background(Brown)) {
                    Image(
                        painter = painterResource(id = R.drawable.thiruvalluval), // Replace with your image resource
                        contentDescription = null, // Provide a description if needed
                        modifier = Modifier.fillMaxSize().alpha(0.1f),
                        contentScale = ContentScale.Fit
                    )
                    LazyColumn {
                        items(paalList) { item ->
                            Card(
                                onClick = { onNavigateToNextScreen(item.name) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = item.name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.hand),
                                        contentDescription = null,
                                        //tint = Color.Gray,
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
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



//@Preview
//@Composable
//fun ThirdScreenPreview(){
//    PaalScreen(
//        dataList = DataSource.quantityOptions,
//        onNavigateToNextScreen = {},
//        modifier = Modifier.fillMaxSize().padding(dimensionResource(16.dp))
//    )
//}
