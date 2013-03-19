package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin;
import com.github.marceloemanoel.gradle.migrations.tasks.MigrationTask;

class EnvironmentMigrationParametersTest {

    def Project project
    def MigrationParameters parameters

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin

        parameters = project.migrateUp.parameters
    }

    @Test
    void theDefaultValueIsDevelopment() {
        assertEquals("development", parameters.environment)
    }

    @Test
    void migrations_environmentTakesPrecedenceOverTheDefaultValue(){
        project.migrations.environment = "test"
        assertEquals("test", parameters.environment)
    }

    @Test
    void parameter_e_TakesPrecedenceOverTheDefaultValue() {
        project.e = "environment"
        assertEquals("environment", parameters.environment)
    }

    @Test
    void parameter_env_TakesPrecedenceOverTheDefaultValue() {
        project.env = "myEnvironment"
        assertEquals("myEnvironment", parameters.environment)
    }

    @Test
    void parameter_environment_TakesPrecedenceOverTheDefaultValue() {
        project.environment = "completeEnvironment"
        assertEquals("completeEnvironment", parameters.environment)
    }

    @Test
    void parameter_e_takesPrecedenceOverTheOthers() {
        project.e = "firstInPrecedence"
        project.env = "secondInPrecedence"
        project.environment = "thirdInPrecedence"
        project.migrations.environment = "fourthInPrecedence"
        
        assertEquals("firstInPrecedence", parameters.environment)
    }

    @Test
    void parameter_env_takesPrecedenceOverEnvironmentAndMigrationsEnvironment() {
        project.env = "secondInPrecedence"
        project.environment = "thirdInPrecedence"
        project.migrations.environment = "fourthInPrecedence"
        
        assertEquals("secondInPrecedence", parameters.environment)
    }
    
    @Test
    void parameter_environment_takePrecedenceOverMigrationsEnvironment() {
        project.environment = "thirdInPrecedence"
        project.migrations.environment = "fourthInPrecedence"
        
        assertEquals("thirdInPrecedence", parameters.environment)
    }
    
}
