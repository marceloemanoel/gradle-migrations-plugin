package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.DownCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class DownTask extends MigrationTask {

    String steps = ""

    public DownTask(){
        setDescription("Executes migration down command.Configurable params: steps")
    }

    @TaskAction
    void status() {
        def command = new DownCommand(baseDir, environment, force)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute(steps)
    }
}
