package com.github.marceloemanoel.gradle.migrations.plugin

import org.gradle.api.*

class MigrationPluginConvention {
  
  Project project
  def baseDir = "migrations"
  
  public MigrationPluginConvention(Project project) {
    this.project = project
  }
  
}