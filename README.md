# Clean Code & Refactoring

### Requirements

- Maven: https://maven.apache.org/ 
- Java: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- IntelliJ Community Edition: https://www.jetbrains.com/idea/#chooseYourEdition
- Git: https://git-scm.com/

**Please try to create and build a hello world project in IntelliJ using Maven before the workshop! The less time spent fixing Java paths and SDK versions the better.**

### How to run code with Maven

**Ensure that:**

- Maven is on your PATH (typing `mvn --version` in a terminal should not produce an error)
- If Maven is _not_ on your PATH, you have to add it. 
    - Windows: `Control Panel -> Search "environment variables" -> "Edit environment variables for your account" -> find the PATH variable -> In the Value field add the directory where your mvn.exe is located`
   
With Maven ready, you can either run it from a terminal:

- Open a terminal and navigate to where the project's `POM.xml` file is located
- `mvn compile`
- `mvn test`

Or you can run it from within IntelliJ:

- In IntelliJ, open the folder where the `POM.xml` is located as a project
- Run from the Run menu