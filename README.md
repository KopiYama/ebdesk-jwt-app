# ebdesk app
The "ebdesk app" is a REST API developed using Spring Boot. It provides functionalities for user authentication, including endpoints for user registration, login, and accessing protected resources.

## Requirements

- **Java**: The application requires Java 11 or later to run.
- **Maven**: Maven is used as the build system and dependency manager.
- **MySQL**: MySQL is used as the relational database to store user and authentication data.

## How to Run

### Running the Application
1. Ensure MySQL is running.
2. Open the application on your IDE. Then click on `application.properties`. Enter this configuration:
   ````bash
   spring.datasource.url=jdbc:mysql://localhost:3306/"enter_your_database_name"
   spring.datasource.username="enter_your_database_username"
   spring.datasource.password="enter_your_database_password"
   spring.jpa.hibernate.ddl-auto=update
   app.jwtSecret=v7Kq4CdIzlUcvOG9zLO30+g3y2et9kF/IP3RM4kz9y3IvP2z0sa4t6FornIe9hX62Hwz/HTJz+RKvMSjbkTT7w==
   app.jwtExpirationMs=86400000
   
   ````
   Note : Enter your database name, username of database, password of database.
3. Running applications
  - Open a terminal or command prompt
  - Run the following command to start the application:
    ````bash
    mvn spring-boot:run
   
    ````
    Wait until the application is fully running and listening on the default port (usually port 8080)

### Testing with Postman
1. Open Postman and make a new requst.
    - set metode to `POST`
    - Enter the registration endpoint URL:
    ````bash
    http://localhost:8080/api/v1/auth/registration
   
    ````
    - On the Body tab, select raw and format JSON like this:
    ````bash
    {
    "username": "newuser",
    "password": "password123",
    "email" : "email123@example.com
    }
    ````
    - Click Send and pay attention to the response. You should receive confirmation that the user is registered
2. Login to Get JWT Tokens.
   - Change the method to POST.
   - Use login URL: `http://localhost:8080/api/v1/auth/login`.
   - In Body, enter:
   ````bash
    {
    "username": "newuser",
    "password": "password123"
    }
   ````
   - Click Send.
   - Copy the JWT token from the given response
3. Access Protected Resources.
   - Create a new request using the GET method
   - Enter the URL for the protected resource : `http://localhost:8080/api/v1/auth/resource`
   - On the Headers tab, enter Authorization in the key column and enter Bearer <token> in the value column, replace <token> with the JWT token you got from the login step.
   - Click Send and pay attention to the response. If the token is valid, you will get access to protected resources.
  
## My Decision and Challenge
### Design Decisions
During the development of this application, I opted to use Spring Boot for its extensive support in building REST APIs and its ability to manage complex dependencies seamlessly. For authentication, Spring Security was integrated to handle secure login and JWT token issuance.

### Library Choices
- Spring Boot: For simplifying backend development.
- Spring Security: For robust security and authentication.
- JJWT: For JWT creation and validation.

### Challenges Faced
- Configuring JWT correctly was a challenge, particularly ensuring that tokens were handled securely.
- Generating a secure jwtSecret for the application.properties required understanding of JWT and security practices.
- Implementing the token retrieval and ensuring that the GET /resource endpoint correctly utilized the token for authentication were significant hurdles that were eventually overcome through detailed debugging and community support.
