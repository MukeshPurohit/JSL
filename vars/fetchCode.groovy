#!/usr/bin/env groovy
import com.bt.java.BuildConfig
def call(def body = [:]) {
    config = BuildConfig.resolve(body)
    stage('Checkout Git repo'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                      git url: "${config.GitURL}"
                }catch (err){
                    echo 'Error while retrieving maven repo ' "${config.GitURL}"
                }
            }
        }
    }
}
