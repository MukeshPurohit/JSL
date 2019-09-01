#!/usr/bin/env groovy

def call(String mavenToUse = 'M3'){
    stage('Package'){
        withMaven(
            maven: "${mavenToUse}"
        ){
          script{
                try{
                    sh "mvn clean install -DskipTests -DskipITs"
                }catch (err){
                    echo 'Error while Repo fetch'
                }
            }
    }
}
}
