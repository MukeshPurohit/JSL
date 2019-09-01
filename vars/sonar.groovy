#!/usr/bin/env groovy

def call(String mavenName = 'M3'){
    stage('Sonar'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                    withSonarQubeEnv('sonar') {
                        sh "mvn sonar:sonar"
                    }
                }catch (err){
                    echo 'Error in sonar processing'
                }
            }
        }
    }
}
