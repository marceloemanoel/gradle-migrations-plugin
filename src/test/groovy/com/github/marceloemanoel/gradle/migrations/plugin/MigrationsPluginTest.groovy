package com.github.marceloemanoel.gradle.migrations.plugin

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.tasks.BootstrapTask
import com.github.marceloemanoel.gradle.migrations.tasks.DownTask
import com.github.marceloemanoel.gradle.migrations.tasks.InitTask
import com.github.marceloemanoel.gradle.migrations.tasks.UpTask

class MigrationsPluginTest {

    def Project project;

    @Before
    public void setUp(){
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateInitTask(){
        Task task = project.tasks.migrateInit
        assertNotNull task
        assertTrue InitTask.class.isInstance(task)
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateBootstrapTask(){
        Task task = project.tasks.migrateBootstrap
        assertNotNull task
        assertTrue BootstrapTask.class.isInstance(task)
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateUpTask(){
        Task task = project.tasks.migrateUp
        assertNotNull task
        assertTrue UpTask.class.isInstance(task)
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateDownTask(){
        Task task = project.tasks.migrateDown
        assertNotNull task
        assertTrue DownTask.class.isInstance(task)
    }

}
