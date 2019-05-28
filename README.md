# **REST Contact service**

[![Build Status](https://travis-ci.com/OlegKliuchnyk/RestContactService.svg?branch=master)](https://travis-ci.com/OlegKliuchnyk/RestContactService)

## Install

* Install and run PostgreSQL (I use version 11)
* Create a database named `"postgres"` using SQL-command `CREATE DATABASE postgres;`
* Create a table named `"contacts"` using SQL-command from file `create_table.sql` which placed in the root directory of this project
* Set database credentials in the `application.properties` file (database URL, port, username, and password)
* Set property `contact.db.total_items` in the `application.yml` file for specifying total entries which you want be insert into database
* Set property `contact.total.items.per_page` in the `application.yml` file for specifying total items per page will be returned to the user for one request
* Run spring boot application and wait while all data will be inserted to the DB
* Send a request to a REST-service via a web browser or using Postman. Requests have to be in the following format:
  * http://localhost:8080/hello/contacts?nameFilter=^A.*$
  * http://localhost:8080/hello/contacts?nameFilter=^A.*$&page=1
  * http://localhost:8080/hello/contacts?nameFilter=^A.*$&page=1&size=200 
  
Developed in Intellij IDEA 