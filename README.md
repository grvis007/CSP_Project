1. Extract the folder CSP595Project and place it on your Tomcat webapps folder.

2. Set the classpath
set CLASSPATH=.;<TOMCAT_HOME>\webapps\CSP595Project\WEB-INF\lib\servlet-api.jar;<TOMCAT_HOME>\webapps\CSP595Project\WEB-INF\lib\mongo-java-driver-3.1.0-20150911.172057-81.jar;

3. To re-compile the application:
javac -d <TOMCAT_HOME>\webapps\CSP595Project\WEB-INF\classes <TOMCAT_HOME>\webapps\CSP595Project\src\com\csp595\model\*.java <TOMCAT_HOME>\webapps\CSP595Project\src\com\csp595\servlets\*.java <TOMCAT_HOME>\webapps\CSP595Project\src\com\csp595\beans\*.java

4. To run the application:

4.1. Start Tomcat and MongoDB

4.2.  Run the following command to restore the existing database:
<MONGODB_BIN_FOLDER>/mongorestore <TOMCAT_HOME>/webapps/CSP595Project/src/mongo_dump/ChicagoArtShop

4.3. Access the home page through http://localhost:8080/CSP595Project/


LOGIN INSTRUCTIONS
The existing database has a few users created:
STORE MANAGER: user = manager, password = 123 
SALESMAN: user = sales, password = 123
CUSTOMER: user = leona, password = 123
CUSTOMER: user = falsiane, password = 123
