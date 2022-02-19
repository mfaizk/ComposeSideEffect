package com.example.composesideeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope= rememberCoroutineScope()
            Scaffold(scaffoldState = scaffoldState) {
                //produceState can be used to make network call Asynchronously
                val counter = produceState(initialValue = 0){
                    delay(3000L)
                    value=4
                }
                if (counter.value%5==0&&counter.value>0){
                    LaunchedEffect(key1 = scaffoldState.snackbarHostState ){
                        scaffoldState.snackbarHostState.showSnackbar("Hello")

                    }
                }
                Button(onClick = {  }) {
                    Text(text = "Click Me : ${counter.value}")
                }
            }
        }
    }
}

var i = 0

//@Composable
//fun MyComposable(backPressedDispatcher: OnBackPressedDispatcher){
//
//    val callback= remember {
//        object:OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
////                TODO("Not yet implemented")
//            }
//        }
//    }
//
////    i++   this will cosider as sideeffect avoid using it here
////    this is better to use
////    it always run after sucessfull recomposition if composition fail it will not run
//    SideEffect {
//        i++
//    }
//    backPressedDispatcher.addCallback(callback)
//
//    DisposableEffect(key1 =backPressedDispatcher , effect = {
//        backPressedDispatcher.addCallback(callback)
//        onDispose {
//            callback.remove()
//        }
//    })
//
//
//    Button(onClick = {}) {
//        Text(text = "Click Me")
//
//    }
//}