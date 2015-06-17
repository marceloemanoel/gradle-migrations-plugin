package com.github.marceloemanoel.gradle.migrations.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

import com.github.marceloemanoel.gradle.migrations.tasks.BootstrapTask
import com.github.marceloemanoel.gradle.migrations.tasks.DownTask
import com.github.marceloemanoel.gradle.migrations.tasks.InitTask
import com.github.marceloemanoel.gradle.migrations.tasks.NewTask
import com.github.marceloemanoel.gradle.migrations.tasks.PendingTask
import com.github.marceloemanoel.gradle.migrations.tasks.StatusTask
import com.github.marceloemanoel.gradle.migrations.tasks.UpTask

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
            "migrations"  "org.mybatis:mybatis:3.0.6"
        }

        project.task('migrateInit', type: InitTask)
        project.task("migrateBootstrap", type: BootstrapTask)
        project.task("migrateStatus", type: StatusTask)
        project.task("migratePending", type: PendingTask)
        
        project.task("migrateNew", type: NewTask) {
            if(project.hasProperty("d")) {
                fileDescription = project.d
                println("description set with ${project.d}")    
            } else            
            if(project.hasProperty("description")) {
                fileDescription = project.description
            }

            if(project.hasProperty("template")) {
                template = project.template
            }
        }
        
        project.task("migrateUp", type: UpTask)
        project.task("migrateDown", type: DownTask)
    }
}