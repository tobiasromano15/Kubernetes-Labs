pipeline {

    agent { kubernetes { defaultContainer 'jnlp' } }

    stages {
        stage('CI') {
            steps { sh 'echo "Running on $HOSTNAME" && sleep 60' }
        }
    }
}