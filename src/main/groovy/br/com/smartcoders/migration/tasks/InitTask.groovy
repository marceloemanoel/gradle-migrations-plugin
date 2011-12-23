package br.com.smartcoders.migration.tasks;

import org.gradle.api.DefaultTask
import org.gradle.api.internal.TaskOutputsInternal
import org.gradle.api.tasks.TaskAction

public class InitTask extends DefaultTask {
  
  def description = "Create the base directory for migrations"
  def group = "MIGRATIONS"
  def baseDir = file("migrations")
  
  def InitTask(){
    outputs.dir baseDir
  }
  
  @TaskAction
  def void action() {
    if(!baseDir.exists()){
      logger.info "Creating migrations directory."
      
      def environments = new File("environments", baseDir)
      def scripts = new File("scripts", baseDir)
      def drivers = new File("drivers", baseDir)
      
      new File("development.properties", environments) << new File("./resources/environments/development.properties").text
      
      [
        environments,
        scripts,
        drivers
      ].each it.mkdirs()
      logger.info "Migrations directory created at '${baseDir.absolutePath}'."
    }
    else{
      logger.info "Migrations directory already exists."
    }
  }
}
