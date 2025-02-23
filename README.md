# Java Project

## Overview
This project follows **Clean Architecture** and **MVVM** design patterns, ensuring scalability and maintainability. It utilizes **RxJava** for reactive programming, **Shimmer** for loading animations, and best practices for **ListAdapter**. The app features API integration, robust error handling, and search functionality.

## Screenshots && Video[
[https://drive.google.com/file/d/1ifra3ia0HFyHhNJRUoF2kozUbLmoF66X/view?usp=drive_link](https://drive.google.com/file/d/17DPWhumxZ-8cpuVcUe9s1U3tq8IL4YEC/view?usp=drive_link)
](https://drive.google.com/file/d/17DPWhumxZ-8cpuVcUe9s1U3tq8IL4YEC/view?usp=drive_link)
## Features
- **MVVM Architecture** for better separation of concerns
- **Clean Architecture** with domain, data, and presentation layers
- **RxJava** for asynchronous operations
- **Shimmer Effect** for loading states
- **API Integration** with Retrofit
- **ListAdapter** best practices for efficient RecyclerView updates
- **Error Handling** with proper exception management
- **Search Functionality** with LiveData

## Tech Stack
- **Language:** Java
- **UI:** XML
- **Architecture:** MVVM + Clean Architecture
- **Networking:** Retrofit
- **Reactive Programming:** RxJava
- **UI Enhancements:** Shimmer
- **Data Management:** Room Database (if applicable)
- **Dependency Injection:** Dagger/Hilt (if applicable)

## Project Structure
```
app/
│-- data/               # Data sources (API, Database, Repositories)
│-- domain/             # Use cases and business logic
│-- presentation/       # ViewModels and UI components
│-- di/                 # Dependency Injection setup
│-- utils/              # Utility classes (error handling, extensions)
```

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/mostafasamy999/Java-NY--Times.git
   ```
2. Open the project in **Android Studio**
3. Sync Gradle files and build the project
4. Run the app on an emulator or a physical device

## API Integration
- Retrofit is used for making API calls
- RxJava handles background operations
- Error handling is implemented using sealed classes

## Search Functionality
- Uses LiveData and RxJava to filter data efficiently
- Implements debounce to avoid unnecessary API calls

## Error Handling
- Global exception handling
- Proper error messages for network failures
- Retry mechanism for failed API requests


## Contact
For any queries, reach out to: **mostafasamy.dev@gmail.com**

