package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.ScriptCommand
import org.apache.ibatis.migration.options.SelectedOptions
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.tasks.parameters.MigrateScriptParameters

class MigrateScript extends MigrationTask {
    
    public MigrateDown(){
        setDescription("Rewinds the database to a previous stage. Configurable params: steps")
        parameters = new MigrateScriptParameters(project)
    }

    @TaskAction
    void status() {
        def options = new SelectedOptions()
        options.environment = environment
        options.force = force
        options.paths.basePath = baseDir
         
        def command = new ScriptCommand(options)
        command.setDriverClassLoader(driverClassLoader)
        command.execute(parameters.version1, parameters.version2)
    }
}
