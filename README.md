Gradle Migrations Plugin [![Build Status](https://travis-ci.org/marceloemanoel/gradle-migrations-plugin.png?branch=master)](https://travis-ci.org/marceloemanoel/gradle-migrations-plugin?branch=master)
=============================

Provide gradle build integration with [mybatis migrations](https://code.google.com/p/mybatis/wiki/Migration). 
The plugin adds a group of tasks named **Migrations** composed by:

<table width="100%">
  <thead>
    <th>
      Task
    </th>
    <th>
      Description
    </th>
    <th>
      Parameters
    </th>
  </thead>
  <tbody>
    <tr>
      <td><a href="#migrateinit">migrateInit</a></td>
      <td>Create migrations repository structure</td>
      <td>-</td>
    </tr>
    <tr>
      <td><a href="#migratebootstrap">migrateBootstrap</a></td>
      <td>Use an existing database structure as an starting point for migrations</td>
      <td>-</td>
    </tr>
    <tr>
      <td><a href="#migratestatus">migrateStatus</a></td>
      <td>Shows current database status</td>
      <td>-</td>
    </tr>
    <tr>
      <td><a href="#migratenew">migrateNew</a></td>
      <td>Create a new migration file.</td>
      <td>description, template</td>
    </tr>
    <tr>
      <td><a href="#migrateup-and-migratedown">migrateUp</a></td>
      <td>Apply any pending migration following creation order.</td>
      <td>steps</td>
    </tr>
    <tr>
      <td><a href="#migrateup-and-migratedown">migrateDown</a></td>
      <td>Rewinds the database to a previous stage.</td>
      <td>steps</td>
    </tr>
  </tbody>
</table>

Install
=======

Just put the following code snippet in your build.gradle file.

```groovy
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath "com.github.marceloemanoel:gradle-migrations:0.2.1"
  }
}

apply plugin: "migrations"
```

Configurations
==============

The only **required** configuration is made on your scripts dependencies section.
The plugin add a configuration scope known as `migrationsDriver`. As its name states,
this configuration scope should contain all JDBC drivers that will be used by your migrations.

To use the migrations with a mysql database for example, one should instruct the build as following:

```groovy
dependencies {
  migrationsDriver 'mysql:mysql-connector-java:5+'
}
```

This will ensure that the last mysql connector will be downloaded when you first run any migration task,
it will be cached by gradle and on later calls it will be used from the local repository.

Customization
=============

Following the rule of convention over configuration the plugin adds a configuration closure to your build.
If all default values are good for you, you're ready to go. But if you need to change anything, you'll see that 
it is an easy task. You only need to write the following on your `build.gradle` file: 

```groovy
migrations {
   baseDir = "anotherDirectory"
   environment = "test"
   force = true
}
```

All values are optional. The following table presents the default values of each property:

<table width="100%">
  <thead>
    <th>Property</th>
    <th>Description</th>
    <th>Default Value</th>
  </thead>
  <tbody>
    <tr>
      <td>baseDir</td>
      <td>Defines the base directory containing the migrations</td>
      <td>migrations</td>
    </tr>
    <tr>
      <td>environment</td>
      <td>Defines the database environment used by the tasks</td>
      <td>development</td>
    </tr>
    <tr>
      <td>force</td>
      <td>Forces the execution of the tasks</td>
      <td>false</td>
    </tr>
  </tbody>
</table>

Available Tasks
===============

migrateInit
-----------
The init command initializes a new repository’ of migration scripts. You’ll run init once to create the
workspace in which everything you need to manage database change will be placed. Running this
command will create the directory specified by the `baseDir` property (which is the directory "migrations"
by default). In case it already exists the command will fail unless the property `force` is true. 

When the command is completed, the directory will contain the following sub-directories:

> environments

In the environments folder you will find .properties files that represent your database instances. By
default a `development.properties` file is created for you to configure your development time database
properties. You can also create `test.properties`, `production.properties` or even a `anything.properties` file. 
The environment can be specified when running a migration by using the `environment` property 
without the path or ".properties" part (the default value is "development").

> scripts

This directory contains your migration SQL files. These are the files that contain your DDL to both
upgrade and downgrade your database structure. By default, the directory will contain the script to
create the changelog table, plus one empty example migration script. To create a new migration script,
use the [migrateNew](#migratenew) command. To run all pending migrations in order, use the [migrateUp](#migrateup) 
command. To undo the last migration applied, use the [migrateDown](#migratedown) command etc.

migrateBootstrap
----------------
The bootstrap command enables the starting point for migrations if you’re working from an existing database.
There’s no point in trying to rewind time and shoehorn your existing database into a series of migration scripts. 
It’s more practical to just accept the current state of your database schema and identify this as the starting point.
In the scripts directory you’ll find a file named `bootstrap.sql`. You can put your existing DDL script in this file. 
If you don’t have a DDL script, you can export your existing database schema and put it in the bootstrap file. 
You’ll want to clean it up so that it doesn’t contain anything specific to any one environment, but otherwise almost 
any script should work. Watch out for DDL that contains conditional elements or branching logic that could generate 
multiple schemas. While this is sometimes necessary, it’s a really good idea to try to eliminate this aspect of your
database schema (put such conditional and branching logic in your code or stored procedures instead).
If you have multiple DDL files, you’ll have to merge them into the single bootstrap file. But worry not,
it’s the last time you’ll ever modify it. 

In order to run, the [migrateBootstrap command](#migratebootstrap) need the JDBC driver of your database.
The plugin add the configuration `migrationsDriver` to your project. More information [here](#configurations).

migrateStatus
-------------
The status command will report the current state of the database. 

<pre>
:migrateStatus
ID             Applied At          Description
================================================================================
20130314124532    ...pending...    create changelog
20130314124533    ...pending...    first migration
</pre>

migrateNew
----------
This task enable the creation of new migrations. Every migration script file should have a .sql extension 
and have both sections **DO** and **UNDO**. As soon you init the migrations repository two files are 
created for you, the changelog migration and the first migration. The changelog migration contains 
the following code:

```sql
--// Create Changelog

-- Default DDL for changelog table that will keep
-- a record of the migrations that have been run.

-- You can modify this to suit your database before
-- running your first migration.

-- Be sure that ID and DESCRIPTION fields exist in
-- BigInteger and String compatible fields respectively.

CREATE TABLE ${changelog} (
ID NUMERIC(20,0) NOT NULL,
APPLIED_AT VARCHAR(25) NOT NULL,
DESCRIPTION VARCHAR(255) NOT NULL
);

ALTER TABLE ${changelog}
ADD CONSTRAINT PK_${changelog}
PRIMARY KEY (id);

--//@UNDO

DROP TABLE ${changelog};
```

This file is composed of three sections:

* Description: Delimited by `--//`, used to describe the migration in the database.

```sql
--// Create Changelog
```

* DO: Everything after the description, used to push forward the database

```sql

-- Default DDL for changelog table that will keep
-- a record of the migrations that have been run.

-- You can modify this to suit your database before
-- running your first migration.

-- Be sure that ID and DESCRIPTION fields exist in
-- BigInteger and String compatible fields respectively.

CREATE TABLE ${changelog} (
ID NUMERIC(20,0) NOT NULL,
APPLIED_AT VARCHAR(25) NOT NULL,
DESCRIPTION VARCHAR(255) NOT NULL
);

ALTER TABLE ${changelog}
ADD CONSTRAINT PK_${changelog}
PRIMARY KEY (id);

```

* UNDO: Everything after the `--//@UNDO` line, used to push backward the database

```sql

DROP TABLE ${changelog};
```

The migrateNew task needs **one** parameter to work as expected, the description of what this migration 
will do to your database. It is used like this:

> gradle migrateNew -Pdescription="This is a sample migration"

This parameter can be abreviated as simple as this:

> gradle migrateNew -Pd="This is a sample migration"

migrateUp and migrateDown
-------------------------

Theses tasks enable the evolving and devolving of your database. They use the script files inside the scripts directory.

The task `migrateUp` uses the **DO section** of the migration scripts to push forward your database and 
the `migrateDown`, in its turn, uses its **UNDO section** to push backward your database.

Both tasks have a parameter named `steps`. By default its value is **1** for `migrateDown` since you probably want to 
rollback only the last migration. For `migrateUp` the default value is `Integer.MAX_VALUE` this is because you probanly
want to execute all upwards pending migrations. The parameter is easily applied through the command line as following:

> gradle migrateUp -Psteps=2

or

> gradle migrateDown -Psteps=3

It is possible to abreviate it like this:

> gradle migrateUp -Ps=2

or 

> gradle migrateDown -Ps=3


License
=======

Copyright 2012 Marcelo Emanoel B. Diniz

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Developers
==========

* [Marcelo Emanoel B. Diniz](http://github.com/marceloemanoel)
* [Mircea Pop](http://github.com/mircea-pop)

Contributions
=============

Just fork the project and send a pull request :D
