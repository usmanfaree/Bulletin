# 📰 Bulletin - Modern Offline-First News App

A modern Android application built to demonstrate Clean Architecture and Modern Android Development (MAD) practices. The project showcases an Offline-First Architecture following Single Source of Truth (SSOT) principles with reactive data streams.

---

## 🚀 Key Features & Highlights

- **Offline-First Experience:** Seamless offline usability by caching news articles locally using Room Database.
- **Single Source of Truth (SSOT):** Reactive data pipeline using Kotlin Flow and emitAll to auto-synchronize the UI with database updates.
- **Reactive UI State Management:** UI reacts directly to StateFlow updates emitted by the ViewModel.
- **Network Resilience:** Graceful loading, success, and fallback error state handling with visual indicators.
- **Clean Navigation:** Smooth screen transitions using Android Jetpack Navigation components.

---

## 🏗️ Architecture & Tech Stack

The app adheres strictly to Google's Recommended Android Architecture Guide (Clean Architecture + MVVM).

[ UI Layer (Fragment / ViewBinding) ]
│  ▲ (StateFlow)
▼  │
[ ViewModel (UI Logic & Scope) ]
│  ▲ (Flow / Resource)
▼  │
[ Repository Layer (Offline-First Logic) ]
│                        │
▼                        ▼
[ Retrofit (Remote API) ]   [ Room Database (SSOT) ]

### Tech Stack Specifications

- **Language:** Kotlin 100%
- **Architecture:** MVVM (Model-View-ViewModel) + Repository Pattern
- **Dependency Injection:** Dagger Hilt
- **Async & Reactive Streams:** Kotlin Coroutines & Flow / StateFlow
- **Local Persistence:** Room Database
- **Networking:** Retrofit 2 + Gson Converter
- **UI Components:** ViewBinding, ConstraintLayout, RecyclerView (ListAdapter + DiffUtil), Jetpack Navigation

---

## 🛠️ Data Flow Pipeline

1. **Trigger:** NewsFragment requests data via NewsViewModel using lifecycleScope.
2. **Repository Flow:**
    - Emits Resource.Loading() state to show progress bar.
    - Fetches fresh data from Retrofit REST API.
    - Caches response inside Room DB.
    - Emits live updates to UI using emitAll(newsDao.getArticles()).
3. **Fallback:** If network request fails, cached data from Room DB is preserved and displayed alongside error states.

---

## ⚙️ Setup & Installation

1. Clone the repository
2. Open the project in Android Studio
3. Build and run the app on an Emulator or physical device