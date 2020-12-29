
# Create DBs
CREATE DATABASE sra_dev;
CREATE DATABASE sra_prod;

# Create DB service accounts

CREATE USER 'sra_dev_user'@'localhost' IDENTIFIED BY 'verycomplexpassword';
CREATE USER 'sra_prod_user'@'localhost' IDENTIFIED BY 'verycomplexpassword';

# DB Grants

GRANT SELECT ON sra_dev.* to 'sra_dev_user'@'localhost';
GRANT UPDATE ON sra_dev.* to 'sra_dev_user'@'localhost';
GRANT INSERT ON sra_dev.* to 'sra_dev_user'@'localhost';
GRANT DELETE ON sra_dev.* to 'sra_dev_user'@'localhost';

GRANT SELECT ON sra_prod.* to 'sra_prod_user'@'localhost';
GRANT UPDATE ON sra_prod.* to 'sra_prod_user'@'localhost';
GRANT INSERT ON sra_prod.* to 'sra_prod_user'@'localhost';
GRANT DELETE ON sra_prod.* to 'sra_prod_user'@'localhost';