#!/bin/bash
# JBoss EAP 6.1 must be saved in /opt with correct permissions before running this script

all() {
  echo '*********************************************'
  echo '** installing Wicc development environment **'
  echo '*********************************************'
  echo ""
  echo "JBoss EAP 6.1 must be saved in /opt with correct permissions before running this script"
  echo ""
  setupParameters
  readInMySQLCredentials
  createdb
  readInGmailCredentials
  setupJbossStandalone  
  echo '******************************************************'
  echo '** installing Wicc development environment finished **'
  echo '******************************************************'
}

setupParameters() {

    JBOSS_HOME=/opt/jboss-eap-6.1
    JBOSS_CLI=$JBOSS_HOME'/bin/jboss-cli.sh -c'
    JBOSS_STANDALONE=$JBOSS_HOME/standalone/configuration/standalone.xml
    JBOSS_STANDALONE_BACKUP=$JBOSS_STANDALONE'.bak.'`date "+%Y%m%d%H%M%S"`
    JBOSS_DATASOURCE_NAME=java:jboss/wicc
    MYSQL_CONNECTOR_GROUPID=mysql
    MYSQL_CONNECTOR_VERSION=5.1.25
    MYSQL_CONNECTOR_NAME=mysql-connector-java
    MYSQL_CONNECTOR_PACKAGING=jar
    MYSQL_CONNECTOR_FULL_NAME=$MYSQL_CONNECTOR_NAME-$MYSQL_CONNECTOR_VERSION.$MYSQL_CONNECTOR_PACKAGING
    MYSQL_CONNECTOR_FULL_NAME_AND_PATH=$JBOSS_HOME/$MYSQL_CONNECTOR_FULL_NAME
    MYSQL_USER=root
    WICC_DB_NAME=timeouttool
    WICC_DB_USER=wicc
    WICC_DB_PASS=wicc
    EMAIL_ADDRESS="andrew.p.spratley@gmail.com"

    MAVEN_DEPENDENCY_PLUGIN_AND_GOAL=org.apache.maven.plugins:maven-dependency-plugin:2.8:copy

}

readInMySQLCredentials() {

    echo -n "Enter root password for MySQL: "

    read -s MYSQL_PASS

    echo "Processing..."
   	
}

readInGmailCredentials() {

    echo -n "Enter password for Gmail: "

    read -s EMAIL_PASS

    echo "Processing..."
   	
}

droptables() {

  mysqldump --user=$MYSQL_USER --password=$MYSQL_PASS --add-drop-table --no-data $WICC_DB_NAME | grep --regexp="FOREIGN_KEY_CHECKS\|^DROP" | mysql --user=$MYSQL_USER --password=$MYSQL_PASS $WICC_DB_NAME

}

createdb() {
  
  echo "Checking whether the database exists"

  db_exists=`mysql -u $MYSQL_USER -p$MYSQL_PASS -e "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '$WICC_DB_NAME'" | wc -c`
  
  if [ $db_exists = 0 ]; then

    echo "** Creating Wicc database $WICC_DB_NAME..."

    mysqladmin -u $MYSQL_USER -p$MYSQL_PASS create $WICC_DB_NAME

  else

    echo "** Wicc database $WICC_DB_NAME exists, drop tables..."
    echo -n 'Are you sure, that you want to drop tables? (y/n) '

    read do_drop

    if [ $do_drop = y ]; then

      droptables

    else

      echo 'The script has not dropped the tables'

    fi

  fi

  create_user=0
 
  echo "Checking whether the database user exists and has privileges"
 
  user_exists=`mysql -u $MYSQL_USER -p$MYSQL_PASS -e "SELECT user FROM mysql.user WHERE user = '$WICC_DB_USER'" | wc -c`
 
  if [ $user_exists = 0 ]; then
  
   create_user=1
  
  else

    user_has_privilage=`mysql -u $MYSQL_USER -p$MYSQL_PASS -e "SELECT User FROM mysql.db WHERE db = '$WICC_DB_NAME' AND user = '$WICC_DB_USER'" | wc -c`

    if [ $user_has_privilage = 0 ]; then

      create_user=1

    fi

  fi

  if [ $create_user = 1 ]; then

    echo "** Creating MySQL user $WICC_DB_USER..."

    mysql --user=$MYSQL_USER \
          --password=$MYSQL_PASS \
          -e "GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER, LOCK TABLES, CREATE TEMPORARY TABLES ON $WICC_DB_NAME.* TO '$WICC_DB_USER'@'localhost' IDENTIFIED BY '$WICC_DB_PASS';FLUSH PRIVILEGES;" $WICC_DB_NAME

  else

    echo 'The user already exists and has appropriate privileges'

  fi

}

setupJbossStandalone() {

    export JBOSS_HOME=$JBOSS_HOME

    # Backup standalone.xml
    cp $JBOSS_STANDALONE $JBOSS_STANDALONE_BACKUP

    # Place mysql connector in JBOSS_HOME
    mvn $MAVEN_DEPENDENCY_PLUGIN_AND_GOAL -Dartifact=$MYSQL_CONNECTOR_GROUPID:$MYSQL_CONNECTOR_NAME:$MYSQL_CONNECTOR_VERSION:$MYSQL_CONNECTOR_PACKAGING -DoutputDirectory=$JBOSS_HOME

    echo "Adding MySQL module"
    $JBOSS_CLI --command="module add --name=com.mysql --resources=$MYSQL_CONNECTOR_FULL_NAME_AND_PATH --dependencies=javax.api,javax.transaction.api"

    echo "Adding MySQL jdbc driver"
    $JBOSS_CLI --command='/subsystem=datasources/jdbc-driver=mysql:add(driver-module-name=com.mysql, driver-name=mysql, driver-class-name=com.mysql.jdbc.Driver)'

    echo "Adding datasource"
    $JBOSS_CLI --command='/subsystem=datasources/data-source="wicc":add(jndi-name="'$JBOSS_DATASOURCE_NAME'",driver-name="mysql",connection-url="jdbc:mysql://localhost/'$WICC_DB_NAME'",user-name="'$WICC_DB_USER'", password="'$WICC_DB_PASS'")'

    echo "Enabling datasource"
    $JBOSS_CLI --command='/subsystem=datasources/data-source=wicc:enable'

    echo "Testing datasource"
    $JBOSS_CLI --command='/subsystem=datasources/data-source=wicc:test-connection-in-pool'

    echo "Enabling descriptor property replacement"
    $JBOSS_CLI --command='/subsystem=ee:write-attribute(name="jboss-descriptor-property-replacement",value=true)'
    $JBOSS_CLI --command='/subsystem=ee:write-attribute(name="spec-descriptor-property-replacement",value=true)'

    echo "Setting system properties"
    $JBOSS_CLI --command='/system-property=web_xml_project_stage/:add(value=Development)'
    $JBOSS_CLI --command='/system-property=org.apache.deltaspike.ProjectStage/:add(value=Development)'
    
    echo "Adding email resource"
    $JBOSS_CLI --command='/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=mail-smtp-gmail:add(host=smtp.gmail.com,port=465)'
    $JBOSS_CLI --command='/subsystem=mail/mail-session=smtp-gmail:add(jndi-name="java:/mail/smtp-gmail", from="'$EMAIL_ADDRESS'")'
    $JBOSS_CLI --command='/subsystem=mail/mail-session=smtp-gmail/server=smtp:add(outbound-socket-binding-ref=mail-smtp-gmail,ssl=true,username='$EMAIL_ADDRESS',password='$EMAIL_PASS')'

    echo "Adding Security Realm"
    cp /home/andrew/.ssh/development-keystore.jks $JBOSS_HOME/standalone/configuration/development-keystore.jks

    $JBOSS_CLI --command='/subsystem=web/connector=http/:write-attribute(name=redirect-port,value=8443)'
    $JBOSS_CLI --command='/subsystem=web/connector=https/:add(socket-binding=https,scheme=https,protocol=HTTP/1.1,enable-lookups=false,secure=true)'
    $JBOSS_CLI --command='/subsystem=web/connector=https/ssl=configuration/:add(name=localhost-ssl,password=PPLErCys__JOPGkbnI2mpF0obBEl2g7f,protocol=TLSv1,key-alias=localhost,certificate-key-file="\${jboss.server.config.dir}/development-keystore.jks")'

    # Remove temporary copy of mysqlconnector
    rm $MYSQL_CONNECTOR_FULL_NAME_AND_PATH

}

all
