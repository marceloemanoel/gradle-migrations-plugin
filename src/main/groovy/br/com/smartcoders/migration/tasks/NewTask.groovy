package br.com.smartcoders.migration.tasks

import org.apache.ibatis.migration.commands.NewCommand
import org.gradle.api.tasks.TaskAction
import org.gradle.cli.CommandLineArgumentException

class NewTask extends BaseTask {
    File baseDir
    String environment
    String template
    String fileDescription = ""
    Boolean force

    public NewTask(){
        setDescription("Execute migrations new command.Configurable params: des, template");
        setGroup("Migration");
    }

    @TaskAction
    def executeMigrations() {
        if (fileDescription.isEmpty()) {
            throw new CommandLineArgumentException("Please provide a description for the new file:" +
            " \nUsage: gradle migrateNew -Pdes=\"your description here\" [-Ptemplate=\"template path\"]")
        }
        def command = new NewCommand(baseDir, environment, template, force)
        updateDriverClassLoader(command)
        command.execute(fileDescription)
    }
}
