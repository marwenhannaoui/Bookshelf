package com.entropia.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.entropia.bookshelf.R


@Composable
fun BooksGridScreen(modifier: Modifier = Modifier, contentPadding: PaddingValues) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding
    ) {
        // TODO LazyGrid
    }
}

@Composable
fun BookCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        //TODO BookCard
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_anim))

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            iterations= LottieConstants.IterateForever,
            contentScale = ContentScale.Fit,
            modifier = Modifier
        )
        Text(
            text = stringResource(id = R.string.loading),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }

}


@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = stringResource(
                id = R.string.connection_error
            )
        )
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}


@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(retryAction = { })
}