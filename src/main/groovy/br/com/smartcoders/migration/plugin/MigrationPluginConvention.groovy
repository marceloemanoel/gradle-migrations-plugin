package br.com.smartcoders.migration.plugin;

import org.gradle.api.*;

class MigrationPluginConvention {
  
  Project project
  def baseDir = "migrations"
  
  public MigrationPluginConvention(Project project) {
    this.project = project
  }
}