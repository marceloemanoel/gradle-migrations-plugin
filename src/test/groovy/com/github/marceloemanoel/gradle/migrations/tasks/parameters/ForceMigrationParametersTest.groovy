package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin;
import com.github.marceloemanoel.gradle.migrations.tasks.MigrationTask;

class ForceMigrationParametersTest {

    def Project project
    def MigrationParameters parameters

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin

        parameters = project.migrateUp.parameters
    }

    @Test
    void theDefaultValueIsFalse() {
        assertFalse(parameters.force)
    }

    @Test
    void migrations_forceTakesPrecedenceOverDefaultValue() {
        project.migrations.force = true
        assertTrue(parameters.force)
    }

    @Test
    void parameter_f_takesPrecedenceOverOthers() {
        project.f = true
        project.migrations.force = false

        assertTrue(parameters.force)
    }

}
