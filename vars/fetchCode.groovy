#!/usr/bin/env groovy

def call(String mavenName = 'M3'){
    stage('Checkout'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                  git url: 'https://github.com/jglick/simple-maven-project-with-tests.git'
                }catch (err){
                    echo 'Error while Repo fetch'
                }
            }
        }
    }
}
