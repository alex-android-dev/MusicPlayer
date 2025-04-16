package com.example.musicplayer.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musicplayer.presentation.main.NavigationItem

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController
) {
    val items = listOf(NavigationItem.Api, NavigationItem.Downloaded)

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry = navHostController.currentBackStackEntryAsState().value

        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            // Функция размещает кнопки и дает возможность управлять навигацией
            NavigationBarItem(
                selected = item.screen.route == currentRoute,
                onClick = { navHostController.navigate(item.screen.route) },
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


//@Composable
//@Preview
//fun BottomNavigationBarPreview() {
//
//    BottomNavigationBar()
//
//}