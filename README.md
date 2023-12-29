# News Hive App

News Hive is an Android application built using Kotlin and Jetpack Compose, following clean architecture principles, modularization, and various modern Android development technologies.

## Architecture

The project is structured using Clean Architecture, separating concerns into layers:

- **Data:** Contains the remote and local module data sources, as well as the repository implementations.

- **Domain:** Contains the entities and use cases module representing the business logic.

- **Presentation:** Consists of the UI and ViewModel module.

## Features

### Home Screen

### Search Screen

### Discover Screen

### Favorites

## Screenshots and Video

![image1](https://github.com/Abdallahx3x/NewsHive/assets/83548062/661082c4-a299-4b24-a567-81dba9cbc4f7)
![image2](https://github.com/Abdallahx3x/NewsHive/assets/83548062/6448ab85-f0c2-4222-8d63-90644b4c5089)
![image3](https://github.com/Abdallahx3x/NewsHive/assets/83548062/3a05e3e8-0523-4fed-9375-0fdf59bd6a21)
![image4](https://github.com/Abdallahx3x/NewsHive/assets/83548062/07018609-90d8-4f1f-802b-82268e2e23cf)
![image5](https://github.com/Abdallahx3x/NewsHive/assets/83548062/037541ef-c13f-458b-b6f9-a7928b0b8bd1)
![image6](https://github.com/Abdallahx3x/NewsHive/assets/83548062/feb5c0fe-73bc-45b7-9731-9e7904b3aa57)
![image7](https://github.com/Abdallahx3x/NewsHive/assets/83548062/101d3789-6736-465c-8e94-db469cf06e3d)
![image8](https://github.com/Abdallahx3x/NewsHive/assets/83548062/1a24dd6f-90f0-4386-842a-df9fa144a710)
![image9](https://github.com/Abdallahx3x/NewsHive/assets/83548062/d2e70ef5-4bd1-45f6-8f50-dc4a963601b7)

## Technologies Used

- **MVVM Architecture:** Utilizing the Model-View-ViewModel architecture pattern for a clean separation of concerns.
- **Paging:** Implementing pagination for efficient data loading.
- **Retrofit:** Networking library for handling API calls.
- **Room Database:** Local storage solution for caching and offline capabilities.
- **Coroutines:** Asynchronous programming for managing background tasks.
- **Kotlin Flow:** Reactive programming for handling streams of data.
- **Dependency Injection:** Utilizing a dependency injection framework (e.g., Dagger, Koin) for managing object creation and injection.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Obtain an API key from [MediaStack](https://mediastack.com/).
4. Insert the API key in the appropriate configuration file.
5. Build and run the application on an emulator or physical device.

## License

This project is open source and released under the [MIT License](LICENSE).
