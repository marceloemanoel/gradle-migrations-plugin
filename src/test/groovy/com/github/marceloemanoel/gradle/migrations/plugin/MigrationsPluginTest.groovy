package com.github.marceloemanoel.gradle.migrations.plugin

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.tasks.InitTask
import com.github.marceloemanoel.gradle.migrations.tasks.UpTask;

class MigrationsPluginTest {
    
    def Project project;
    
    @Before
    public void setUp(){
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
    }
    
    @Test
    void afterApplyPluginProjectShouldHaveMigrateInitTask(){
        Task initTask = project.tasks.migrateInit
        assertNotNull initTask 
        assertTrue InitTask.class.isInstance(initTask)
    }
    
    @Test
    void afterApplyPluginProjectShouldHaveMigrateUpTask(){
        Task upTask = project.tasks.migrateUp
        assertNotNull upTask
        assertTrue UpTask.class.isInstance(upTask)
    }
}
