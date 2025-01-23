package com.khun.smartstream.presentation.screens.videoPlayer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.khun.smartstream.presentation.common.LoadingIndicator
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

object TrailerVideoPlayerScreen {
    const val MovieIdBundleKey = "movieId"
}

@Composable
fun TrailerVideoPlayerScreen(
    onBackPressed: () -> Unit,
    trailerVideoPlayerScreenViewModel: TrailerVideoPlayerScreenViewModel = hiltViewModel()
) {
    val uiState by trailerVideoPlayerScreenViewModel.uiState.collectAsStateWithLifecycle()

    when (val s = uiState) {
        is TrailerVideoPlayerScreenUiState.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
        is TrailerVideoPlayerScreenUiState.Error -> {
            Error()
        }
        is TrailerVideoPlayerScreenUiState.Done -> {
            YouTubeVideoPlayer(
                videoId = s.videos.find { it.type == "Trailer" }?.key?:"", // Use YouTube video ID
                onBackPressed = onBackPressed
            )
        }
    }
}

@Composable
fun YouTubeVideoPlayer(videoId: String, onBackPressed: () -> Unit) {
    BackHandler(onBack = onBackPressed)

    AndroidView(
        factory = { context ->
            val view = YouTubePlayerView(context)
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer:
                                         YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                }
            )
            view
        },
        modifier = Modifier.fillMaxSize()
    )
}