package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import org.gradle.api.Project

class MigrateScriptParameters extends MigrationParameters {
    
    public MigrateScriptParameters(Project project) {
        super(project)
    }
    
    def getVersion1() {
        if(project.hasProperty("v1"))
            return project.v1;
        ""
    }
    
    def getVersion2() {
        if(project.hasProperty("v2"))
            return project.v2
        ""
    }
}
