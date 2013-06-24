package com.github.marceloemanoel.gradle.migrations.helper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.ibatis.migration.commands.BaseCommand;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.marceloemanoel.gradle.migrations.helper.ClassLoaderProvider;
import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin;

public class ClassLoaderProviderTest {
    
    @Mock
    def BaseCommand command
    def ClassLoaderProvider provider
    def Project project
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
        project.configurations {
            compile
        }
        provider = new ClassLoaderProvider(project);
    }

    @Test
    void testUpdateDriverClassLoader() {
        assertNotNull provider.driverClassLoader
    }

    @Test
    void configurationReturnsMigrationDriverIfItIsDefined() {
        project.dependencies {
            migrationsDriver 'mysql:mysql-connector-java:5+'
        }
        provider = new ClassLoaderProvider(project);
        assertEquals("migrationsDriver", provider.configuration().getName());
    }
    
    @Test
    void configurationReturnsCompileIfMigrationDriverIsNotDefined() {
        assertEquals("compile", provider.configuration().getName());
    }

}
