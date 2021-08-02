# Android Clean architecture +  Kotlin Coroutine Boilerplate


Hey, I created this boilerplate to help Android developers understand how to implement clean architecture in thier projects and a clean way on how to adopt seperation of concerns.


## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* Android Support Libraries
* [Coroutine](https://developer.android.com/kotlin/coroutines)
* [Koin](https://insert-koin.io/)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Timber](https://github.com/JakeWharton/timber)
* [Mockito](http://site.mockito.org/)


# Architecture

 Here's how the sample project implements it:

Clean architecture encourages separation of concerns, making the code loosely coupled. This results in a more testable and flexible code. This approach divides the project in 3 modules: presentation, data and domain.

### Presentation:
This is the User interface layer(Activities, Fragments and ViewModel) with the Android Framework, the MVVM pattern and the DI module. Depends on domain to access the use cases and on di, to inject dependencies.


### Domain:  
This layer holds the business logic. Contains the use cases, in charge of calling the correct repository or data member.

### Data: 
This layer has the responsibility of selecting the proper data source for the domain layer. It contains the implementations of the repositories declared in the domain layer.
This includes: 
* ```Remote Datasource:```: Handles communications with the remote API(REST or GRAPHQL). In this project, it makes ann HTTP call using Retrofit Interface
* ```Cache Datasource``` : Handles communication with the local storage which is used to cache the evtn data locally

### Advantages of Using Clean Architecture:
* Your code will be easily testable.
* Your code is further decoupled (the biggest advantage.)
* The package structure is even easier to navigate.
* The project is even easier to maintain.
* Your team can add new features even more quickly.



## Conclusion

 I will be glad to answer any questions and also accept pull request if you find any issue.

