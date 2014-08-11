#!/bin/bash
export JBOSS_HOME='/opt/jboss-eap-6.1'

jbosscliconnect='/opt/jboss-eap-6.1/bin/jboss-cli.sh -c'

MYSQL_CONNECTOR_GROUPID=mysql
MYSQL_CONNECTOR_VERSION=5.1.25
MYSQL_CONNECTOR_NAME=mysql-connector-java
MYSQL_CONNECTOR_PACKAGING=jar
MYSQL_CONNECTOR_FULL_NAME=$MYSQL_CONNECTOR_NAME-$MYSQL_CONNECTOR_VERSION.$MYSQL_CONNECTOR_PACKAGING
MYSQL_CONNECTOR_FULL_NAME_AND_PATH=$JBOSS_HOME/$MYSQL_CONNECTOR_FULL_NAME
DEPENDENCY_PLUGIN_VERSION=2.8
MAVEN_DEPENDENCY_PLUGIN_AND_GOAL=org.apache.maven.plugins:maven-dependency-plugin:2.8:copy

#
# Read in username & password
#
# Add mysql user
# mysqladmin create timeouttool
# mysql -uroot -prootpw -e "GRANT ALL PRIVILEGES ON dbname.* TO username@hostname IDENTIFIED BY 'userpassword'"
# FLush priveliges

# Place mysql connector in JBOSS_HOME
mvn $MAVEN_DEPENDENCY_PLUGIN_AND_GOAL -Dartifact=$MYSQL_CONNECTOR_GROUPID:$MYSQL_CONNECTOR_NAME:$MYSQL_CONNECTOR_VERSION:$MYSQL_CONNECTOR_PACKAGING -DoutputDirectory=$JBOSS_HOME

echo "Adding MySQL module"
$jbosscliconnect --command="module add --name=com.mysql --resources=$MYSQL_CONNECTOR_FULL_NAME_AND_PATH --dependencies=javax.api,javax.transaction.api"

echo "Adding MySQL jdbc driver"
$jbosscliconnect --command='/subsystem=datasources/jdbc-driver=mysql:add(driver-module-name=com.mysql, driver-name=mysql, driver-class-name=com.mysql.jdbc.Driver)'
 
echo "Adding datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source="wicc":add(jndi-name="java:jboss/wicc", driver-name="mysql", connection-url="jdbc:mysql://localhost/timeouttool", user-name="wicc", password="wicc")'

echo "Enabling datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source=wicc:enable'

echo "Testing datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source=wicc:test-connection-in-pool'

# Remove temporary copy of mysqlconnector
rm $MYSQL_CONNECTOR_FULL_NAME_AND_PATH

