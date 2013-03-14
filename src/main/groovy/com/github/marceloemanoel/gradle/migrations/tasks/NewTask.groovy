package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.NewCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.cli.CommandLineArgumentException

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper;

class NewTask extends DefaultTask {
    
    File baseDir
    String environment
    String template
    String fileDescription
    Boolean force

    public NewTask(){
        setDescription("Execute migrations new command.Configurable params: description, template")
        setGroup("Migration");
    }

    @TaskAction
    def executeMigrations() {
        if (fileDescription.isEmpty()) {
            throw new CommandLineArgumentException("Please provide a description for the new file:" +
            " \nUsage: gradle migrateNew -Pdescription=\"your description here\" [-Ptemplate=\"template path\"]")
        }
        def command = new NewCommand(baseDir, environment, template, force)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute(fileDescription)
    }
}
