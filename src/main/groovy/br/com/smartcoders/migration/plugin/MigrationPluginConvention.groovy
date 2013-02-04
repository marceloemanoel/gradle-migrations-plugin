package br.com.smartcoders.migration.plugin;

import org.gradle.api.*

class MigrationPluginConvention {
  
  Project project
  def baseDir = "sql"
  def environment = "development"
  def force = false
  
  public MigrationPluginConvention(Project project) {
    this.project = project
  }
}