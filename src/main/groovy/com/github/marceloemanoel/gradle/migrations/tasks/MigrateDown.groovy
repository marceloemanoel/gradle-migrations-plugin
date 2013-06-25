package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.DownCommand
import org.apache.ibatis.migration.options.SelectedOptions
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.tasks.parameters.MigrationStepParameters

class MigrateDown extends MigrationTask {
    
    public MigrateDown(){
        setDescription("Rewinds the database to a previous stage. Configurable params: steps")
        parameters = new MigrationStepParameters(project)
    }

    @TaskAction
    void status() {
        def options = new SelectedOptions()
        options.environment = environment
        options.force = force
        options.paths.basePath = baseDir
         
        def command = new DownCommand(options)
        command.setDriverClassLoader(driverClassLoader)
        command.execute(parameters.steps)
    }
}
