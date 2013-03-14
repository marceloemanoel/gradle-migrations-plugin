package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.InitializeCommand
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.TaskAction

class InitTask extends DefaultTask {

    File baseDir
    String environment
    Boolean force

    public InitTask(){
        setDescription("Create migrations structure")
        setGroup("Migration")
    }

    @TaskAction
    void createDirectories() {
        if(baseDir.exists()) {
            if(force){
                logger.info "Deleting directory."
                baseDir.deleteDir()
            }
            else{
                logger.info "migrations dir already exists: " + baseDir.getAbsolutePath()
                return
            }
        } 
        else {
            logger.info "Creating directory."
            baseDir.mkdirs()
        }
        
        new InitializeCommand(baseDir, environment, force).execute()
        
        logger.info "Directory created at '${baseDir.absolutePath}'."
    }
}