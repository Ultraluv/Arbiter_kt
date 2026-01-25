package com.ultraluv.arbiter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ultraluv.arbiter.viewmodel.NavigationViewModel

@SuppressLint("ContextCastToActivity")
@Composable
fun AndroidBackHandler(navigationViewModel: NavigationViewModel) {
    val activity = LocalContext.current as? Activity
    androidx.activity.compose.BackHandler(
        enabled = true
    ) {
        Log.e("TAG", "AndroidBackHandler")
        if(navigationViewModel.canPop()){
            navigationViewModel.onBack()
        }else{
            val didPop = navigationViewModel.onBack()
            if (!didPop) {
                activity?.finish()
            }
        }
    }
}