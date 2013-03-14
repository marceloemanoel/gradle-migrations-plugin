package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.InitializeCommand
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.tasks.TaskAction

class InitTask extends DefaultTask {
  
  File baseDir
  String environment
  
  public InitTask(){
    setDescription("Create migrations structure");
    setGroup("Migration");
  }
  
  @TaskAction
  void createDirectories() {
    logger.info "Creating directory."
    
    if(!baseDir.exists())
      baseDir.mkdirs();
    
    def command = new InitializeCommand(baseDir, "development", true)
    command.execute()
    
    File driversDir = new File(baseDir, "drivers");
    driversDir.deleteDir();
    
    File environmentsDir = new File(baseDir, "environments")
    
    def props = new Properties()
    def devEnv = new File(environmentsDir,"development.properties")
    devEnv.withInputStream { stream ->
      props.load(stream)
    }
    
    props.driver_path = getProject().getConfigurations().migrationDriver.singleFile.parent
    
    devEnv.withOutputStream { out ->
      props.store(out, "")
    }
    
    logger.info "Directory created at '${baseDir.absolutePath}'."
  }
}