package br.com.smartcoders.migration.tasks;

import org.apache.ibatis.migration.commands.BootstrapCommand
import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.TaskAction

class BootstrapTask extends DefaultTask {

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
        command.execute()
    }

    @Override
    public Task doLast(Closure action) {
        def clean = {
            logger.info "cleaning the drivers folder..."
            File driverFolder = new File(baseDir, "drivers")
            if (driverFolder.exists()) {
                File[] files = driverFolder.listFiles()
                files.each { it.delete() }
            }            
        }
        return super.doLast(clean << action);
    }
}
