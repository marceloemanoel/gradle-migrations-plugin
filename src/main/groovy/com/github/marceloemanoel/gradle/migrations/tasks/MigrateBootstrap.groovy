package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.BootstrapCommand
import org.apache.ibatis.migration.options.SelectedOptions
import org.gradle.api.tasks.TaskAction

class MigrateBootstrap extends MigrationTask {

    public MigrateBootstrap(){
        setDescription("Use an existing database structure as an starting point for migrations")
    }

    @TaskAction
    void bootstrap() {
        def options = new SelectedOptions()
        options.environment = environment
        options.force = force
        options.paths.basePath = baseDir
        
        def command = new BootstrapCommand(options)
        command.setDriverClassLoader(driverClassLoader)
        command.execute()
    }
}
