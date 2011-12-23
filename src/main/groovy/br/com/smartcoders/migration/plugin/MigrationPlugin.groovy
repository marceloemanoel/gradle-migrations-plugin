package br.com.smartcoders.migration.plugin;
import org.gradle.api.*

import br.com.smartcoders.migration.tasks.InitTask

class MigrationPlugin implements Plugin<Project> {
  
  def void apply(Project project) {
    
    project.getRepositories().mavenCentral()
    project.getConfigurations().add("migrations")
    project.getDependencies().add("migrations", "org.mybatis:mybatis:3.0.6")
    
    project.convention.plugins.migration = new MigrationPluginConvention(project)

    project.tasks.init = new InitTask()
    
    project.task('migrate') << {
      println "j‡ pode migrar alguma coisa"
    }
  
  }
}

class MigrationPluginConvention {
  
  Project project;
  def migrationBaseDir = "migrations"
  
  public MigrationPluginConvention(Project project) {
    this.project = project
  }  
  
}