package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.UpCommand
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper
import com.github.marceloemanoel.gradle.migrations.tasks.parameters.MigrationStepParameter

class MigrateUp extends MigrationTask {
    
    MigrationStepParameter parameters
    
    public MigrateUp(){
        setDescription("Apply any pending migration following creation order. Configurable params: steps")
        parameters = new MigrationStepParameter(project)
    }

    @TaskAction
    def executeMigrations() {
        def command = new UpCommand(baseDir, environment, force)
        CommandHelper.updateDriverClassLoader(getProject(), command)
        command.execute(parameters.steps)
    }
}