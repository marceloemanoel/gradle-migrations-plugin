package com.github.marceloemanoel.gradle.migrations.helper

import org.apache.ibatis.migration.commands.BaseCommand
import org.gradle.api.DefaultTask
import org.gradle.api.Project

final class CommandHelper {
    
    private Project project;
    
    public CommandHelper(Project project) {
        this.project = project;
    }
    
    public updateDriverClassLoader(BaseCommand command) {
        def dependencies = getDependencies();
        command.setDriverClassLoader(new URLClassLoader(dependencies.toArray(new URL[dependencies.size])))
    }
    
    def getDependencies() {
        configuration().files.collect {
            it.toURI().toURL()
        }
    }
    
    def configuration() {
        def migrationDriver = project.getConfigurations().findByName("migrationDriver");
        if(migrationDriver != null) {
            project.logger.warn("The 'migrationDriver' configuration has been deprecated and is scheduled to be removed in the next release. It is now replaced by the 'compile' configuration.")
            migrationDriver;
        }
        else {
            project.getConfigurations().findByName("compile");
        }
    }
}
