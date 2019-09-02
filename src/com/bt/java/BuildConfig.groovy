package com.bt.java
import com.cloudbees.groovy.cps.NonCPS

class BuildConfig implements Serializable {
    static Map resolve(def body = [:]) {

        Map config = [:]
        config = body
        echo "inside buildConfig class"

        if (body in Map) {
            config = body
        } else if (body in Closure) {
            body.resolveStrategy = Closure.DELEGATE_FIRST
            body.delegate = config
            body()
        } else {
            throw  new Exception(sprintf("Unsupported build config type:%s", [config.getClass()]))
        }
        return config
    }
}
