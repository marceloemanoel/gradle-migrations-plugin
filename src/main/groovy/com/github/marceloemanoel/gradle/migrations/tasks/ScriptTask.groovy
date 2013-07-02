package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.ScriptCommand
import org.gradle.api.tasks.TaskAction
import org.gradle.cli.CommandLineArgumentException

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class ScriptTask extends MigrationTask {
    
    String v1
    String v2
    
    public ScriptTask() {
        setDescription("Create a new migration file. Configurable params: description, template")
    }
    
    @TaskAction
    def executeMigrations() {
        def command = new ScriptCommand(baseDir, environment, force)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute(v1, v2)
    }
}
