# Weather-App

## Installation
- Clone or download this project
- In your project's directory, create keystore.properties file, including this following line:
````properties
APPID = "YOUR_APPID"
````

- In your Android Studio, choose Build menu, click Make Project
- Run app

## Screenshot App
- Your first home app will be like this, favorite city is empty.
<img src="https://github.com/Sibaho/Weather-App/blob/master/img/home-empty.png" width="200" height="400">

- Click search icon on the top right appbar to search city to find out forecast, type your city then click search icon in your keyboard
<img src="https://github.com/Sibaho/Weather-App/blob/master/img/search.png" width="200" height="400">

- The search result will be like this:
<img src="https://github.com/Sibaho/Weather-App/blob/master/img/search-result.png" width="200" height="400">

- This is the display of the Home app if you add city to your favorite, you can find out the forcast of the city by clicking it
<img src="https://github.com/Sibaho/Weather-App/blob/master/img/home-with-favorite.png" width="200" height="400">

## Architecture

This application use MVVM (Model-View-ViewModel) pattern
<img src="https://github.com/Sibaho/Weather-App/blob/master/img/mvvm.png">


## Libraries
- Room
- RxJava
- Retrofit
- Hilt
- LiveData
- Android Material
