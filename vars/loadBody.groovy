import com.bt.java.BuildConfig

def call(def body = [:]) {
    echo "calling resolve method of class"
    config = BuildConfig.resolve(body)
    }
