#!/bin/bash
export JBOSS_HOME='/opt/jboss-eap-6.1'

mysqlconnector=mysql-connector-java-5.1.25-bin.jar
jbosscliconnect='/opt/jboss-eap-6.1/bin/jboss-cli.sh -c'

MYSQL_CONNECTOR_VERSION=5.1.25
MYSQL_CONNECTOR_NAME=mysql-connector-java
MYSQL_CONNECTOR_FULL_NAME=$MYSQL_CONNECTOR_NAME-$MYSQL_CONNECTOR_VERSION-bin.jar
DEPENDENCY_PLUGIN_VERSION=2.8
MAVEN_DEPENDENCY_PLUGIN=org.apache.maven.plugins:maven-dependency-plugin:2.8

#
# Read in username & password
#
# Add mysql user
# mysqladmin create timeouttool
# mysql -uroot -prootpw -e "GRANT ALL PRIVILEGES ON dbname.* TO username@hostname IDENTIFIED BY 'userpassword'"
# FLush priveliges

# Place mysql connector to JBOSS_HOME
mvn $MAVEN_DEPENDENCY_PLUGIN:copy -Dartifact=mysql:mysql-connector-java:5.1.25:jar -DoutputDirectory=$JBOSS_HOME

echo "Adding MySQL module"
$jbosscliconnect --command="module add --name=com.mysql --resources=$JBOSS_HOME/$mysqlconnector --dependencies=javax.api,javax.transaction.api"

echo "Adding MySQL jdbc driver"
$jbosscliconnect --command='/subsystem=datasources/jdbc-driver=mysql:add(driver-module-name=com.mysql, driver-name=mysql, driver-class-name=com.mysql.jdbc.Driver)'
 
echo "Adding datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source="wicc":add(jndi-name="java:jboss/wicc", driver-name="mysql", connection-url="jdbc:mysql://localhost/timeouttool", user-name="wicc", password="wicc")'

echo "Enabling datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source=wicc:enable'

echo "Testing datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source=wicc:test-connection-in-pool'

# Remove temporary copy of mysqlconnector
rm "$JBOSS_HOME/$mysqlconnector"
