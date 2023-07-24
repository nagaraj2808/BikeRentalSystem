# BikeRentalSystem
The bike rental system is an application that allows users to rent bikes from a fleet 
of available bikes. The system is designed to have three types of users - regular 
users, administrators, and a database. Regular users can register, login, rent bikes 
for a specified duration, return bikes, and view their rental history. Administrators 
are responsible for managing the bike fleet, which includes adding or removing 
bikes from the system. The database stores information about the bike fleet, rental 
history, and user data.
The system uses a class diagram to illustrate the relationship between different 
classes in the application. The class diagram incorporates design patterns such as 
Singleton, Factory Method, and Observer to improve the design and functionality 
of the application.

This application is designed using following design patterns
  * Signleton pattern : used to keep only one instance of database throughout the application
  * Factory pattern: used to create objects of different kinds of bike
  * Decorator pattern: used to add accessories to rental

The database includes following tables:
  * admin
  * user
  * rental
  * bike

## Usecase modelling
![image](https://github.com/nagaraj2808/BikeRentalSystem/assets/79707183/ce0b101b-03e6-40d8-bf02-2d222aafa7cf)

## Class modelling
![image](https://github.com/nagaraj2808/BikeRentalSystem/assets/79707183/1bba2c9c-b527-4b3f-8238-64496ee2ab78)

## Activity modelling
![image](https://github.com/nagaraj2808/BikeRentalSystem/assets/79707183/6004a6da-8154-4be1-9e72-e7f249d8996d)


