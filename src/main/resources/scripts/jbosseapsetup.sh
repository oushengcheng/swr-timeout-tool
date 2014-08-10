#!/bin/bash
jbosscliconnect="/opt/jboss-eap-6.1/bin/jboss-cli.sh -c"

echo "Adding datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source="wicc":add(jndi-name="java:jboss/wicc", driver-name="mysql", connection-url="jdbc:mysql://localhost/timeouttool", user-name="wicc",password="wicc")'

echo "Enabling datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source=wicc:enable'

echo "Testing datasource"
$jbosscliconnect --command='/subsystem=datasources/data-source=wicc:test-connection-in-pool'


