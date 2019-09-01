#!/usr/bin/env groovy

def call(String mavenToUse = 'M3'){
    stage('Results'){
        withMaven(
            maven: "${mavenToUse}"
        ){
          script{
                try{
                        junit '**/target/surefire-reports/TEST-*.xml'
                        archiveArtifacts 'target/*.jar'
                }catch (err){
                    echo 'Error while displaying results'
                }
            }
    }
}
}
