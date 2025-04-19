package com.example.presentation.presentation.main

/*
@Composable
fun PlayerScreenTest(context: Context) {

    val viewModel: TrackListViewModel = viewModel()
    val screenState = viewModel.screenState.collectAsState(TrackListScreenState.Initial)
    AppMusicPlayer.initPLayer(context)

    when (val state = screenState.value) {
        is TrackListScreenState.Tracks -> {

            LaunchedEffect(true) {
                val serviceIntent = Intent(context, PlaybackService::class.java)
                context.startService(serviceIntent)
            }


            Log.d("PlayerScreenTest", "state is Tracks")
//            AppMusicPlayer.playTrackList(state.trackList)

            AndroidView(factory = {
                PlayerView(context).apply {
//                    this.player = AppMusicPlayer.getPlayer()
                }
            })

            DisposableEffect(Unit) {
                onDispose {
//                    AppMusicPlayer.releasePlayer()
                }
            }
        }

        else -> {}
    }

}

//private fun askPermission() {
//    if (ContextCompat.checkSelfPermission(
//            this,
//            Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION
//        ) != Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION
//    )
//}



 */