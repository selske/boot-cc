sudo apt-get update

sudo apt-get -y mysql-server mysql-client

sudo su -

echo 'bind-address=0.0.0.0' >> /etc/mysql/conf.d/mysql.cnf

mysql --password=root -e "CREATE USER 'kevin'@'%' IDENTIFIED BY 'kevin_pwd'"
mysql -u root --password=root -e "GRANT ALL PRIVILEGES ON *.* TO 'kevin'@'%' WITH GRANT OPTION;FLUSH PRIVILEGES;"
mysql --password=root -e "CREATE DATABASE springboot"

exit
