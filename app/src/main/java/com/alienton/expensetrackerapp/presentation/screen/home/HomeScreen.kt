package com.alienton.expensetrackerapp.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alienton.expensetrackerapp.presentation.viewmodel.HomeViewModel
import com.alienton.expensetrackerapp.domain.model.Result
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val expenses by viewModel.expenses.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val totalExpenses by viewModel.totalExpenses.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expense Tracker") },
                actions = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_expense") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Expense")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Total Expenses", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "$%.2f".format(totalExpenses),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navController.navigate("analytics") }) {
                    Text("Analytics")
                }
                Button(onClick = { navController.navigate("budget") }) {
                    Text("Budget")
                }
            }

            when (expenses) {
                is Result.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is Result.Success -> {
                    val expenseList = (expenses as Result.Success).data
                    if (expenseList.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        ) {
                            Text("No expenses yet")
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(expenseList) { expense ->
                                ExpenseItem(expense, navController)
                            }
                        }
                    }
                }
                is Result.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text("Error loading expenses")
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseItem(
    expense: com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(expense.description, style = MaterialTheme.typography.bodyMedium)
                Text(
                    SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(expense.date),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text("$%.2f".format(expense.amount), style = MaterialTheme.typography.bodyMedium)
        }
    }
}