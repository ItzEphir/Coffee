package com.ephirium.coffee.feature.profile.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ephirium.coffee.feature.profile.presentation.model.ComplimentUiModel
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Compliments(
    compliments: List<ComplimentUiModel>,
    onRefresh: suspend () -> Unit,
    onAdd: suspend () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val reachedBottom by remember {
        derivedStateOf {
            val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == lazyListState.layoutInfo.totalItemsCount - 3
        }
    }
    
    val pullToRefreshState = rememberPullToRefreshState()
    
    LaunchedEffect(key1 = pullToRefreshState.isRefreshing, block = {
        if (pullToRefreshState.isRefreshing) {
            onRefresh()
            pullToRefreshState.endRefresh()
        }
    })
    
    LaunchedEffect(key1 = reachedBottom) {
        if (reachedBottom) onAdd()
    }
    
    Box(modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = lazyListState,
        ) {
            if (!pullToRefreshState.isRefreshing) {
                items(compliments) { compliment ->
                    Compliment(
                        complimentUiModel = compliment,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                
                item(key = compliments.size) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(12.dp)
                                .height(12.dp)
                                .align(Alignment.Center),
                        )
                    }
                }
            }
        }
        
        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}

private class ComplimentsProvider : PreviewParameterProvider<List<ComplimentUiModel>> {
    override val values = FAKE_COMPLIMENTS
}

@ThemePreview
@Composable
private fun ComplimentsPreview(@PreviewParameter(ComplimentsProvider::class) compliments: List<ComplimentUiModel>) {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            Compliments(
                compliments = compliments,
                onRefresh = {},
                onAdd = {},
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}