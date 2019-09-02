import com.bt.java.BuildConfig

def call(def body = [:]) {
    echo "calling resolve method of class"
    config = BuildConfig.resolve(body)
    config.each{ k, v -> println "${k} <--> ${v}" }
    
    println "***inside loadBody.groovy************** ${config.GitURL}"

    }
