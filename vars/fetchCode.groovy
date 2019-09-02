#!/usr/bin/env groovy
import com.bt.java.BuildConfig

def call(def body = [:]) {
    echo "calling resolve method of class"
    config = BuildConfig.resolve(body)
    config.each{ k, v -> println "${k} <--> ${v}" }
    echo "***inside fetchCode.groovy**************"config.GitURL
    }
/*
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
*/
