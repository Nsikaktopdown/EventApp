# Event App - Jetpack Compose


This is a simple event App using [TicketMasterApi](https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/) with pagination concepts using [PagingLibrary](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) with Jetpack Compose and Kotlin Coroutines + Flow. 


## Languages, libraries and tools used

* [TicketMasterApi](https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/)
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
         <img src="https://user-images.githubusercontent.com/16048595/183443976-92d511e2-6cb9-42c2-964e-e99061ea7412.png"  alt="1" width = 340px height = 500px ></td>
      
 <td><img src="https://user-images.githubusercontent.com/16048595/183444007-23bec7da-165b-4f89-a5df-2da3cb92bce2.png" align="right" alt="2" width = 340px height = 500px></td>
  
  <td><img src="https://user-images.githubusercontent.com/16048595/183444012-e8fcfee4-7146-4ca2-bc2b-2efd18c347fe.png" align="right" alt="2" width = 340px height = 500px></td>
  <td><img src="https://user-images.githubusercontent.com/16048595/183444021-0dcee1b3-3639-49bb-88f5-3b2591222a89.png" align="right" alt="2" width = 340px height = 500px></td>
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
* ```EventPagedDatasource``` : This class manages the actual loading of data for your list from the remote datasource.  Since we have one key per page of Event API data, we extend ```PageKeyedDataSource``` from the paging library. 
Finally, the ```EventPagedDataSource``` will be created by its factory, ```AppPageDataSourceFactory``` which inherits of ```DataSource.Factory```

## Conclusion

 I will be glad to answer any questions and also accept pull request if you find any issue.

