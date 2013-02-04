package br.com.smartcoders.migration.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CleanTask extends DefaultTask {
    File baseDir

    public CleanTask(){
        setDescription("Deletes the migrate driver(s)");
        setGroup("Migration");
    }

    @TaskAction
    void clean() {
        logger.info "cleaning the drivers folder..."
        File driverFolder = new File(baseDir, "drivers")
        if (driverFolder.exists()) {
            driverFolder.listFiles().each { it.delete() }
        }
    }
}
