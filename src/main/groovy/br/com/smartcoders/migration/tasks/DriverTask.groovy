package br.com.smartcoders.migration.tasks;

import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class DriverTask extends DefaultTask {
    File baseDir

    @OutputDirectory
    File driversDir

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

        File gradleCachedDriver = project.getConfigurations().migrationDriver.singleFile
        File targetDriver = new File(driversDir,gradleCachedDriver.getName())

        if (!targetDriver.exists()) {
            ( new AntBuilder ( ) ).copy ( file : gradleCachedDriver , tofile : targetDriver )
            logger.info "Driver created at '${driversDir.absolutePath}'."
        }
    }
}
