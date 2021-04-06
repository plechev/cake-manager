## Running the Cake Manager application

### Standalone

This starts only the Spring Boot application accessible at http://localhost:8282. 

    ./gradlew bootRun 

### Dockerized

This will build docker image of the application and use Docker Compose to bootstrap environment with the Cake Manager app 
plus pre-configured FusionAuth oAuth server. 
  
    ./gradlew composeUp

to stop:

    ./gradlew composeDown 

or (removing all containers):

    ./gradlew composeDownForced


Once the Docker containers are up and running the Cake Manager application is accessible on http://localhost:8282. 
The separate FusionAuth server is accessible on http://localhost:9011

## Authenticating 

There are two options to authenticate the user:

#### GitHub 
Use your own GitHub account to login and gain access to Cake Manager.
     
#### FusionAuth (built-in)
Use the following:
    
- email: _user@waracle.co_
- password: _password_

**Notes**: 
 - The built-in FusionAuth server is only available when running the application in Dockerised mode as explained above.
 - Further details on FusionAuth setup available in file `fusionauth/kickstart/kickstart-development.json`   

## Containerisation 

The Cake Manager app can be packaged as Docker image and ready to be included in CI/CD pipelines. 
The Docker commands can all be executed via Gradle tasks: 

 - build: `dockerBuildImage`
 - run Dockerised app (e.g. locally): `dockerRun` (also cleans and builds fresh Docker image)
 - stop Dockerised app: `dockerStop`

TODO: externalise relevant packaging parameters as environment variables, typically controlled via CI/CD  

