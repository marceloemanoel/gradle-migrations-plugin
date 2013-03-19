package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin;
import com.github.marceloemanoel.gradle.migrations.tasks.MigrationTask;

class BaseDirMigrationParametersTest {

    def Project project
    def MigrationParameters parameters

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin

        parameters = project.migrateUp.parameters
    }

    @Test
    void theDefaultValueIsIsMigrations() {
        assertEquals(project.file("migrations"), parameters.baseDir)
    }
    
    @Test
    void parameter_b_takesPrecedenceOverDefaultValue() {
        project.b = "sql"
        assertEquals(project.file("sql"), parameters.baseDir)
    }
    
}
