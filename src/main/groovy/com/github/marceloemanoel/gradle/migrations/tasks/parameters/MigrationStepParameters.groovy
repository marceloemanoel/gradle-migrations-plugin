package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import org.gradle.api.Project

class MigrationStepParameters extends MigrationParameters {
    
    public MigrationStepParameters(Project project) {
        super(project)
    }
    
    def getSteps() {
        if(project.hasProperty("s"))
            return project.s;
        if(project.hasProperty("steps"))
            return project.steps
        ""
    }
}
