# Popular Movies App

## App Functionality

An App to help users discover and filter between Popular and Top Rated movies from the themoviedb.org web API. 

The App displays a scrolling grid of movie posters, when a movie is selected a details screen is launched, which provides the user with more information about the movie, and a few trailers they which can watch on youtube.

This app utilizes the themoviedb.org web API core Android user interface components and fetches movie information using .

- Kotlin
- Dagger 2 (For Dependency Injection)
- rxJava (For Managing Background Tasks)
- Retrofit (For Networking)
- JetPack
    - ViewModel (For managing UI related data in a lifecycle conscious way)
    - LiveData (For notifying views of data changes)
- Picasso (For displaying images)
- Architecture
    - Clean Architecture
    - MVVM
