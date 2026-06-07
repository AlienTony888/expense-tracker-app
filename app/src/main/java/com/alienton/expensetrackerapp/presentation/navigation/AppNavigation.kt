package com.alienton.expensetrackerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alienton.expensetrackerapp.presentation.screen.home.HomeScreen
import com.alienton.expensetrackerapp.presentation.screen.addexpense.AddExpenseScreen
import com.alienton.expensetrackerapp.presentation.screen.expense_detail.ExpenseDetailScreen
import com.alienton.expensetrackerapp.presentation.screen.analytics.AnalyticsScreen
import com.alienton.expensetrackerapp.presentation.screen.budget.BudgetScreen
import com.alienton.expensetrackerapp.presentation.screen.settings.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("add_expense") {
            AddExpenseScreen(navController)
        }
        composable(
            "expense_detail/{expenseId}",
            arguments = listOf(navArgument("expenseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val expenseId = backStackEntry.arguments?.getString("expenseId") ?: ""
            ExpenseDetailScreen(navController, expenseId)
        }
        composable("analytics") {
            AnalyticsScreen(navController)
        }
        composable("budget") {
            BudgetScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}