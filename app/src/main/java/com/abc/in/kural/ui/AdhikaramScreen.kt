package com.abc.`in`.kural.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.abc.`in`.kural.model.Adhikaram

import com.abc.`in`.kural.viewmodel.KuralViewModel
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.abc.`in`.kural.R
import com.abc.`in`.kural.model.Athisudi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.magnifier
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abc.`in`.kural.ui.theme.Brown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdhikaramScreen(
    viewModel: KuralViewModel,
    onNavigateToNextScreen: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val adhikaramData = viewModel.adhikaramData.collectAsState(initial = emptyList()).value
    // Display UI based on the athikaramData
    Column {
        if (adhikaramData.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize().background(Brown)) {
                Image(
                    painter = painterResource(id = R.drawable.thiruvalluval), // Replace with your image resource
                    contentDescription = null, // Provide a description if needed
                    modifier = Modifier.fillMaxSize().alpha(0.1f),
                    contentScale = ContentScale.Fit
                )
                LazyColumn {
                    items(adhikaramData) { item ->
                        Log.e("item", item.name)
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