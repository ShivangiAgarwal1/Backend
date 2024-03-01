
## Prerequisites & Installations

- Java Development Kit (JDK) 17 :
  You can install JDK from this link [https://www.oracle.com/ca-en/java/technologies/downloads/#java17/]

  To check if successfully installed , in terminal check java --version

  If you face any issues in configuring Jdk in Intellij, you may want to add path to JAVA_HOME as mentioned in this link[https://www.baeldung.com/install-maven-on-windows-linux-mac]

- Maven for building (version 3.x or newer)

  You can install maven  from this link [https://maven.apache.org/]

  Please check the steps for installing and configuring maven from this link[https://www.baeldung.com/install-maven-on-windows-linux-mac]

- To install via homebrew

  For macOS (using Homebrew):
  # Install OpenJDK 11
  brew install openjdk@11

  # Verify Java installation
  java -version

  # Install Maven 3.3+
  brew install maven

  # Verify Maven installation
  mvn -version


- Intellij IDE for running Spring Boot project
  You can install Intellij IDE by following instructions from [https://www.jetbrains.com/help/idea/installation-guide.html]


## Project Cloning

1. Clone the repository: https://github.com/ShivangiAgarwal1/Backend
2. The database slack_clone.db (same as available in python porject) is included in the project.

## Running the application
    1.Open IntelliJ IDEA:
    2.Open the cloned project:

        Go to File > Open or click on "Open" on the welcome screen.

        Navigate to the directory where you cloned your Spring Boot project and select the project or pom.xml from the project root folder. Click "Open" to load the project into IntelliJ IDEA.

   

    3.Configure JDK in IntelliJ IDEA:
        
        Go to File > Project Structure.
        
        Under Project, ensure that the Project SDK is set to JDK 17. If it's not, click on "New" and navigate to the directory where JDK 17 is installed and select it.

    4.Configure Maven in IntelliJ IDEA:
        
        IntelliJ IDEA should automatically detect the Maven installation(If yes, you can skip this step). 
        
        If not, you may need to specify the path to the Maven executable:
        
            Go to File > Settings (or IntelliJ IDEA > Preferences on macOS).
        
            Expand "Build, Execution, Deployment" and click on "Build Tools" and then "Maven".
        
            Set the "Maven home directory" to the location where Maven is installed.

    5.Build the project:
        
        Go to terminal in Intellij and build the project via this command: `mvn clean install`
    
        IntelliJ IDEA will start indexing and downloading dependencies automatically. You can monitor the progress in the bottom-right corner.

        After the indexing is complete, ensure that there are no errors in the project structure. Any issues with dependencies or configurations will be highlighted.

    6.Run the Spring Boot application:
        
        Locate the main class of the Spring Boot application (File name: BackendApplication, annotated with @SpringBootApplication).
        
        Right-click on the main class and select "Run BackendApplication" from the context menu.

        IntelliJ IDEA will start the Spring Boot application, and you can view the application's output in the Run tool window.


        Alternatively you can run the application using the below command in terminal. Make sure you have maven already installed

            cd Backend
            mvn spring-boot:run
        
    7.Access the application at `http://localhost:8080/api/posts` in a web browser.


## Swagger Documentation

    You can use Swagger UI to view and interact with the API endpoints.

        -Swagger UI is available at `http://localhost:8080/swagger-ui.html`
