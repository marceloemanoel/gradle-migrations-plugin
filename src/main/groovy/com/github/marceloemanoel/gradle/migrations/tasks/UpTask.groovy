package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.UpCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class UpTask extends DefaultTask {

    File baseDir
    String environment
    String steps
    Boolean force

    public UpTask(){
        setDescription("Execute migrations up command.Configurable params: steps")
        setGroup("Migration");
    }

    @TaskAction
    def executeMigrations() {
        def command = new UpCommand(baseDir, environment, force)
        CommandHelper.updateDriverClassLoader(getProject(), command)
        command.execute(steps)
    }
}