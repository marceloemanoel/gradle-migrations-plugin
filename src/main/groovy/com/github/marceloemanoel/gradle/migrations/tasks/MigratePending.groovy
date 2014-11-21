package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.PendingCommand
import org.apache.ibatis.migration.options.SelectedOptions
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.tasks.parameters.MigrationStepParameters

class MigratePending extends MigrationTask {
    
    public MigrateUp(){
        setDescription("Apply any pending migration following creation order." +
			" Also applies pending migrations with ids lower than the last applied migration.")
        parameters = new MigrationStepParameters(project)
    }

    @TaskAction
    def executeMigrations() {
        def options = new SelectedOptions()
        options.environment = environment
        options.force = force
        options.paths.basePath = baseDir
        
        def command = new PendingCommand(options)
        command.setDriverClassLoader(driverClassLoader)
        command.execute()
    }
}