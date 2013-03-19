package com.github.marceloemanoel.gradle.migrations.tasks.parameters

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.cli.CommandLineArgumentException
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin

class DescriptionMigrateNewParametersTest {

    def Project project
    def MigrateNewParameters parameters

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin

        parameters = project.migrateNew.parameters
    }

    @Test
    void theDefaultValueIsNull(){
        assertNull(parameters.description)
    }

    @Test
    void parameter_d_takesPrecedenceOverDefaultValue() {
        project.d = "firstInPrecedence"
        assertEquals("firstInPrecedence", parameters.description)
    }

    @Test
    void parameter_desc_takesPrecedenceOverDefaultValue() {
        project.desc = "secondInPrecedence"
        assertEquals("secondInPrecedence", parameters.description)
    }

    @Test
    void parameter_description_takesPrecedenceOverDefaultValue() {
        project.description = "thirdInPrecedence"
        assertEquals("thirdInPrecedence", parameters.description)
    }

    @Test
    void parameter_d_takesPrecedenceOverTheOthers() {
        project.d = "firstInPrecedence"
        project.desc = "secondInPrecedence"
        project.description = "thirdInPrecedence"

        assertEquals("firstInPrecedence", parameters.description)
    }

    @Test
    void parameter_desc_takesPrecedenceOverDescription() {
        project.desc = "secondInPrecedence"
        project.description = "thirdInPrecedence"

        assertEquals("secondInPrecedence", parameters.description)
    }

    @Test
    void expectCommandLineErrorWhenValidateWithDefaultValue() {
        expectCommandLineError()
    }

    @Test
    void expectCommandLineErrorWhenValidateWithEmptyDescription(){
        project.d = ""
        expectCommandLineError()
    }

    private expectCommandLineError() {
        try {
            parameters.validate()
            fail()
        }
        catch(CommandLineArgumentException e){
            def msg = "Please provide a description for the new file:\n" +
                    "Usage: gradle migrateNew -Pdescription=\"your description here\" [-Ptemplate=\"template path\"]"
            assertEquals(msg, e.message)
        }
    }
}
