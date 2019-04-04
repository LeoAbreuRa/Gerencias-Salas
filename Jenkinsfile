#!groovy

pipeline {
  agent any
  stages {
        
        stage('Docker Build') {
            agent any
            steps {
                sh 'docker build -f Dockerfile -t demo/oracle-java:8 .'
            }
        }
        stage('Docker Compile Class') {
            agent any
            steps {
                sh 'cd src/java/br/com/container/util/ && docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 javac InicializadorProjeto.java'
            }
        }    
        stage('Docker Execute Java Class') {
            agent any
            steps {
                sh 'cd src/java/br/com/container/util/ && docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 javac InicializadorProjeto.java'
            }
        }        
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }

}
