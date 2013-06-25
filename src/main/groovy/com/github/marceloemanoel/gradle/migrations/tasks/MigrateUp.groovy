package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.UpCommand
import org.apache.ibatis.migration.options.SelectedOptions
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.tasks.parameters.MigrationStepParameters

class MigrateUp extends MigrationTask {
    
    public MigrateUp(){
        setDescription("Apply any pending migration following creation order. Configurable params: steps")
        parameters = new MigrationStepParameters(project)
    }

    @TaskAction
    def executeMigrations() {
        def options = new SelectedOptions()
        options.environment = environment
        options.force = force
        options.paths.basePath = baseDir
        
        def command = new UpCommand(options)
        command.setDriverClassLoader(driverClassLoader)
        command.execute(parameters.steps)
    }
}