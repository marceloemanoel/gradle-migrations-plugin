package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin;

class StepParameterTest {
    
    def Project project
    def MigrationStepParameter parameter
    
    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
        
        parameter = project.migrateUp.parameters
    }

    @Test
    void theDefaultStepValueIsAnEmptyString() {
        assertEquals("", parameter.steps)
    }
    
    @Test
    void parameter_s_takesPrecedenceOverTheOthers() {
        project.s = "3"
        project.steps = "0"
        assertEquals("3", parameter.steps)
    }
    
    @Test
    void parameter_steps_takesPrecedenceOverDefaultValue() {
        project.steps = "2"
        assertEquals("2", parameter.steps)
    }
}
