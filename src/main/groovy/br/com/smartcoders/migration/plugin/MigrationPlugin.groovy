package br.com.smartcoders.migration.plugin;

import org.gradle.api.*

import br.com.smartcoders.migration.tasks.InitTask


class MigrationPlugin implements Plugin<Project> {
  
  
  def void apply(Project project) {
    def convention = new MigrationPluginConvention(project) 
  
    project.convention.plugins.migration = convention
    
    project.getRepositories().mavenCentral()
    project.configurations {
      migrations
    }
    project.dependencies {
      "migrations"  "org.mybatis:mybatis:3.0.6" 
    }
    
    project.task('init', type: InitTask) {
      baseDir = new File(convention.baseDir)
    }
  }
}