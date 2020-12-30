## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE sra_dev;
CREATE DATABASE sra_prod;

#Create database service accounts
CREATE USER 'sra_dev_user'@'localhost' IDENTIFIED BY 'verycomplexpassword';
CREATE USER 'sra_prod_user'@'localhost' IDENTIFIED BY 'verycomplexpassword';
CREATE USER 'sra_dev_user'@'%' IDENTIFIED BY 'verycomplexpassword';
CREATE USER 'sra_prod_user'@'%' IDENTIFIED BY 'verycomplexpassword';

#Database grants
GRANT SELECT ON sra_dev.* to 'sra_dev_user'@'localhost';
GRANT INSERT ON sra_dev.* to 'sra_dev_user'@'localhost';
GRANT DELETE ON sra_dev.* to 'sra_dev_user'@'localhost';
GRANT UPDATE ON sra_dev.* to 'sra_dev_user'@'localhost';
GRANT SELECT ON sra_prod.* to 'sra_prod_user'@'localhost';
GRANT INSERT ON sra_prod.* to 'sra_prod_user'@'localhost';
GRANT DELETE ON sra_prod.* to 'sra_prod_user'@'localhost';
GRANT UPDATE ON sra_prod.* to 'sra_prod_user'@'localhost';
GRANT SELECT ON sra_dev.* to 'sra_dev_user'@'%';
GRANT INSERT ON sra_dev.* to 'sra_dev_user'@'%';
GRANT DELETE ON sra_dev.* to 'sra_dev_user'@'%';
GRANT UPDATE ON sra_dev.* to 'sra_dev_user'@'%';
GRANT SELECT ON sra_prod.* to 'sra_prod_user'@'%';
GRANT INSERT ON sra_prod.* to 'sra_prod_user'@'%';
GRANT DELETE ON sra_prod.* to 'sra_prod_user'@'%';
GRANT UPDATE ON sra_prod.* to 'sra_prod_user'@'%';