# Bulletin News App (Android)

Android news application built using MVVM architecture.  
The app fetches latest news from a REST API and displays them in a list with offline caching support using Room Database.

---

## Features

- Fetch news from REST API (Retrofit)
- Display news in RecyclerView
- News detail screen
- Offline caching using Room Database
- MVVM architecture (ViewModel + Repository)
- Loading, success, and error state handling
- Unit testing for ViewModel

---

## Tech Stack

- Kotlin
- MVVM Architecture
- Retrofit
- Room Database
- RecyclerView (ListAdapter + DiffUtil)
- LiveData & ViewModel
- Glide
- JUnit + MockK (Testing)

---

## Architecture Flow

UI (Fragment)
→ ViewModel
→ Repository
→ Retrofit / Room

---

## Offline Support

If API fails, cached data from Room database is shown.

---

## Note

This project was built for learning Android development and practicing real-world architecture patterns.
