import com.bt.java.BuildConfig

def call(def body = [:]) {
    config = BuildConfig.resolve(body)
    }
