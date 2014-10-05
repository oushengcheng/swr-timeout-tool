#! /bin/bash

OPENSHIFT_USER=adminXv7I7J4
OPENSHIFT_PWD=WsVwvN6VLPil
OPENSHIFT_PORT=64986

echo "Run rhc port-forward first"
echo "Using Openshift MySQL username $OPENSHIFT_USER"
echo "Using Openshift MySQL password $OPENSHIFT_PWD"
echo "Using port for MySQL $OPENSHIFT_PORT"

mysql --host=127.0.0.1 --port=$OPENSHIFT_PORT --user=$OPENSHIFT_USER --password=$OPENSHIFT_PWD timeouttool < data_dml.sql
