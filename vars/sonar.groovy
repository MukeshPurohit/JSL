#!/usr/bin/env groovy

def call(String mavenName = 'M3'){
    stage('Sonar'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                  sh "mvn sonar:sonar"
                }catch (err){
                    echo 'Ate the error in sonar'
                }
            }
        }
    }
}
