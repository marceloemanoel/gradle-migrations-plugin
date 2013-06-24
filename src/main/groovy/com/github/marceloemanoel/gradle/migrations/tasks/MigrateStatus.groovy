package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.StatusCommand
import org.apache.ibatis.migration.options.SelectedOptions
import org.gradle.api.tasks.TaskAction

class MigrateStatus extends MigrationTask {
    
    public MigrateStatus() {
        setDescription("Shows current database status")
    }

    @TaskAction
    void status() {
        def options = new SelectedOptions();
        options.environment = environment
        options.force = force
        options.paths.basePath = baseDir
        
        def command = new StatusCommand(options)
        command.setDriverClassLoader(driverClassLoader)
        command.execute()
    }
}
