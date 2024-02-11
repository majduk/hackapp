# Hackathon sample Java EE application

- Author: Michał Ajduk
- Level: Intermediate
- Technologies: JSP
- Target Product: EAP
- Base Source: <https://github.com/jboss-jdf/jboss-as-quickstart/>

What is it?
-----------

This quickstart is a deployable Maven 3 project to help you get your foot in the door developing with Java EE 7 on WildFly 8 or WildFly 8.

This project is setup to allow you to create a compliant Java EE 7 application using *JSP 2.0* *EL 2.0* *JSTL 1.2* *CDI 1.0*, *EJB 3.1*, *JPA 2.0* and Bean Validation 1.0.

System requirements
-------------------

All you need to build this project is Java 8.0 (Java SDK 1.8) or better, Maven 3.0 or better.

The application this project produces is designed to be run on WildFly 8.


Start WildFly 8 with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Configure target server:
```
   export WILDFLY_HOST=<hostname>
   export WILDFLY_PASSWORD=<password>, default is admin
```
3. Type this command to build and deploy the archive:
```
        mvn clean package wildfly:deploy
```
4. This will deploy `target/hackapp-*.war` to the running instance of the server.

Data source
---------------------
Application uses Hibernate with JTA source defined in [persistence.xml](src/main/java/resources/META-INF):
```
   <persistence-unit name="primary">
      <jta-data-source>java:/OracleDS</jta-data-source>
      <properties>
      ...
      </properties>
   </persistence-unit>
```
This managed data source needs to be provided by the application server.

Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc

