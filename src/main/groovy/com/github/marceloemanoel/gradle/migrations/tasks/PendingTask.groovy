package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.PendingCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class PendingTask extends MigrationTask {

    public PendingTask(){
        setDescription("Runs all pending migrations")
    }

    @TaskAction
    void pending() {
        logger.info "Running all pending migrations."

        new PendingCommand(baseDir, environment, force).execute()
        
        logger.info "All pending migrations were runned."
    }
}