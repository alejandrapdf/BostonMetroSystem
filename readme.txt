# Boston Metro System

## Overview
The **Boston Metro System** is a project designed to provide commuters with an efficient and user-friendly transportation solution. The project aims to create both a front-end user interface and a back-end system that allows users to find the fastest route between two points within the Boston metro system. The application will display metro stations, routes, and the most efficient paths to reach a desired destination.

## Key Features
- **Fastest Route Calculation**: The application helps users find the quickest route between two metro stations, factoring in transit time and transfer points.
- **Interactive Map**: Visual representation of the metro stations, routes, and connections to aid users in navigating the system.
- **User-Friendly Interface**: Simple, intuitive design for easy interaction and clear route information.
- **Real-Time Updates**: Displays real-time information on train schedules and delays, ensuring users can make informed decisions about their travel.

## Design and Development

### Front-End Design:
- **Clean, Minimalist UI**: The interface has been designed to be visually simple, with a focus on ease of use for both experienced and new commuters.
- **Route Search Functionality**: Users can input starting and destination stations, and the system will display the fastest available route.
- **Interactive Map**: Users can see metro stations and routes laid out in an easy-to-navigate format.

### Back-End Development:
- **Route Optimization Algorithm**: The back-end system uses an algorithm to calculate the fastest and most efficient route between two stations, considering factors like train schedules and transfer times.
- **Database of Metro Stations**: The system uses an up-to-date database containing information about the metro stations, routes, and connections across Boston's transit system.
- **Real-Time Data Integration**: The back-end syncs with live metro data to provide users with accurate information on train availability, delays, and route changes.

## Future Development
- **Mobile App Integration**: Future plans include creating a mobile version of the application for users on the go.
- **Expanded Metro Data**: Adding support for more routes, stations, and other modes of public transportation (e.g., buses, ferries).
- **User Personalization**: Allow users to save favorite routes, receive route recommendations, and set up notifications for their daily commute.

## Run Project Instructions
~~~~To run the code using the main method in IntelliJ 
1) Extract zip file "CS308-Boston.zip"
2) Open project in IntelliJ (File->Open->select "CS308-Boston" folder)
3) Load Maven build script (bottom right of screen)
4) When prompted to by IntelliJ, choose to trust the project
5) Assign SDK to the project (File->Project Structure->Project->SDK)
6) Run "MGraphDriver" (main) class

~~~~To run the jar files in IntelliJ
1) Download a JFX SDK of your choice (we used openjfx https://openjfx.io/)
2) extract to a location of your choice
3) add the location of the lib forlder as a 'VM option' in IntelliJ run/debug configurations
		(eg. --module-path "${PATH_TO_FX}" --add-modules javafx.controls,javafx.fxml 
		where "${PATH_TO_FX}" is the path to the lib folder of the JFX SDK)
4) Run either of the jar files in the project 
		CS308-Boston\out\artifacts\CS308_Boston_jar\CS308-Boston.jar 
		or
		CS308-Boston\target\CS308-Boston-1.0-SNAPSHOT.jar
## Conclusion
The **Boston Metro System** project is designed to improve the commuter experience by providing a reliable, fast, and easy-to-use system to navigate the metro network. By offering the fastest routes, real-time updates, and an intuitive interface, the project aims to make commuting in Boston more efficient and less stressful.		
