package com.github.marceloemanoel.gradle.migrations.tasks

import org.gradle.api.DefaultTask

import com.github.marceloemanoel.gradle.migrations.tasks.parameters.MigrationParameters

class MigrationTask extends DefaultTask {
    
    def parameters
    
    protected MigrationTask(){
        setGroup("Migration")
        parameters = new MigrationParameters(project)
    }
    
    def File getBaseDir(){
        parameters.baseDir
    }
    
    def String getEnvironment() {
        parameters.environment
    }
    
    def Boolean getForce(){
        parameters.force
    }
}
