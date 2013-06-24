package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.NewCommand
import org.apache.ibatis.migration.options.SelectedOptions;
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper
import com.github.marceloemanoel.gradle.migrations.tasks.parameters.MigrateNewParameters

class MigrateNew extends MigrationTask {

    def MigrateNewParameters parameters
    
    public MigrateNew(){
        setDescription("Create a new migration file. Configurable params: description, template")
        parameters = new MigrateNewParameters(project)
    }

    @TaskAction
    def executeMigrations() {
        parameters.validate()
        
        def options = new SelectedOptions();
        options.environment = environment
        options.template = parameters.template
        options.force = force
        options.paths.basePath = baseDir
        
        def command = new NewCommand(options)
        command.setDriverClassLoader(driverClassLoader)
        command.execute(parameters.description)
    }
}
