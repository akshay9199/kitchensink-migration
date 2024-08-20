# Kitchensink - Spring Boot Application

This project is a migration from a Jakarta EE 10 web-enabled database application utilizing JSF, CDI, EJB, JPA, and Bean Validation.

**Application Source:** [Kitchensink - JBoss EAP Quickstarts](https://github.com/jboss-developer/jboss-eap-quickstarts/tree/8.0.x/kitchensink)

---

## System Requirements

To build and run this project, ensure you have the following:

- **Java JDK 21.0** or later
- **Maven 3.9.1** or later

Additionally, the application connects to a MongoDB database for data storage. Ensure MongoDB is installed on your system.

- **Default MongoDB Port:** `27017`  
  If your MongoDB server runs on a different port, update the configuration in the `application.properties` file accordingly.

To download MongoDB Compass, visit: [MongoDB Compass](https://www.mongodb.com/products/tools/compass)

---

## Application Deployment

The application can run on both JBOSS and Tomcat servers. Specific configurations are required for each server:

### JBOSS Server

- Ensure the following configuration files are present in the `WEB-APP` folder:
  - `beans.xml`
  - `faces-config.xml`
  - `*-ds.xml` (Database configuration)

- Uncomment the necessary exclusions in the `pom.xml` file. These exclusions prevent dependency conflicts when deploying on JBOSS.

### Tomcat Server

No additional configuration is required. The application can be run as-is.

---

## Building and Running the Application on a Tomcat Server

1. **Build the Application:**
   - Navigate to the project directory in your command prompt.
   - Run the following command to clean and build the project:
     ```sh
     mvn clean install
     ```
   - This command installs all dependencies and generates a WAR file in the `target` folder.

2. **Run the Application:**
   - Navigate to the `target` folder.
   - Run the following command to start the application:
     ```sh
     java -jar kitchensink-migration-0.0.1-SNAPSHOT.war
     ```
   - The application will run on port `8080`.

3. **Access the Application:**
   - Open your browser and go to: `http://localhost:8080`
   
4. **Authentication:**
   - The application is secured with Spring Security using Basic Authentication. Use the following credentials:
     - **User:** `user`, **Password:** `password`, **Roles:** USER
     - **User:** `admin`, **Password:** `admin`, **Roles:** USER, ADMIN
   - To define additional roles, modify the `SecurityConfig.java` file.

