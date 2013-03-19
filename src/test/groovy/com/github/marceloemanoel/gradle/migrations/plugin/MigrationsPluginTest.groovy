package com.github.marceloemanoel.gradle.migrations.plugin

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.tasks.MigrateBootstrap
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateDown
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateInit
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateNew
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateStatus
import com.github.marceloemanoel.gradle.migrations.tasks.MigrateUp

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
        assertTrue MigrateInit.class.isInstance(task)
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateBootstrapTask(){
        Task task = project.tasks.migrateBootstrap
        assertNotNull task
        assertTrue MigrateBootstrap.class.isInstance(task)
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateStatusTask(){
        Task task = project.tasks.migrateStatus
        assertNotNull task
        assertTrue MigrateStatus.class.isInstance(task)
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateNewTask(){
        Task task = project.tasks.migrateNew
        assertNotNull task
        assertTrue MigrateNew.class.isInstance(task)
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateUpTask(){
        Task task = project.tasks.migrateUp
        assertNotNull task
        assertTrue MigrateUp.class.isInstance(task)
    }

    @Test
    void afterApplyPluginProjectShouldHaveMigrateDownTask(){
        Task task = project.tasks.migrateDown
        assertNotNull task
        assertTrue MigrateDown.class.isInstance(task)
    }

}
