package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.StatusCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper


class StatusTask extends MigrationTask {
    
    public StatusTask() {
        setDescription("Shows migrations status")
    }

    @TaskAction
    void status() {
        def command = new StatusCommand(baseDir, environment, force)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute()
    }
}
