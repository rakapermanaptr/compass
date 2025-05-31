package org.creospace.compass.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.creospace.compass.presentation.create_journal.CreateJournalScreen
import org.creospace.compass.presentation.detail_journal.DetailJournalScreen
import org.creospace.compass.presentation.edit_journal.EditJournalScreen
import org.creospace.compass.presentation.main.MainScreen
import org.creospace.compass.presentation.music.MusicScreen

@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.Main.route,
    ) {
        composable(route = Screens.Main.route) {
            MainScreen(
                toCreateJournal = {
                    navController.navigate(Screens.CreateJournal.route)
                },
                toMeditation = {
                    navController.navigate(Screens.MusicScreen.route)
                },
                toDetail = {
                    navController.navigate(Screens.DetailJournal(journalId = it.id))
                },
            )
        }
        composable(route = Screens.CreateJournal.route) {
            CreateJournalScreen(navController = navController)
        }
        composable<Screens.DetailJournal> { backStackEntry ->
            val journalId: Screens.DetailJournal = backStackEntry.toRoute()
            DetailJournalScreen(
                navController = navController,
                id = journalId.journalId,
                toEdit = {
                    navController.navigate(Screens.EditJournal(journalId = it.id))
                }
            )
        }
        composable<Screens.EditJournal>{ backStackEntry ->
            val journalId: Screens.EditJournal = backStackEntry.toRoute()
            EditJournalScreen(
                navController = navController,
                id = journalId.journalId
            )
        }
        composable(route = Screens.MusicScreen.route) {
            MusicScreen(navController = navController)
        }
    }
}