package com.github.marceloemanoel.gradle.migrations.tasks

import org.gradle.api.DefaultTask

import com.github.marceloemanoel.gradle.migrations.helper.ClassLoaderProvider
import com.github.marceloemanoel.gradle.migrations.tasks.parameters.MigrationParameters

class MigrationTask extends DefaultTask {
    
    def parameters
    def helper
     
    protected MigrationTask(){
        setGroup("Migration")
        parameters = new MigrationParameters(project)
        helper = new ClassLoaderProvider(project)
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
    
    def ClassLoader getDriverClassLoader() {
        helper.driverClassLoader
    }
    
}
