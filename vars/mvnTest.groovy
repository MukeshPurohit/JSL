#!/usr/bin/env groovy

def call(String mavenName = 'M3'){
    stage('Tests'){
        withMaven(
            maven: "${mavenName}"
        ){
            sh "mvn test"
        }
    }
}
