########## build application ########

mvn clean install -DskipTests

########## Execute tests ############

mvn test

########### Run console application ########

java -jar target/calculator-1.0-SNAPSHOT.jar