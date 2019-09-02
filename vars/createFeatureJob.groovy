#!/usr/bin/env groovy

import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import org.jenkinsci.plugins.workflow.flow.FlowDefinition
import hudson.plugins.git.GitSCM
import hudson.plugins.git.UserRemoteConfig
import com.cloudbees.hudson.plugins.folder.*
import com.bt.java.BuildConfig
import hudson.model.*
/*
String folderName = "$folderName"
String jobName = "$jobName"
String jobScript = "$jobScript"
String gitRepo = "$gitRepo"
String gitRepoName = "$gitRepoName"
String credentialsId = "$credentialsId"
*/

def call(def body = [:]) 
{
    String folderName = "feature"
    String jobName = "ABC"
    String jobScript = "Jenkinsfile"
    String gitRepo = "https://github.com/jglick"
    String gitRepoName = "simple-maven-project-with-tests"
    String credentialsId = "null"

    Jenkins jenkins = Jenkins.instance
    config = BuildConfig.resolve(body)
    def folder = jenkins.getItem(folderName)
    if (folder == null) 
    {
      folder = jenkins.createProject(Folder.class, folderName)
    }

    UserRemoteConfig userRemoteConfig = new UserRemoteConfig(gitRepo, gitRepoName, null, credentialsId)

    branches = null
    doGenerateSubmoduleConfigurations = false
    submoduleCfg = null
    browser = null
    gitTool = null
    extensions = []

    GitSCM scm = new GitSCM([userRemoteConfig], branches, doGenerateSubmoduleConfigurations, submoduleCfg, browser, gitTool, extensions)
    FlowDefinition flowDefinition = (FlowDefinition) new CpsScmFlowDefinition(scm, jobScript)

    Object job = null
    job = folder.getItem(jobName)
    if (job == null) 
    {
      oldJob = jenkins.getItem(jobName)
      if (oldJob.getClass() == WorkflowJob.class) 
      {
        // Move any existing job into the folder
        Items.move(oldJob, folder)
      } else 
      {
        // Create it if it doesn't
        job = folder.createProject(WorkflowJob, jobName)
      }
    }
    // Add the workflow to the job
    job.setDefinition(flowDefinition)

    // Set the branch somehow
    job.save()

}



/*

def call(def body = [:]) 
{
    config = BuildConfig.resolve(body)
    hudson.model.Hudson.instance.items.findAll{job -> job}.each 
    {
    job -> println("Job: " + job.name)
    }
}
    stage('Checkout Git repo'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                      git url: "${config.GitURL}"
                }catch (err){
                    echo 'Error while retrieving maven repo ' "${config.GitURL}"
                }
            }
        }
    }
}
*/
