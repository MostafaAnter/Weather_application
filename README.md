# Weather App - Android Technical Task #

### [👉 Video Demo for this task on youtube 👈](https://youtu.be/S6ppgoK1-rM)

### Weather application
- [x] Created android screen that has search bar on top.
- [x] Detected current location of the customer.
- [x] Using https://openweathermap.org/api. Current weather data web service to provide all the necessary data.
- [x] All permissions is properly handled .
- [x] There are no restrictions on use of external libraries.

 <img src="https://github.com/MostafaAnter/Android_Technical_Task/blob/master/task.gif" width="300">

### Project Dependency Graph ###
- App module
  depend on core + feature search current weather + library location tracker : contains app start point it may be a splash screen.
- Core module
  depend on nothing  : contains all sharable data among different modules.
- Feature Search current weather Module
  depend on core + library location tracker : contain search weather feature related ui and other staffs.
- Library location tracker
  depend on core : contain code of getting current user location.

<img src="https://raw.githubusercontent.com/MostafaAnter/Weather_application/master/project.dot.png" width="300">

### Use domain specific langauge ###
- Make gradle scripts easy
- Support Kotlin for better dependencies management by Using Domain-Specific Languages.
- Support autocomplete

### Modular architecture ###
- Speeds up builds
- Enable on demand delivery
- Simplify development
- Reuse modules across apps
- Experiment with new technologies
- Scale development teams
- Enables refactoring
- Simplifies test automation

### MVVM Inside each module
- each developer inside android team has a free to select suitable architecture for his module.

### Dependency injection with Hilt ###
- Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in our project.

### Enable AppCenter Pipelines ###
- Support CI so i can check different stages like build and run unit test
  before merge any branch to master branch.

### Detekt ###
- to make code style analysis and be sure follow best practice for writing kotlin code.

### Firebase crashlytics ###
- to rack bugs even though app moved to production

### To avoid 64k functions problem and get smaller apk ###
- Enables code shrinking, obfuscation, and optimization for only
- Enables resource shrinking, which is performed by the

### Data binding
- The Data Binding Library automatically generates the classes required to bind the views in the layout with your data objects.

### leakCanary
- to detect memory leak through debugging process that will improve app performance as well.

### Truth For unit test its recommended from google
- write unit test for [Search Validator Class](https://github.com/MostafaAnter/Weather_application/blob/master/feature_search_current_weather/src/test/java/app/anter/feature_search_current_weather/ui/usecase/SearchValidatorTest.kt)

### Reactive programming (Coroutines)
Coroutines is Google recommended solution for asynchronous programming on Android. Noteworthy features include the following:

- Lightweight: You can run many coroutines on a single thread due to support for suspension, which doesn't block the thread where the coroutine is running. Suspending saves memory over blocking while supporting many concurrent operations.
- Fewer memory leaks: Use structured concurrency to run operations within a scope.
- Built-in cancellation support: Cancellation is propagated automatically through the running coroutine hierarchy.
- Jetpack integration: Many Jetpack libraries include extensions that provide full coroutines support. Some libraries also provide their own coroutine scope that you can use for structured concurrency.

### PermissionsDispatcher ###
PermissionsDispatcher provides a simple annotation-based API to handle runtime permissions.
- Fully Kotlin/Java support
- Special permissions support
- 100% reflection-free


### License
    Copyright [2021] [Mostafa Anter]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and limitations under the License.

