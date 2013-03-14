package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.DownCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class DownTask extends DefaultTask {

    File baseDir
    String environment
    Boolean force
    String steps

    public DownTask(){
        setDescription("Executes migration down command.Configurable params: steps")
        setGroup("Migration")
    }

    @TaskAction
    void status() {
        def command = new DownCommand(baseDir, environment, force)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute(steps)
    }
}
