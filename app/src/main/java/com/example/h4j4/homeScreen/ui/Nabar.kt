package com.example.h4j4.homeScreen.ui

import android.graphics.Paint.Align
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.h4j4.homeScreen.viewState.HomeScreenViewState
import javax.annotation.meta.When

@Composable
fun Navbar(uiState: HomeScreenViewState) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                width = 4.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            ),

        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,

        content = {

            Row(

                modifier = Modifier
                    .fillMaxHeight()
                    .width(78.dp),

                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,

                content = {

                    Row(

                        modifier = Modifier
                            .fillMaxHeight()
                            .width(30.dp),

                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,

                        content = {

                            Icon(
                                imageVector = Icons.Rounded.RestartAlt,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    )

                    Row(

                        modifier = Modifier
                            .fillMaxHeight()
                            .width(50.dp),

                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,

                        content = {

                            when (uiState) {
                                HomeScreenViewState.Loading -> {

                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(30.dp),

                                        strokeCap = StrokeCap.Round,
                                        color = Color.White
                                    )
                                }

                                is HomeScreenViewState.LoadedSuccessfully -> {

                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "${uiState.streak.streak}", color = Color.White)
                                }

                                HomeScreenViewState.LoadedUnsuccessfully -> {}
                            }
                        }
                    )
                }
            )


            IconButton(

                onClick = {},
                content = {

                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        }
    )
}