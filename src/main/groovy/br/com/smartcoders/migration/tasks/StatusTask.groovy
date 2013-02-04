package br.com.smartcoders.migration.tasks

import org.apache.ibatis.migration.commands.StatusCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class StatusTask extends DefaultTask {
    File baseDir
    String environment
    Boolean force
    
    public StatusTask(){
      setDescription("Create migrations status");
      setGroup("Migration");
    }
    
    @TaskAction
    void status() 
    {
        def command = new StatusCommand(baseDir, environment, force)
        command.execute()
    }
      
}
