package br.com.smartcoders.migration.tasks;

import org.apache.ibatis.migration.commands.UpCommand;
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

class UpTask extends DefaultTask {
  
  File baseDir
  String environment
  String stepCounter
  
  public UpTask(){
    setDescription("Execute migrations");
    setGroup("Migration");
  }
  
  @TaskAction
  def executeMigrations() {
    def command = new UpCommand(baseDir, "development", true)
    command.execute(stepCounter)
  }
  
}