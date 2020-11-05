# CMS
A Java Swing Based University Management System With Mysql
# configuration
Configure myConnection.java in CMS/Src/cms update it with your credentials
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename", "username", "password");
# database setup
Create Scheme ums in mysql workbench or using similiar utility.
Select the database as default and then populate it with uml.sql
# To login into system
Provide student id and password to login e.g
username: 1
password: 1595477
  Cheers you have a working University/student management system
# Miscellaneous
  u may need to import a library to mysql-connector-java to connect to database
