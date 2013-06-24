package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import org.gradle.api.Project

class MigrationParameters {
    
    protected Project project
    
    public MigrationParameters(Project project) {
        this.project = project 
    }
    
    def File getBaseDir() {
        if(project.hasProperty("b"))
            return project.file(project.b)
        project.file(project.migrations.baseDir)
    }
    
    def String getEnvironment() {
        def properties = ["e", "env", "environment"]
        for (String property : properties) {
            if(project.hasProperty(property))
                return project[property]
        }
        project.migrations.environment
    }
    
    def Boolean getForce() {
        if(project.hasProperty("f"))
            return project.f
        project.migrations.force
    }
}
