package org.creospace.compass.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import compass.composeapp.generated.resources.Res
import compass.composeapp.generated.resources.group_6
import compass.composeapp.generated.resources.image_group_6
import compass.composeapp.generated.resources.image_main
import compass.composeapp.generated.resources.image_main_1
import org.creospace.compass.data.Journal
import org.creospace.compass.presentation.Screens
import org.creospace.compass.presentation.components.ItemJournal
import org.creospace.compass.presentation.components.MainFloatingActionButton
import org.creospace.compass.presentation.components.Spacer
import org.creospace.compass.presentation.components.TextMedium
import org.creospace.compass.theming.primaryLight
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
    toCreateJournal: () -> Unit,
    toMeditation: () -> Unit,
    toDetail: (Journal) -> Unit
) {

    val journals by viewModel.journals.collectAsState()

    MainContent(
        journals = journals,
        toCreateJournal = toCreateJournal,
        toMeditation = toMeditation,
        toDetailJournal = { journal ->
            toDetail(journal)
        }
    )

}

@Composable
private fun MainContent(
    journals: List<Journal>,
    toCreateJournal: () -> Unit,
    toMeditation: () -> Unit,
    toDetailJournal: (Journal) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            MainFloatingActionButton(
                toMeditation = toMeditation,
                toCreateJournal = toCreateJournal
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            TextMedium(
                text = "My Journals",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 24
            )
            if (journals.isNotEmpty()) {
                JournalList(
                    journals = journals,
                    toDetailJournal = toDetailJournal
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.image_group_6),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun JournalList(
    journals: List<Journal>,
    toDetailJournal: (Journal) -> Unit
) {
    LazyColumn {
        items(journals.size) { index ->
            val journal = journals[index]
            ItemJournal(
                title = journal.title.orEmpty(),
                description = journal.description.orEmpty(),
                toDetail = { toDetailJournal(journal) }
            )
        }
    }
}