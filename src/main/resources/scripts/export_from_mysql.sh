echo -n "Enter root password for MySQL: "
read -s password
echo "Processing..."

mysqldump -d --user=root --password=$password timeouttool > schema_ddl.sql
mysqldump --complete-insert --lock-tables --no-create-db --no-create-info --user=root --password=$password timeouttool > data_dml.sql
