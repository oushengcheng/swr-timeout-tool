echo -n "Enter root password for MySQL: "
read -s password
echo "Processing..."

mysqldump --complete-insert \
          --lock-tables \
          --no-create-db \
          --user=root \
          --password=$password \
          timeouttool \
          ContingencyAlteration \
          ContingencyAlteration_alterations \
          ContingencyPlan \
          ContingencyPlan_ContingencyAlteration \
   > contingency_plans_ddl_and_dml.sql
