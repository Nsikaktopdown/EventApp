# Android Clean architecture + Paging Library + Kotlin Flow Boilerplate


This boilerplate with help Android developers understand how structure their projects in a clean way and also handling pagination with Paging Library from Jetpack.


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
* [PagingLibrary](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)


## `Screen Shots`
<table style="padding:10px">
  <tr>
    <td> 
         <img src="https://github.com/Nsikaktopdown/AndroidCleanBase/blob/master/screenshot/Screenshot_20210802_182345.png"  alt="1" width = 279px height = 540px ></td>
      
 <td><img src="https://github.com/Nsikaktopdown/AndroidCleanBase/blob/master/screenshot/Screenshot_20210802_182356.png" align="right" alt="2" width = 279px height = 540px></td>
  
   <!--<td><img src="./Scshot/trip_end.png" align="right" alt="4" width =  279px height = 496px></td>-->
  </tr>
 </table>


# Architecture

![diagram](https://github.com/Nsikaktopdown/AndroidCleanBase/blob/master/screenshot/diagram.png)

 Here's how the sample project implements it:

Clean architecture encourages separation of concerns, making the code loosely coupled. This results in a more testable and flexible code. This approach divides the project in 3 modules: presentation, data and domain.

### Presentation:
This is the User interface layer(Activities, Fragments and ViewModel) with the Android Framework, the MVVM pattern and the DI module. 

### Domain:  
This layer holds the business logic. Contains the use cases, in charge of calling the correct repository or data member.

### Data: 
This layer has the responsibility of selecting the proper data source for the domain layer. It contains the implementations of the repositories declared in the domain layer.
This includes: 
* ```Remote Datasource:```: Handles communications with the remote API(REST or GRAPHQL). In this project, it makes ann HTTP call using Retrofit Interface
* ```Cache Datasource``` : Handles communication with the local storage which is used to cache the events data locally
* ```EventPagedDatasource``` : This class manages the actual loading of data for your list from the remote datasource.  Since we have one key per page of Event API data extends ```PageKeyedDataSource``` from the paging library. 
Finally, the ```EventPagedDataSource``` will be created by its factory, ```AppPageDataSourceFactory``` which inherits of ```DataSource.Factory```

## Conclusion

 I will be glad to answer any questions and also accept pull request if you find any issue.

