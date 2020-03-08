pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                git 'https://github.com/xuzhe0205/koowakchai-platform-springmvc'
                sh 'mvn -B -DskipTests clean package'
            }
        }
         stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}