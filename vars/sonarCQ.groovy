#!/usr/bin/env groovy

def call(String mavenName = 'M3'){
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    try {
        body()
    } catch(e) {
        currentBuild.result = "FAILURE";
        throw e;
    } finally {

        config.each{ k, v -> println "${k}::::${v}" }

    }
    
    stage('Sonar'){
        withMaven(
            maven: "${mavenName}"
        ){
            script{
                try{
                    withSonarQubeEnv('sonar') {
                        sh "mvn sonar:sonar"
                    }
                }catch (err){
                    echo 'Error in sonar processing'
                }
            }
        }
    }
}
