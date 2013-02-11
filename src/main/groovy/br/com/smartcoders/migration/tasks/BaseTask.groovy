package br.com.smartcoders.migration.tasks;

import org.apache.ibatis.migration.commands.BaseCommand
import org.gradle.api.DefaultTask



public class BaseTask extends DefaultTask {

    protected updateDriverClassLoader(BaseCommand command)
    {
        command.setDriverClassLoader(new URLClassLoader(project.getConfigurations().migrationDriver.singleFile.toURI().toURL()))
    }
}
