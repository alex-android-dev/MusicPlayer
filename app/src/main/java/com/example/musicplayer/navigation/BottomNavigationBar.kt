package com.example.musicplayer.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navState: NavigationState,
) {
    val items = listOf(NavigationItem.Api, NavigationItem.Downloaded)

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry = navState.navHostController.currentBackStackEntryAsState().value

        /** Размещает кнопки и дает возможность управлять навигацией **/
        items.forEach { item ->

            /** Отслеживание роутов по иерархии **/
            val selected = navBackStackEntry?.destination?.hierarchy?.any {
                it.route == item.screen.route
            } ?: false

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) navState.navigateTo(item.screen.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(item.iconResourceId),
                        contentDescription = null,
                    )
                }
            )

        }

    }
}