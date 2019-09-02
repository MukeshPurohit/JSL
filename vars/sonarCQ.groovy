#!/usr/bin/env groovy
import com.bt.java.BuildConfig
def call(def body = [:]) 
{
    config = BuildConfig.resolve(body)
    stage('Sonar')
    {
        withMaven(maven: "${mavenName}")
        {
            script
            {
                try
                {
                    withSonarQubeEnv('sonar') 
                    {
                        sh "mvn sonar:sonar"
                    }
                }catch (err)
                {
                    echo 'Error in sonar processing' "${config.SonarURL}" 'on REPO ' "${config.GitURL}"
                }
            }
        }
    }
}
