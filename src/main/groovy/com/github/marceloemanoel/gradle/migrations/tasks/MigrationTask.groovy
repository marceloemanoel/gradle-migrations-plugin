package com.github.marceloemanoel.gradle.migrations.tasks

import java.io.File;

import org.gradle.api.DefaultTask

class MigrationTask extends DefaultTask {
    
    protected MigrationTask(){
        setGroup("Migration")
    }
    
    def File getBaseDir(){
        project.file(project.migrations.baseDir)
    }
    
    def String getEnvironment() {
        project.migrations.environment
    }
    
    def Boolean getForce(){
        project.migrations.force
    }
}
