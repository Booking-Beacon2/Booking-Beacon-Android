package com.example.booking_beacon.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booking_beacon.enums.UserType
import com.example.booking_beacon.screen.LoginScreen
import com.example.booking_beacon.screen.RegisterNormalUserScreen
import com.example.booking_beacon.screen.SelectLoginTypeScreen

enum class NavRoute(val routeName: String, val description: String) {
    SelectLoginTypeScreen(
        "SelectLoginTypeScreen",
        description = "로그인 타입 선택 화면"
    ),
    LoginScreen("LoginScreen", description = "로그인 화면"), RegisterScreen(
        "RegisterScreen",
        description = "회원가입 화면"
    )

}

class RouteAction(navHostController: NavHostController) {

    val navTo: (String) -> Unit = { route ->
        navHostController.navigate(route)
    }

    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }
}

@Composable
fun NavigationGraph(startRoute: NavRoute = NavRoute.SelectLoginTypeScreen) {

    //네비게이션 컨트롤러
    val navController = rememberNavController()

    val routeAction = remember(navController) { RouteAction(navController) }

    NavHost(navController, startRoute.routeName) {
        composable(NavRoute.SelectLoginTypeScreen.routeName) {
            SelectLoginTypeScreen(routeAction)
        }
        composable(
            "${NavRoute.LoginScreen.routeName}/{type}",
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { entry ->
            val userType = entry.arguments?.getString("type") ?: UserType.USER.name
            LoginScreen(routeAction, UserType.valueOf(userType))
        }
        composable(
            "${NavRoute.RegisterScreen.routeName}/{type}",
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { entry ->
            val userType = entry.arguments?.getString("type") ?: UserType.USER.name

            RegisterNormalUserScreen(routeAction = routeAction, UserType.valueOf(userType))
        }

    }
}