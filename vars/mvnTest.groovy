#!/usr/bin/env groovy
import com.bt.java.BuildConfig
def call(def body = [:]) {
    config = BuildConfig.resolve(body)
    stage('Unit Testing'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                      echo "${mavenName}"
                       mvn test
                       echo 'Unit Testing completed successfully'
                }catch (err){
                    echo 'Error while perfomring Unit Testing of maven repo'
                }
            }
        }
    }
}

/*
def call(String mavenName = 'M3'){
    stage('Tests'){
        withMaven(
            maven: "${mavenName}"
        ){
            sh "mvn test"
        }
    }
}
*/
