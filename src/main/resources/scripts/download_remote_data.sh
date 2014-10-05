#! /bin/bash

OPENSHIFT_USER=adminXv7I7J4
OPENSHIFT_PWD=WsVwvN6VLPil
OPENSHIFT_PORT=64986

mysqldump --host=127.0.0.1 --port=$OPENSHIFT_PORT --user=$OPENSHIFT_USER --password=$OPENSHIFT_PWD timeouttool > data_dml.sql
