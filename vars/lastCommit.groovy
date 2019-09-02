#!/usr/bin/env groovy
import com.bt.java.BuildConfig
def call(def body = [:]) 
{
    config = BuildConfig.resolve(body)
    stage('Checkout Git repo')
    {
        withMaven(
            maven: "${mavenName}"
        )
        {
            script
            {
                try
                {
                      git url: "${config.GitURL}"
                      def lastSuccessfulCommit = getLastSuccessfulCommit()
                      def currentCommit = commitHashForBuild( currentBuild.rawBuild )
                      if (lastSuccessfulCommit) 
                      {
                        commits = sh(
                          script: "git rev-list $currentCommit \"^$lastSuccessfulCommit\"",
                          returnStdout: true
                        ).split('\n')
                        println "Commits are: $commits"
                      }

                }catch (err)
                {
                    echo 'Error while retrieving maven repo ' "${config.GitURL}"
                }
            }
        }
    }
}

def getLastSuccessfulCommit() 
{
  def lastSuccessfulHash = null
  def lastSuccessfulBuild = currentBuild.rawBuild.getPreviousSuccessfulBuild()
  if ( lastSuccessfulBuild ) 
  {
    lastSuccessfulHash = commitHashForBuild( lastSuccessfulBuild )
  }
  return lastSuccessfulHash
}

def commitHashForBuild( build ) 
{
  def scmAction = build?.actions.find { action -> action instanceof jenkins.scm.api.SCMRevisionAction }
  return scmAction?.revision?.hash
}
