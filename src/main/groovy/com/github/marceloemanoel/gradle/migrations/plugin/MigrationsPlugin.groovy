package com.github.marceloemanoel.gradle.migrations.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

import com.github.marceloemanoel.gradle.migrations.tasks.InitTask
import com.github.marceloemanoel.gradle.migrations.tasks.UpTask

class MigrationsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.extensions.create("migrations", MigrationPluginExtension)
        def extensionPoint = project.migrations
        
        project.getRepositories().mavenCentral()
        project.configurations {
            migrationDriver
            migrations
        }
        project.dependencies { "migrations"  "org.mybatis:mybatis:3.0.6" }

        project.task('migrateInit', type: InitTask) {
            baseDir = project.file(extensionPoint.baseDir)
            force = extensionPoint.force
        }

        project.task("migrateUp", type: UpTask) {
            baseDir = project.file(extensionPoint.baseDir)
            if(project.hasProperty("stepCount"))
                stepCounter = project.stepCount
        }
    }
}