#!/usr/bin/env groovy

def call(String mavenName = 'M3'){
    stage('Checkout'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                  sh "git 'https://github.com/jglick/simple-maven-project-with-tests.git'"
                }catch (err){
                    echo 'Error while Repo fetch'
                }
            }
        }
    }
}
