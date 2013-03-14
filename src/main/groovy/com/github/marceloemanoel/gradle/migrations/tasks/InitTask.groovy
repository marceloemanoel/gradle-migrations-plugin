package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.InitializeCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

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
                logger.info "Migrations dir already exists: " + baseDir.getAbsolutePath()
                return
            }
        }

        def command = new InitializeCommand(baseDir, environment, force)
        CommandHelper.updateDriverClassLoader(getProject(), command)
        command.execute()

        logger.info "Directory created at '${baseDir.absolutePath}'."
    }
}