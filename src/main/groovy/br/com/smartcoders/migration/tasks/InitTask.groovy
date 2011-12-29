package br.com.smartcoders.migration.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.JavaExec;
import org.gradle.api.tasks.TaskAction;

class InitTask extends JavaExec {
  
  File baseDir;
  
  public InitTask(){
    setDescription("Create migrations structure");
    setGroup("Migration");
  }
  
  void createFileFromTemplates(file, template){
    String content = InitTask.class.getResourceAsStream("/templates/${template}").text
    
    file << content
  }
  
  void copyEnvironment(environments) {
    String resource = InitTask.class.getResourceAsStream("/templates/environments.properties").text;
    File output = new File(environments, "development.properties");
    
    output << resource  
  }

    @TaskAction
  void createDirectories() {
    logger.info "Creating directory."
    
    File environments = new File("environments", baseDir);
    File scripts = new File("scripts", baseDir);
    
    [environments, scripts].each {
      it.mkdirs();
    }
    
    copyEnvironment(environments)
    
    logger.info "Directory created at '${baseDir.absolutePath}'."
  }
}
