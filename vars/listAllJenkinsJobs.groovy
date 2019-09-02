#!/usr/bin/env groovy
import com.bt.java.BuildConfig
import hudson.model.*


def call(def body = [:]) 
{
    config = BuildConfig.resolve(body)
    hudson.model.Hudson.instance.items.findAll{job -> job}.each 
    {
    job -> println("Job: " + job.name)
    }
}
/*
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
*/
