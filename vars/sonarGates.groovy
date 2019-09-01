stage("Quality Gate"){
           steps {
                script {
                
                    timeout(time: 160, unit: 'SECONDS') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
