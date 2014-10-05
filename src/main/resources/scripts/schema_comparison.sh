#!/bin/bash

echo -n "Enter root password for MySQL: "
read -s password
echo "Processing..."

# Rename start database name to start_timeouttool
sed 's/CREATE DATABASE  IF NOT EXISTS `timeouttool`/CREATE DATABASE  IF NOT EXISTS `start_timeouttool`/g' startschema.sql > startschematemp.sql
sed -i 's/USE `timeouttool`;/USE `start_timeouttool`;/g' startschematemp.sql

# Rename end database name to end_timeouttool
sed 's/CREATE DATABASE  IF NOT EXISTS `timeouttool`/CREATE DATABASE  IF NOT EXISTS `end_timeouttool`/g' endschema.sql > endschematemp.sql
sed -i 's/USE `timeouttool`;/USE `end_timeouttool`;/g' endschematemp.sql

# Import schema to mysql
mysql --user=root --password=$password < startschematemp.sql
mysql --user=root --password=$password < endschematemp.sql

# Calculate differences
mysqldiff --difftype=sql --force --server1=root:$password@localhost start_timeouttool:end_timeouttool > diff.sql

# Clean up temporary files
rm -rf startschematemp.sql
rm -rf endschematemp.sql

# Clean up database
mysqladmin --user=root --password=$password drop start_timeouttool
mysqladmin --user=root --password=$password drop end_timeouttool
