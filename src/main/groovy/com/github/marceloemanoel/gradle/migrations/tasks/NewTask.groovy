package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.NewCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.cli.CommandLineArgumentException

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper;

class NewTask extends MigrationTask {

    String template
    String fileDescription

    public NewTask(){
        setDescription("Create a new migration file. Configurable params: description, template")
    }

    @TaskAction
    def executeMigrations() {
        if (fileDescription == null || fileDescription.isEmpty()) {
            def msg = "Please provide a description for the new file:\n" +
                      "Usage: gradle migrateNew -Pdescription=\"your description here\" [-Ptemplate=\"template path\"]"
            throw new CommandLineArgumentException(msg)
        }

        def command = new NewCommand(baseDir, environment, template, force)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute(fileDescription)
    }
}
