**Project Overview**
This project is written in Kotlin with clean Architecture includes architectural components like View Binding, LiveData, Navigation, ViewModel. Used MVVM architecture pattern. 

1. Clean Architecture -> It is used to determine the scalability, performance, security, code reduction etc.
2. MVVM -> Model-View-ViewModel. Separates presentation login(Views or UI) with business logic. Loosely coupled, increasing the testability. Has 1 to many relations with single viewmodel. Doesn't have the reference of View therefore overcomes the drawbacks of MVP and MVC.
3. View Binding -> It replaces findViewById making it easier to code that interacts with views. Enable it by added "viewBindind = true" in build gradle file.
4. LiveData -> It's an Observable data holder. Works on Observer Pattern and updates the view whenever there is change in data. Also, Activity lifecycle aware.
5. Navigation -> We used Jetpack Navigation component. Provides a consistent way to manage the Activity/Fragment. Uses visual graph to make the entire process easier, clearer and more consistent.
6. ViewModel -> It's a class that holds the state of UI. Encapsulate the business Logic and is Lifecycle aware.
7. Hilt -> For Dependency Injection.
8. RxJava -> For handling Asynchronous event and data stream in more concise w& declarative way. 

**How can I improve the project?**
By adding 
1. Unit testcases (JUnit, Mockito)
2. CI/CD (Continuous Integration/ Continuous Delivery) pipeline. CI -> Automatically build and test the app when changes are pushed, ensuring code quality and early bug detection. CD -> Automate app deployment to different environments (e.g., testing, production) for consistent and reliable releases.
3. UI Testcases (Espresso)
