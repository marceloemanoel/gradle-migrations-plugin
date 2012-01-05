package br.com.smartcoders.migration.plugin;

import org.gradle.api.*
import org.gradle.api.artifacts.Configuration.State

import br.com.smartcoders.migration.tasks.InitTask
import br.com.smartcoders.migration.tasks.UpTask


class MigrationPlugin implements Plugin<Project> {
  
  def void apply(Project project) {
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