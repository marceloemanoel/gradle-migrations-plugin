package br.com.smartcoders.migration.plugin;

import org.gradle.api.*
import org.gradle.api.artifacts.Configuration.State
import br.com.smartcoders.migration.tasks.*

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
    
    project.task('migrateInit', type: InitTask) {
      baseDir = project.file(convention.baseDir)
	  environment = convention.environment
	  force = convention.force
    }
    
    project.task("migrateUp", type: UpTask) {
      baseDir = project.file(convention.baseDir)
	  environment = convention.environment
	  force = convention.force
	  if(project.hasProperty("stepCount")) {
		  stepCounter = project.stepCount
	  }
    }
	
	project.task("migrateBootstrap", type: BootstrapTask) {
      baseDir = project.file(convention.baseDir)
	  environment = convention.environment
	  force = convention.force
    }

	project.task("migrateDriver", type: DriverTask) {
	  baseDir = project.file(convention.baseDir)
	  environment = convention.environment
	}
  }
}