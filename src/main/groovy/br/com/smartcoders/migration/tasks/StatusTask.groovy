package br.com.smartcoders.migration.tasks

import org.apache.ibatis.migration.commands.StatusCommand
import org.gradle.api.tasks.TaskAction


class StatusTask extends BaseTask {
    File baseDir
    String environment
    Boolean force
    
    public StatusTask(){
      setDescription("Shows migrations status");
      setGroup("Migration");
    }
    
    @TaskAction
    void status() 
    {
        def command = new StatusCommand(baseDir, environment, force)   
        updateDriverClassLoader(command)
        command.execute()
    }
}
