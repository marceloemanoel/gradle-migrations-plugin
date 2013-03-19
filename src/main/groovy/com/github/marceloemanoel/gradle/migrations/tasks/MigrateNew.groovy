package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.NewCommand
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
        def command = new NewCommand(baseDir, environment, parameters.template, force)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute(parameters.description)
    }
}
