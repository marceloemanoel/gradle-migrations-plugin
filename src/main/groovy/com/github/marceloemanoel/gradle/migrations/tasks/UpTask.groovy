package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.UpCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class UpTask extends DefaultTask {

    File baseDir
    String environment
    String stepCounter
    Boolean force

    public UpTask(){
        setDescription("Execute migrations")
        setGroup("Migration")
    }

    @TaskAction
    def executeMigrations() {
        new UpCommand(baseDir, environment, force).execute(stepCounter)
    }
}