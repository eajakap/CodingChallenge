# CodingChallenge: City Connections
Checks for the cities that are connected in the road network
 - City Connection Service – read the cities from the file 
 – “CityConnect.txt."
The file has entries as under –
###### cat CityConnect.txt 
        • Boston, New York
        • Philadelphia, Newark
        • Newark, Boston
        • Trenton, Albany
        • Boston,Chicago
        • New York,Princeton
        • Trenton, New York
### Build the program: 
 -         mvn clean install
###	Run the Spring Boot Application
  -        java jar target/cityconnect-0.0.1-SNAPSHOT.jar
###	Test the spring boot application
  #### Go to the Web browser and execute the following commands:
  ######  Check Connections:
        •	http://localhost:8080/
        •	http://localhost:8080/connected?origin=Newark&destination=Boston
  ###### List Connections
        •	http://localhost:8080/list?origin=Newark&destination=Boston
        •	http://localhost:8080/list?&destination=Boston
        •	http://localhost:8080/list?origin=Newark
        •	http://localhost:8080/list
