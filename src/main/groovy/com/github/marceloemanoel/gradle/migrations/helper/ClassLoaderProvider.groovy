package com.github.marceloemanoel.gradle.migrations.helper

import org.apache.ibatis.migration.commands.BaseCommand
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration;

final class ClassLoaderProvider {

    private Project project;
    private Configuration configuration;

    public ClassLoaderProvider(Project project) {
        this.project = project;
    }

    def configuration() {
        def driverConfig = project.getConfigurations().findByName("migrationsDriver");
        if(driverConfig && !driverConfig.empty) {
            project.logger.warn "The 'migrationDriver' configuration has been deprecated and is scheduled to be removed in the next release. It is now replaced by the 'compile' configuration."
            driverConfig;
        }
        else {
            project.getConfigurations().findByName("compile");
        }
    }

    def ClassLoader getDriverClassLoader() {
        if(!configuration) {
            configuration = configuration();
        }
        def dependencies = getDependencies()
        new URLClassLoader(dependencies.toArray(new URL[dependencies.size]))
    }

    def getDependencies() {
        configuration.files.collect {
            it.toURI().toURL()
        }
    }

}
