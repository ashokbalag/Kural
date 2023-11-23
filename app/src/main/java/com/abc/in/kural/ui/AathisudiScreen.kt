package com.abc.`in`.kural.ui

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import com.abc.`in`.kural.ui.theme.Brown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AadhisudiScreen(
    viewModel: KuralViewModel,
    onNavigateToNextScreen: (Athisudi) -> Unit,
    modifier: Modifier = Modifier
) {
    val athisudiList = viewModel.athisudi.collectAsState(initial = emptyList()).value

    // Display UI based on the athikaramData
    Column {
        if (athisudiList.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize().background(Brown)) {
                Image(
                    painter = painterResource(id = R.drawable.bg_one), // Replace with your image resource
                    contentDescription = null, // Provide a description if needed
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().alpha(0.2f),
                    contentScale = ContentScale.FillHeight
                )
                LazyColumn {
                    items(athisudiList) { item ->
                        ItemCard(
                            athisudi = item,
                            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                            onNavigateToNextScreen
                        )
                    }
                }
            }
        } else {
            // UI when data is not available or still loading
            CircularProgressIndicator()
        }
    }
}



    @Composable
    fun ItemCard(
        athisudi: Athisudi,
        modifier: Modifier = Modifier,
        onNavigateToNextScreen : (Athisudi) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }
        Card(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImageWithCount(athisudi.number)
                    AthisudiInformation(athisudi)
                    Spacer(Modifier.weight(1f))
                    ItemButtonExpanded(
                        expanded = expanded,
                        onClick = { expanded = !expanded },
                    )
                }
                if (expanded) {
                    AthisudiDetails(
                        athisudi,
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            top = dimensionResource(R.dimen.padding_small),
                            bottom = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium)
                        ),
                        onNavigateToNextScreen
                    )
                }
            }
        }
    }

    @Composable
    fun AthisudiInformation(
        athisudi: Athisudi,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier) {
            Text(
                text = athisudi.poem,
                style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold,
                //modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
            )
            Text(
                text = athisudi.translation,
                style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold,
                //modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
            )
        }
    }

    @Composable
    private fun ImageWithCount(
        athisudiCount: Int,
        modifier: Modifier = Modifier
    ) {
        Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            // Image
            Image(
                modifier = modifier
                    .size(48.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.avvai),
                contentDescription = null // Decorative image, no content description
            )

            // Count indicator
            Box(
                modifier = Modifier
                    .size(48.dp)
            ) {
                Text(
                    text = athisudiCount.toString(), // Replace with your count value
                    color = Color.White,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    fontWeight = FontWeight.Bold,
                    //fontSize = 10.sp,
                )
            }
        }
    }

    @Composable
    private fun ItemButtonExpanded(
        expanded: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }


    @Composable
    fun AthisudiDetails(
        athisudi: Athisudi,
        modifier: Modifier = Modifier,
        onNavigateToNextScreen: (Athisudi) -> Unit
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
        Card(modifier = modifier.padding(4.dp)) {


                Column() {


                    DetailText(name = athisudi.paraphrase)
                    Divider(
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                    DetailText(name = athisudi.meaning)
                }
        }
            // Pushes the icon button to the right
            IconButton(
                onClick = { onNavigateToNextScreen(athisudi) },
                modifier = Modifier
                    .size(32.dp).padding(end = 8.dp)
                    .clip(MaterialTheme.shapes.small).align(Alignment.TopEnd)
            ) {
                Icon(
                    Icons.Filled.Share,
                    contentDescription = "Share",
                    tint = Brown
                )
            }

        }
    }

    @Composable
    fun DetailText(name: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = name,
                    modifier = Modifier.padding(8.dp).fillMaxSize(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }