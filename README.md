# Basmos Food Ordering System

A JavaFX desktop application that simulates a real-world fast food ordering system with client ordering, admin management, secure login authentication, and receipt generation.

## Features

* Secure login authentication system (Admin and Client roles)
* Role-based access control (Admin / Client dashboards)
* Login attempt restriction (maximum of 3 tries before exit)
* Multiple meal selection using a checklist
* On-site and remote ordering options
* Delivery address input for remote orders
* Admin dashboard to view and manage orders
* Assign delivery personnel to orders
* Automatic total price calculation
* Receipt generation with formatted output
* Print receipt functionality

## Technologies Used

* Java
* JavaFX (GUI)
* Java Collections Framework (ArrayList, HashMap)
* Event-Driven Programming
* Object-Oriented Programming

## How It Works

* User logs in with authenticated credentials (Admin or Client)
* System validates login details and role selection
* Access is granted based on user role
* Client selects meals and chooses order type
* Order is placed and stored temporarily
* Admin views and confirms orders
* Total price is calculated automatically
* Receipt is generated and can be printed

## How to Run

* Make sure you have Java JDK 8 or higher installed
* Ensure JavaFX is properly configured (for JDK 11 and above)
* Open the project in NetBeans or any Java IDE
* Run the BasmosFood.java file
