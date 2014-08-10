#!/bin/bash

# Rename start database name to start_timeouttool
sed 's/CREATE DATABASE  IF NOT EXISTS `timeouttool`/CREATE DATABASE  IF NOT EXISTS `start_timeouttool`/g' startschema.sql > startschematemp.sql
sed -i 's/USE `timeouttool`;/USE `start_timeouttool`;/g' startschematemp.sql

# Rename end database name to end_timeouttool
sed 's/CREATE DATABASE  IF NOT EXISTS `timeouttool`/CREATE DATABASE  IF NOT EXISTS `end_timeouttool`/g' endschema.sql > endschematemp.sql
sed -i 's/USE `timeouttool`;/USE `end_timeouttool`;/g' endschematemp.sql

# Import schema to mysql
echo "Enter root password for MySQL:"
mysql -u root -p < startschematemp.sql

echo "Enter root password for MySQL:"
mysql -u root -p < endschematemp.sql

# Calculate differences
mysqldiff --difftype=sql --force --server1=root:monster@localhost start_timeouttool:end_timeouttool > diff.sql

# Clean up temporary files
rm -rf startschematemp.sql
rm -rf endschematemp.sql

# Clean up database
mysqladmin -u root -p drop start_timeouttool
mysqladmin -u root -p drop end_timeouttool
