package com.github.marceloemanoel.gradle.migrations.tasks

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin;

class MigrationTaskTest {

    def Project project
    def MigrationTask task

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin

        task = project.migrateUp
    }

    @Test
    void ifNothingIsSpecifiedTheSelectedEnvironmentIsTakenFromDefaultValueDevelopment() {
        assertEquals("development", task.environment)
    }

    @Test
    void environmentOfExtensionPointTakesPrecedenceOverTheDefaultValue(){
        project.migrations.environment = "test"
        assertEquals("test", task.environment)
    }

    @Test
    void parameter_e_TakesPrecedenceOverTheDefaultValueOfEnvironment() {
        project.e = "environment"
        assertEquals("environment", task.environment)
    }

    @Test
    void parameter_env_TakesPrecedenceOverTheDefaultValueOfEnvironment() {
        project.env = "myEnvironment"
        assertEquals("myEnvironment", task.environment)
    }

    @Test
    void parameter_environment_TakesPrecedenceOverTheDefaultValueOfEnvironment() {
        project.environment = "completeEnvironment"
        assertEquals("completeEnvironment", task.environment)
    }

    @Test
    void parameter_e_takesPrecedenceOverTheOthersEnvironmentParameters() {
        project.e = "firstInPrecedence"
        project.env = "secondInPrecedence"
        project.environment = "thirdInPrecedence"
        project.migrations.environment = "fourthInPrecedence"
        
        assertEquals("firstInPrecedence", task.environment)
    }

    @Test
    void parameter_env_takesPrecedenceOverEnvironmentAndMigrationsEnvironmentParameters() {
        project.env = "secondInPrecedence"
        project.environment = "thirdInPrecedence"
        project.migrations.environment = "fourthInPrecedence"
        
        assertEquals("secondInPrecedence", task.environment)
    }
    
    @Test
    void parameter_environment_takePrecedenceOverMigrationsEnvironmentParameter() {
        project.environment = "thirdInPrecedence"
        project.migrations.environment = "fourthInPrecedence"
        
        assertEquals("thirdInPrecedence", task.environment)
    }


    @Test
    void ifNothingIsSpecifiedParameterForceIsFalse() {
        assertFalse(task.force)
    }
    
    @Test
    void forceOfExtensionPointTakesPrecedenceOverDefaultValue() {
        project.migrations.force = true
        assertTrue(task.force)
    }
    
    @Test
    void parameter_f_takesPrecedenceOverOtherForceParameters() {
        project.f = true
        project.migrations.force = false
        
        assertTrue(task.force)
    }
    
    @Test
    void ifNothingIsSpecifiedParameterBaseDirIsMigrations() {
        assertEquals(project.file("migrations"), task.baseDir)
    }
    
    @Test
    void parameter_b_takesPrecedenceOverBaseDirDefaultValue() {
        project.b = "sql"
        assertEquals(project.file("sql"), task.baseDir)
    }
    
}
