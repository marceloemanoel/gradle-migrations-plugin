package com.github.marceloemanoel.gradle.migrations.tasks

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin;

class SteppableMigrationTaskTest {
    
    def Project project
    def SteppableMigrationTask task
    
    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
        
        task = project.migrateUp
    }

    @Test
    void ifNoStepsIsProvidedValueOneIsReturned() {
        assertEquals("1", task.steps)
    }
    
    @Test
    void parameter_s_takesPrecedenceOverTheOthers() {
        project.s = "3"
        project.steps = "0"
        assertEquals("3", task.steps)
    }
    
    @Test
    void parameter_steps_takesPrecedenceOverDefaultValue() {
        project.steps = "2"
        assertEquals("2", task.steps)
    }
}
