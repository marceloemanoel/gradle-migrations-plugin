gradle-migrations-plugin
=============================

Gradle plugin to integrate mybatis migrations

Install
=======

Just put the following code snippet in your build.gradle file.

```groovy
buildScript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath "com.github.marceloemanoel:gradle-migrations-plugin:0.1"
  }
}

apply plugin: "gradle-migrations-plugin"
```
