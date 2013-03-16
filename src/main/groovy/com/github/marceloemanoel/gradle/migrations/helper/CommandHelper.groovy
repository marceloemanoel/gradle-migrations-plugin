package com.github.marceloemanoel.gradle.migrations.helper

import org.apache.ibatis.migration.commands.BaseCommand
import org.gradle.api.DefaultTask
import org.gradle.api.Project

final class CommandHelper {

    public static updateDriverClassLoader(Project project, BaseCommand command) {
        def dependencies = project.getConfigurations().migrationsDriver.files.collect {
            it.toURI().toURL()
        }
        command.setDriverClassLoader(new URLClassLoader(dependencies.toArray(new URL[dependencies.size])))
    }
}
