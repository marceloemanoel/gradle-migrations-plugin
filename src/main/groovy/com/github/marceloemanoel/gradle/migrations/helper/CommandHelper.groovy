package com.github.marceloemanoel.gradle.migrations.helper

import org.apache.ibatis.migration.commands.BaseCommand
import org.gradle.api.DefaultTask
import org.gradle.api.Project

final class CommandHelper {

    public static updateDriverClassLoader(Project project, BaseCommand command) {
        command.setDriverClassLoader(new URLClassLoader(project.getConfigurations().migrationDriver.singleFile.toURI().toURL()))
    }
}
