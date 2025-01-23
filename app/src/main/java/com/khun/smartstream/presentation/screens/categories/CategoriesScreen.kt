package com.khun.smartstream.presentation.screens.categories

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.khun.smartstream.domain.mapper.Categories
import com.khun.smartstream.presentation.common.LoadingIndicator
import com.khun.smartstream.presentation.common.MovieCard
import com.khun.smartstream.presentation.screens.dashboard.rememberChildPadding
import com.khun.smartstream.presentation.utils.GradientBg

@Composable
fun CategoriesScreen(
    gridColumns: Int = 4,
    onCategoryClick: (categoryId: Int) -> Unit,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
    categoriesScreenViewModel: CategoriesScreenViewModel = hiltViewModel()
) {

    val uiState by categoriesScreenViewModel.uiState.collectAsStateWithLifecycle()

    when (val s = uiState) {
        CategoriesScreenUiState.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }

        is CategoriesScreenUiState.Ready -> {
            Catalog(
                gridColumns = gridColumns,
                movieCategories = s.categoryList,
                onCategoryClick = onCategoryClick,
                onScroll = onScroll,
                modifier = Modifier.fillMaxSize()
            )
        }

        is CategoriesScreenUiState.Error -> {
            Error()
        }
    }
}

@Composable
private fun Catalog(
    movieCategories: Categories,
    modifier: Modifier = Modifier,
    gridColumns: Int = 4,
    onCategoryClick: (categoryId: Int) -> Unit,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
) {
    val childPadding = rememberChildPadding()
    val lazyGridState = rememberLazyGridState()
    val shouldShowTopBar by remember {
        derivedStateOf {
            lazyGridState.firstVisibleItemIndex == 0 &&
                    lazyGridState.firstVisibleItemScrollOffset < 100
        }
    }
    LaunchedEffect(shouldShowTopBar) {
        onScroll(shouldShowTopBar)
    }

    AnimatedContent(
        targetState = movieCategories,
        modifier = Modifier
            .padding(horizontal = childPadding.start)
            .padding(top = childPadding.top),
        label = "",
    ) { it ->
        LazyVerticalGrid(
            state = lazyGridState,
            modifier = modifier,
            columns = GridCells.Fixed(gridColumns),
        ) {
            itemsIndexed(it) { index, movieCategory ->
                var isFocused by remember { mutableStateOf(false) }
                MovieCard(
                    onClick = {
                        onCategoryClick(movieCategory.id!!.toInt())
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(16 / 9f)
                        .onFocusChanged {
                            isFocused = it.isFocused || it.hasFocus
                        }
                        .focusProperties {
                            if (index % gridColumns == 0) {
                                left = FocusRequester.Cancel
                            }
                        }
                ) {
                    val itemAlpha by animateFloatAsState(
                        targetValue = if (isFocused) .6f else 0.2f,
                        label = ""
                    )
                    val textColor = if (isFocused) Color.White else Color.White

                    Box(contentAlignment = Alignment.Center) {
                        Box(modifier = Modifier.alpha(itemAlpha)) {
                            GradientBg()
                        }
                        Text(
                            text = movieCategory.name ?: "",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = textColor,
                            )
                        )
                    }
                }
            }
        }
    }
}
