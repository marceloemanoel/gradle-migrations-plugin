package com.github.marceloemanoel.gradle.migrations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.ibatis.migration.commands.BaseCommand;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper;
import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin;

public class CommandHelperTest {
    
    @Mock
    def BaseCommand command
    def CommandHelper commandHelper
    def Project project
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
        project.configurations {
            compile
        }
        commandHelper = new CommandHelper(project);
    }

    @Test
    void testUpdateDriverClassLoader() {
        assertNotNull commandHelper.driverClassLoader
    }

    @Test
    void configurationReturnsMigrationDriverIfItIsDefined() {
        project.dependencies {
            migrationsDriver 'mysql:mysql-connector-java:5+'
        }
        commandHelper = new CommandHelper(project);
        assertEquals("migrationsDriver", commandHelper.configuration().getName());
    }
    
    @Test
    void configurationReturnsCompileIfMigrationDriverIsNotDefined() {
        assertEquals("compile", commandHelper.configuration().getName());
    }

}
