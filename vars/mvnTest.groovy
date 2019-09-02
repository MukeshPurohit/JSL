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
                       sh "mvn test"
                }catch (err){
                    echo 'Error while perfomring Unit Testing of maven repo'
                }
            }
        }
    }
}
