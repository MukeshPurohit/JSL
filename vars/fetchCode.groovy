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
                }catch (err)
                {
                    echo 'Error while retrieving maven repo ' "${config.GitURL}"
/*                  String recipient = 'deg@bt.com'
                    mail subject: "${env.JOB_NAME} (${env.BUILD_NUMBER}) failed",
                    body: "It appears that ${env.BUILD_URL} is failing for code ${config.GitURL} ",
                    to: recipient,
                    replyTo: recipient,
                    from: 'noreply@bt.com'
*/
                }
            }
        }
    }
}
