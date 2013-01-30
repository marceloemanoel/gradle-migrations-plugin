package br.com.smartcoders.migration.tasks;

import org.apache.ibatis.migration.commands.InitializeCommand
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.tasks.TaskAction

class DriverTask extends DefaultTask {
  
  File baseDir
  String environment
  Boolean force
  
  public DriverTask(){
    setDescription("Copy and link jdbc driver");
    setGroup("Migration");
  }
  
  @TaskAction
  void copyDriver() {
    
    if(! baseDir.exists()) {
	  logger.error "did not find dir: " + baseDir.getAbsolutePath()
	  return
    }
	
	File environmentsDir = new File(baseDir, "environments")
	File driversDir = new File(baseDir, "drivers")
	if (! driversDir.exists()) {
		driversDir.mkdirs()
	}

	File gradleCachedDriver = project.getConfigurations().migrationDriver.singleFile
	File targetDriver = new File(driversDir,gradleCachedDriver.getName())

	( new AntBuilder ( ) ).copy ( file : gradleCachedDriver , tofile : targetDriver )
    logger.info "Driver created at '${driversDir.absolutePath}'."
  }
}
