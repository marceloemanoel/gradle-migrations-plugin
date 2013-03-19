package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import org.gradle.api.Project
import org.gradle.cli.CommandLineArgumentException

class MigrateNewParameters extends MigrationParameters {

    public MigrateNewParameters(Project project) {
        super(project)
    }

    def String getTemplate() {
        if(project.hasProperty("template"))
            project.template
    }

    def String getDescription() {
        if(project.hasProperty("d"))
            project.d
        else if(project.hasProperty("desc"))
            project.desc
        else if(project.hasProperty("description"))
            project.description
    }
    
    def validate() {
        if (description == null || description.isEmpty()) {
            def msg = "Please provide a description for the new file:\n" +
                      "Usage: gradle migrateNew -Pdescription=\"your description here\" [-Ptemplate=\"template path\"]"
            throw new CommandLineArgumentException(msg)
        }
    }
}
