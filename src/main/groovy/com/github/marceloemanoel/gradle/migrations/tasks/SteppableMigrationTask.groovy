package com.github.marceloemanoel.gradle.migrations.tasks

class SteppableMigrationTask extends MigrationTask {
    
    def String getSteps() {
        if(project.hasProperty("s"))
            return project.s;
        if(project.hasProperty("steps"))
            return project.steps    
        ""
    }
    
}
