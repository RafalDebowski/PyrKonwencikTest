package com.example.pyrkonwenciknew.ui

import GuestListView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pyrkonwenciknew.R
import com.example.pyrkonwenciknew.ui.compose.NavigationType
import com.example.pyrkonwenciknew.ui.compose.error.ErrorView
import com.example.pyrkonwenciknew.ui.compose.guestDetails.GuestDetailsView
import com.example.pyrkonwenciknew.ui.compose.guestList.ProgressView
import com.example.pyrkonwenciknew.ui.theme.PyrKonwencikNewTheme
import com.example.pyrkonwenciknew.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PyrKonwencikNewTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(resources.getString(R.string.title))
                            }
                        )
                    }

                ) { innerPadding ->
                    AppNavHost(Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun AppNavHost(modifier: Modifier) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavigationType.GuestList.name,
            modifier = modifier
        ) {
            composable(route = NavigationType.GuestList.name) {
                GuestsView(navController, modifier)
                viewModel.getGuestsList()
            }
            composable(route = NavigationType.GuestDetails.name) {
                GuestView(navController)
            }
        }
    }

    @Composable
    fun GuestsView(navController: NavHostController, modifier: Modifier) {
        val listState = viewModel.state.collectAsState()
        when (val state = listState.value) {
            is MainViewModel.MainState.Loading -> {
                ProgressView(true, modifier)
            }

            is MainViewModel.MainState.Error -> {
                ErrorView(state.errorMessage.orEmpty(), modifier)
            }

            is MainViewModel.MainState.ListLoaded -> {
                GuestListView(state.guestsList) {
                    viewModel.setSelectedGuest(it)
                    navController.navigate(NavigationType.GuestDetails.name)
                }
            }
        }
    }

    @Composable
    fun GuestView(navController: NavController) {
        viewModel.getSelectedGuest()?.let {
            GuestDetailsView(
                item = it,
                navController = navController
            )
        }
    }
}