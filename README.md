### Architecture Overview

## Android App (Client Side)

**Pattern: MVVM (Model-View-ViewModel)**
1. **Model:** `HighlightData`, `ApiService`, `RetrofitAnkiNetwork`
2. **View:** `MainActivity`, Composables (UI components)
3. **ViewModel:** (optional) Manages UI-related data and API calls

## Python Flask API (Server Side) https://github.com/meisei4/ankikindle

**Pattern: MVC with Services**
1. **Model:** SQLAlchemy models (e.g., `Lookup`)
2. **View:** Flask routes (API endpoints)
3. **Controller:** Route handlers
4. **Service:** `VocabHighlightService` (business logic)

### Interaction Flow

1. **User Interaction:** User highlights text in the Android app.
2. **API Request:** App sends highlight data to Flask API via Retrofit.
3. **API Processing:** Flask controller forwards request to service.
4. **Business Logic:** Service processes data, updates database, interacts with AnkiConnect.
5. **Response:** Flask API returns success/failure to the app.
6. **UI Update:** App updates UI based on API response.

### Summary

- **Client-Side (Android App):** MVVM pattern.
- **Server-Side (Flask API):** MVC pattern with Services.
- **Full-Stack Architecture:** Combines MVVM and MVC with Services for clear separation of concerns.
