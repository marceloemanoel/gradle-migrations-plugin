package com.github.marceloemanoel.gradle.migrations.tasks

import static org.junit.Assert.*

import java.sql.Timestamp
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After;
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin

class InitTaskTest {

    def Project project
    def InitTask task

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
        task = project.tasks.migrateInit
    }

    @After
    void tearDown() {
        baseDir().deleteDir()
    }

    @Test
    void ifBaseDirExistsAndForceIsFalseThanBaseDirCantBeOverwritten(){
        baseDir().mkdirs()

        def file = timestampFile()
        file.createNewFile()

        task.createDirectories()

        assertTrue(file.exists())
    }

    @Test
    void ifBaseDirExistsAndForceIsTrueThanBaseDirMustBeOverwritten() {
        baseDir().mkdirs()

        project.migrations.force = true

        def file = timestampFile()
        file.createNewFile()

        task.createDirectories()

        assertFalse(file.exists())
    }
    
    @Test 
    void ifBaseDirDoesntExistsThanItMustBeCreated(){
        assertFalse(baseDir().exists())
        task.createDirectories()
        
        assertTrue(baseDir().exists())
        assertTrue(new File(baseDir(), "environments").exists())
        assertTrue(new File(baseDir(), "scripts").exists())
        assertFalse(new File(baseDir(), "drivers").exists())
        assertFalse(new File(baseDir(), "README").exists())    
    }
    
    private String currentTimestamp() {
        def timestamp = new Timestamp(System.currentTimeMillis())
        timestamp.toString().replaceAll("[- :\\.]", "")
    }

    private File timestampFile() {
        project.file(new File(project.migrations.baseDir, currentTimestamp()))
    }

    private File baseDir(){
        project.file(project.migrations.baseDir)
    }

}
