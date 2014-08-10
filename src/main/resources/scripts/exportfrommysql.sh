mysqldump -d -u root -p timeouttool > schema_ddl.sql
mysqldump --complete-insert --lock-tables --no-create-db --no-create-info -u root -p timeouttool > data_dml.sql
