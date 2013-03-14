package com.github.marceloemanoel.gradle.migrations.tasks

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.cli.CommandLineArgumentException;
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin

class NewTaskTest {

    def Project project
    def NewTask task

    @Before
    void setUp(){
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
        task = project.migrateNew
    }

    @Test
    void ifANullDescriptionIsGivenTheBuildFails(){
        task.fileDescription = null
        expectCommandLineError()
    }
	
    @Test
    void ifAnEmptyDescriptionIsGivenTheBuildFails(){
        task.fileDescription = ""
        expectCommandLineError()
    }
    
    private expectCommandLineError() {
        try {
            task.executeMigrations()
            fail()
        }
        catch(CommandLineArgumentException e){
            def msg = "Please provide a description for the new file:\n" +
                    "Usage: gradle migrateNew -Pdescription=\"your description here\" [-Ptemplate=\"template path\"]"
            assertEquals(msg, e.message)
        }
    }
}
