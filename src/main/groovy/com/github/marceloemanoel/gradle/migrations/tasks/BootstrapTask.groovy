package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.BootstrapCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper;

class BootstrapTask extends DefaultTask {

    def File getBaseDir(){
        project.file(project.migrations.baseDir)
    }
    
    def String getEnvironment() {
        project.migrations.environment
    }
    
    def Boolean getForce(){
        project.migrations.force
    }

    public BootstrapTask(){
        setDescription("Bootstrap migrations");
        setGroup("Migration");
    }

    @TaskAction
    void bootstrap() {
        def command = new BootstrapCommand(baseDir, environment, force)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute()
    }
}
