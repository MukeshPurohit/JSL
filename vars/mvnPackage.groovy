#!/usr/bin/env groovy
import com.bt.java.BuildConfig
def call(def body = [:]) 
{
    config = BuildConfig.resolve(body)
    stage('Package')
    {
        withMaven(maven: "${mavenName}")
        {
            script
            {
                try
                {
                    sh "mvn clean install -DskipTests -DskipITs"
                }catch (err)
                {
                    echo 'Error during packaging of REPO ' "${config.GitURL}"
                }
            }
        }
    }
}

/*
#!/usr/bin/env groovy

def call(String mavenToUse = 'M3'){
    stage('Package'){
        withMaven(
            maven: "${mavenToUse}"
        ){
          script{
                try{
                    sh "mvn clean install -DskipTests -DskipITs"
                }catch (err){
                    echo 'Error while packaging code'
                }
            }
    }
}
}
*/


