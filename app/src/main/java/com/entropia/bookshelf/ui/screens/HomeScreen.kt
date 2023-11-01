package com.entropia.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.entropia.bookshelf.R
import com.entropia.bookshelf.model.Book
import com.entropia.bookshelf.util.parseUrl


@Composable
fun HomeScreen(
    retryAction: () -> Unit,
    bookshelfUiState: BookshelfUiState,
    modifier: Modifier
) {
    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier)
        is BookshelfUiState.Error -> ErrorScreen(retryAction = retryAction, modifier)
        is BookshelfUiState.Success -> BooksGridScreen(books = bookshelfUiState.books)
    }
}

@Composable
fun BooksGridScreen(
    books: List<Book>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding
    ) {
        items(items = books) { book ->
            BookCard(book = book)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCard(book: Book, modifier: Modifier = Modifier) {
    var showBookData by remember { mutableStateOf(book.imageLinks["thumbnail"] == null) }
    Card(modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        onClick = { showBookData = !showBookData }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(parseUrl(book.imageLinks["thumbnail"]))
                    .crossfade(true)
                    .build(), contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.no_cover_thumbnail),
                placeholder = painterResource(id = R.drawable.no_cover_thumbnail),
                modifier = Modifier.fillMaxWidth()
            )
            if (showBookData) {
                Card(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))) {
                    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))) {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = book.authors.toString().removeSurrounding("[", "]"),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

            }
        }


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
            iterations = LottieConstants.IterateForever,
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