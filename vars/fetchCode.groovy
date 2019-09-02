#!/usr/bin/env groovy
import com.bt.java.BuildConfig
def call(def body = [:]) {
    echo "calling resolve method of class"
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
