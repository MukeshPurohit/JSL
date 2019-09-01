#!/usr/bin/env groovy

def call(String mavenName = 'M3'){
    stage('Quality Gate'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                    withSonarQubeEnv('sonar') {
                                 timeout(time: 160, unit: 'SECONDS') {
                                            def qg = waitForQualityGate()
                                            if (qg.status != 'OK') {
                                                       error "Pipeline aborted due to quality gate failure: ${qg.status}"
                                                      }
                                                   }
                                              }
                }catch (err){
                               echo 'Error in sonar processing. Quality gate failure...'
                           }
                }
        }
    }
}
