# Kitchensink - Spring Boot Application <br>
The application is migration from Jakarta EE 10 web-enabled database application using JSF, CDI, EJB, JPA, and Bean Validation.<br>
Application Source - https://github.com/jboss-developer/jboss-eap-quickstarts/tree/8.0.x/kitchensink
---
## System Requirements<br>
The application is designed to work on JDK 21.0+ versions<br>
All you need to build this project is **Java 21.0 (Java JDK 21)** or later and **Maven 3.9.1** or later.<br>
---
Additionally, the application is connected to MongoDB database to saved data. Make sure you have MongoDB installed.<br>
To download MongoCompass follow https://www.mongodb.com/products/tools/compass <br>
---
The application will try to connect via port **27017**, Incase the MongoDB server run on any other port <br>make sure to change the configuration in **application.properties** as well.

The application is designed to run on both servers JBOSS and Tomcat.<br>
To run the application on JBOSS make sure you have the config files present in WEB-APP folder<br>
**beans.xml**<br>
**faces-config.xml**<br>
**Also make sure you have `*-ds.xml` for database configuration**<br>
To run the application on Tomcat server nothing will change.

---

To run the application on JBOSS make sure to uncomment the pom.xml dependencies exclusions.<br> These exclusions are necessary as when the application gets deployed on JBOSS server, 
these dependencies get conflict with JBOSS dependencies .<br> To run it on tomcat no change needs to be made<br>
## Building and running the quickstart application with a Tomcat Server
To build the application go to command prompt Project location and run<br>
`mvn clean install`<br>
This will install all the dependencies and post that the application war will get generated in target folder<br>
Once that is done, we can run the application from target folder by running below command<br>
`java -jar kitchensink-migration-0.0.1-SNAPSHOT.war`<br>
This will run the application on 8080 port<br>

The application can be accessed via localhost:8080<br>
The application has Spring Security via BasicAuth. To access the application use any of the below user details<br>
1. Username: **user**, password: **password**, roles: USER
2. username: **admin**,password: **admin**, roles: USER,ADMIN

To define any other roles add the credentials in SecurityConfig.java<br>

