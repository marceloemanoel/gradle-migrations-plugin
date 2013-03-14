package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.InitializeCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class InitTask extends DefaultTask {

    def File getBaseDir(){
        project.file(project.migrations.baseDir)
    }
    
    def String getEnvironment() {
        project.migrations.environment
    }
    
    def Boolean getForce(){
        project.migrations.force
    }
    
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
                logger.info "Migrations directory already exists: " + baseDir.getAbsolutePath()
                return
            }
        }

        new InitializeCommand(baseDir, environment, force).execute()

        logger.info "Directory created at '${baseDir.absolutePath}'."
    }
}