# Expense Tracker Android App

A comprehensive Android expense tracking application built with Kotlin, Jetpack Compose, and modern Android architecture patterns.

## 🎯 Features

### Core Features
- ✅ Add, edit, and delete expenses
- ✅ Categorize expenses with custom categories
- ✅ Track expenses by date range
- ✅ View total expenses summary
- ✅ Support multiple payment methods (Cash, Card, Transfer)

### Advanced Features
- 📊 Analytics and visual reports
- 💰 Budget management and alerts
- 🏷️ Tag expenses for better organization
- 📍 Location tracking for expenses
- 🔄 Recurring expense management
- 🔔 Reminder notifications
- 💳 Multiple payment method support
- 📸 Receipt attachment
- 📄 Export reports to PDF
- 🌙 Dark mode support
- 🗑️ Soft delete for data recovery
- 🌐 Offline-first architecture

## 🏗️ Architecture

The app follows Clean Architecture with MVVM pattern:

```
Presentation Layer (UI) → ViewModel → Use Cases → Repository → Data Layer
```

### Technology Stack
- **UI Framework**: Jetpack Compose
- **Database**: Room
- **Dependency Injection**: Dagger Hilt
- **Networking**: Retrofit
- **Asynchronous**: Coroutines
- **Navigation**: Jetpack Navigation
- **Charts**: MPAndroidChart
- **Data Persistence**: DataStore

## 📁 Project Structure

```
src/main/java/com/alienton/expensetrackerapp/
├── data/
│   ├── local/
│   │   ├── db/
│   │   ├── dao/
│   │   └── entity/
│   └── repository/
├── domain/
│   ├── model/
│   └── usecase/
├── presentation/
│   ├── screen/
│   ├── theme/
│   ├── viewmodel/
│   └── navigation/
└── di/
```

## 🚀 Setup Instructions

### Clone the Repository
```bash
git clone https://github.com/AlienTony888/expense-tracker-app.git
cd expense-tracker-app
```

### Open in Android Studio
1. Open Android Studio
2. Click **File** → **Open**
3. Select the cloned `expense-tracker-app` folder
4. Let Gradle sync automatically
5. Wait for indexing to complete

### Run the App
1. Connect an Android device (API 24+) or start an emulator
2. Click the **Run** button (or press Shift + F10)
3. Select your device
4. The app will build and launch

## 🧪 Testing

The app includes:
- Unit tests for ViewModels and UseCases
- UI tests for Composable screens
- Repository tests with mocking

Run tests:
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

## 🛡️ Error Handling

The app implements comprehensive error handling:
- Try-catch blocks in all critical operations
- Result<T> sealed class for safe error propagation
- User-friendly error messages
- Network error handling
- Database transaction rollback on failure
- Validation for all user inputs

## 📋 Scenarios Covered

### Normal Scenarios
- Adding new expenses
- Viewing expense list
- Filtering by category and date
- Updating existing expenses
- Deleting expenses

### Edge Cases
- Empty expense list
- Invalid amount (negative, zero, non-numeric)
- Empty description
- Missing category selection
- Network failures
- Database corruption
- Concurrent modifications
- Date range validation

### Performance Scenarios
- Large number of expenses (1000+)
- Memory efficiency
- Database query optimization
- UI responsiveness

### Security Scenarios
- Data encryption
- Input validation
- SQL injection prevention (Room handles this)
- Safe serialization

## 🔮 Future Enhancements

- Cloud sync with Firebase
- Multi-user support
- Advanced analytics with graphs
- Bill splitting feature
- OCR for receipt scanning
- Machine learning for expense categorization
- Export to CSV/Excel
- Multi-currency support
- Offline sync

## 📦 Dependencies

- Jetpack Compose 1.5.4
- Room 2.6.0
- Dagger Hilt 2.47
- Retrofit 2.9.0
- Coroutines 1.7.3
- Navigation Compose 2.7.5
- MPAndroidChart
- DataStore

## 📄 License

MIT License

## 👤 Author

AlienTony888

## 💬 Support

For issues or questions, please create an issue in the repository.
