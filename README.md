# codeFellowship
-To Start the application run the command ./gradlew bootRun in the terminal and navigate to http://localhost8080:

## Routes

- /: This displays the homepage where there are links present to login or signup with redirects to their respective pages. 
- If the signup is successful it will redirect you to a password protected page with information about the fellowship
- If you need to signup you will be redirected to a signup page where you need to enter your first name, username and password
- Users can make posts to their own profile. In addition, they can see all of their friends on their profile page. A user can visit another user's page, but they are not able to edit their friend's posts. 
- This application requires a local installation of PostgreSQL. Once configuration for your environment is complete, a file called application.properties should be created in your /resources folder. This will look like this:

spring.datasource.url=jdbc:postgresql://localhost:5432/?user=<user>&password=<user>
#spring.datatsource.username=
#spring.datasource.password=

#//spring.jpa.hibernate.dll-auto=create //creates and drops tables everytime you run your app
# UPDATE means check current STATE of DB, updates what it needs to
# NONE, CREATE-DROP,
spring.jpa.hibernate.dll-auto=update
spring.jpa.generate-ddl=true```
where user and password are for your local Postgres setup. Please also be sure to use a JDBC-compliant connection string like the one shown above. Optionally, you can provide your username and password in the spring.datasource.username and spring.datasource.password properties, respectively.
