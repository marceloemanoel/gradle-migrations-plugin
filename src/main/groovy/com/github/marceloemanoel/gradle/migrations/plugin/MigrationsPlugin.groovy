package com.github.marceloemanoel.gradle.migrations.plugin;

import org.gradle.api.Plugin
import org.gradle.api.Project

class MigrationsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        def convention = new MigrationPluginConvention(project)
        
          project.convention.plugins.migration = convention
          
          project.getRepositories().mavenCentral()
          project.configurations {
            migrationDriver
            migrations
          }
          project.dependencies {
            "migrations"  "org.mybatis:mybatis:3.0.6"
          }
          
          project.task('init', type: InitTask) {
            baseDir = new File(convention.baseDir)
          }
          
          project.task("up", type: UpTask) {
            baseDir = new File(convention.baseDir)
            if(project.hasProperty("stepCount"))
            stepCounter = project.stepCount
          }
    }

}