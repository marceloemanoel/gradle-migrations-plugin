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
        if(project.hasProperty("e"))
            return project.e
        if(project.hasProperty("env"))
            return project.env
        if(project.hasProperty("environment"))
            return project.environment
        project.migrations.environment
    }
    
    def Boolean getForce() {
        if(project.hasProperty("f"))
            return project.f
        project.migrations.force
    }
}
