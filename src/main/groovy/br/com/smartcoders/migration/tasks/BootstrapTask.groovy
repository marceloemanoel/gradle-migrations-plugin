package br.com.smartcoders.migration.tasks;

import org.apache.ibatis.migration.commands.BootstrapCommand
import org.gradle.api.Task
import org.gradle.api.tasks.TaskAction

class BootstrapTask extends BaseTask {

    File baseDir
    String environment
    Boolean force

    public BootstrapTask(){
        setDescription("Bootstrap migrations");
        setGroup("Migration");
    }

    @TaskAction
    void bootstrap() {
        def command = new BootstrapCommand(baseDir, environment, force)
        updateDriverClassLoader(command)
        command.execute()
    }
}
