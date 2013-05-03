package com.github.marceloemanoel.gradle.migrations.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

import com.github.marceloemanoel.gradle.migrations.tasks.MigrateBootstrap
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateDown
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateInit
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateNew
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateStatus
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateUp

class MigrationsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.extensions.create("migrations", MigrationPluginExtension)
        def extensionPoint = project.migrations

        project.getRepositories().mavenCentral()
        
        project.configurations {
            migrationsDriver
            migrations
        }
        
        project.dependencies {
            "migrations"  'org.mybatis:mybatis-migrations:3.1.0'
        }

        project.task('migrateInit', type: MigrateInit)
        project.task("migrateBootstrap", type: MigrateBootstrap)
        project.task("migrateStatus", type: MigrateStatus)
        project.task("migrateNew", type: MigrateNew)
        project.task("migrateUp", type: MigrateUp)
        project.task("migrateDown", type: MigrateDown)
    }
}