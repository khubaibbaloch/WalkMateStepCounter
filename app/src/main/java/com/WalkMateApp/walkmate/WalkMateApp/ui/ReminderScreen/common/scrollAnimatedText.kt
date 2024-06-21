package com.WalkMateApp.walkmate.WalkMateApp.ui.ReminderScreen.common

import androidx.compose.runtime.Composable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun scrollAnimatedText(
    listStart: Int,
    listEnd: Int,
    onCenterIndexChanged: (Int) -> Unit // Lambda parameter to return the center index value
) {
    val list = (listStart..listEnd).toList()
    val extendedList =
        List(100000) { list[it % list.size] } // Repeating the list to ensure wrap around

    // Start from the middle of the extended list
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = extendedList.size / 2)
    val coroutineScope = rememberCoroutineScope()

    var isUserScrolling by remember { mutableStateOf(false) }

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress && !isUserScrolling) {
            delay(10) // Delay before initiating the scroll loop (adjust as needed)
            while (true) {
                // Calculate the center index
                val firstVisibleItemIndex = listState.firstVisibleItemIndex
                val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
                if (visibleItemsInfo.isNotEmpty()) {
                    val centerIndex = firstVisibleItemIndex + (visibleItemsInfo.size / 2) - 2
                    coroutineScope.launch {
                        listState.animateScrollToItem(centerIndex)
                    }
                    // Invoke the lambda to pass the center index value
                    onCenterIndexChanged(extendedList[centerIndex-1])
                }
                delay(10) // Delay between each scroll (adjust as needed)
            }
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { listState.isScrollInProgress }.collect { isScrolling ->
            isUserScrolling = isScrolling
        }
    }

    Column(
        modifier = Modifier
            .height(160.dp) // Adjust the height to show only 3 items at a time
            .width(70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .height(160.dp) // Adjust the height to show only 3 items at a time
                .width(70.dp),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(extendedList) { index, item ->
                val firstVisibleItemIndex = listState.firstVisibleItemIndex
                val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
                val centerIndex = firstVisibleItemIndex + (visibleItemsInfo.size / 2) - 2
                val fontSize = when (index) {
                    centerIndex - 1, centerIndex + 1 -> 30.sp
                    centerIndex -> 15.sp
                    else -> 15.sp
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp), // Ensuring each item has a height of 50.dp
                    contentAlignment = Alignment.Center
                ) {
                    val formattedText = "%02d".format(item)
                    Text(text = formattedText, fontSize = fontSize)
                }
            }
        }
        // Display the center item index for debugging purposes
        val centerIndex =
            listState.firstVisibleItemIndex + (listState.layoutInfo.visibleItemsInfo.size / 2)

        // Call the lambda to return the center index value
        onCenterIndexChanged(extendedList[centerIndex-1])
    }
}
