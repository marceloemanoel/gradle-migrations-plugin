package com.github.marceloemanoel.gradle.migrations.plugin

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

class MigrationsPluginTest {
    
    def Project project;
    
    @Before
    public void setUp(){
        project = ProjectBuilder.builder().build()
        new MigrationsPlugin().apply(project)
    }
    
    @Test
    void afterApplyMyPluginProjectShouldHave(){
        //test code goes here...
    }
}
