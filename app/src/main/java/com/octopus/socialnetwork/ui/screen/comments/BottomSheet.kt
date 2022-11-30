//package com.octopus.socialnetwork.ui.screen.comments
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
//
//@Preview
//@Composable
//fun BottomSheet() {
////    ConstraintLayout(
////        modifier = Modifier
////            .fillMaxWidth()
////            .height(400.dp)
////    ) {
////        val boxContainer = createRef()
////        val comments = createRef()
////        Box(modifier = Modifier
////            .fillMaxSize()
////            .background(
////                color = Color.White,
////                shape = AbsoluteRoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
////            )
////            .constrainAs(boxContainer) {
////                top.linkTo(boxContainer.top)
////                start.linkTo(boxContainer.start)
////                end.linkTo(boxContainer.end)
////            }) {
////            PostContent()
////         }
////
////    }
//
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .background(
//                color = Color.White,
//                shape = AbsoluteRoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
//            )
//    ) {
//        Column() {
//            Column(
//                modifier = Modifier
//                    .weight(.9f)
//                    .height(0.dp)
//                    .verticalScroll(rememberScrollState())
//            ) {
//                PostContent()
//            }
//            Column(
//                modifier = Modifier
//                    .weight(.1f)
//                    .height(0.dp)
//            ) {
//                Button(
//                    onClick = { /*TODO*/ }, modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                ) {
//
//                }
//            }
//
//        }
//    }
//}