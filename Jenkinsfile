pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                bat 'xcopy /Y /E target\\MyVegKart.war C:\\apache-tomcat-9.0.xx\\webapps\\'
            }
        }
    }
}
