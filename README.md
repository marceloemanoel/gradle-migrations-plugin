Gradle Migrations Plugin
=============================

Gradle plugin to integrate [mybatis migrations](https://code.google.com/p/mybatis/wiki/Migration) in the build system life cycle. 
The plugin adds a group of tasks named *Migrations* composed by:

* [migrateInit](#migrateinit) - Create migrations structure
* [migrateBootstrap](#migratebootstrap) - Bootstrap migrations
* [migrateStatus](#migratestatus) - Shows migrations status
* [migrateNew](#migratenew) - Execute migrations new command.Configurable params: description, template
* [migrateUp](#migrateup) - Execute migrations up command.Configurable params: steps
* [migrateDown](#migratedown) - Executes migration down command.Configurable params: steps


Install
=======

Just put the following code snippet in your build.gradle file.

```groovy
buildScript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath "com.github.marceloemanoel:gradle-migrations:0.2"
  }
}

apply plugin: "migrations"
```

Available Tasks
===============

migrateInit
-----------
The init command initializes a new ‘migration path’, also called a ‘repository’ (of migration scripts).
Regardless of whether your working with a new database or an existing one, you’ll run init to create the
workspace in which you’ll place everything you need to manage database change. Running this
command will create the directory specified by the `baseDir` property (which is the directory "migrations"
by default). 

When the command is completed, the directory will contain the following sub-directories:

> ./environments

In the environments folder you will find .properties files that represent your database instances. By
default a development.properties file is created for you to configure your development time database
properties. You can also create test.properties and production.properties files. The environment can 
be specified when running a migration by using the `environment` property (without the path or ".properties" part).

> ./scripts

This directory contains your migration SQL files. These are the files that contain your DDL to both
upgrade and downgrade your database structure. By default, the directory will contain the script to
create the changelog table, plus one empty example migration script. To create a new migration script,
use the [migrateNew](#migratenew) command. To run all pending migrations, use the [migrateUp](#migrateup) 
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

migrateUp
---------


migrateDown
-----------

Customization
=============

If you need to change any of the default values you can use a configuration block like this:

```groovy
migrations {
   baseDir = "anotherDirectory"
   environment = "test"
   force = true
}
```

All values are optional the following table presents the default values of each property:

<pre>
Property      Description                                               Default Value
==========================================================================================
baseDir       Defines the base directory containing the migrations      migrations
environment   Defines the environment used by the tasks                 development
force         Forces the execution of the tasks                         false
</pre>
