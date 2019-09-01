#!/usr/bin/env groovy

def call(String mavenToUse = 'M3'){
    stage('Package'){
        withMaven(
            maven: "${mavenName}"
        ){
            sh "mvn clean install -DskipTests -DskipITs"
        }
    }
