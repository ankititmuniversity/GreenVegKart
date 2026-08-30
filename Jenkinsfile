pipeline {
    agent any
    stages {
        stage('Build1') {
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
                bat 'xcopy /Y /E target\\MyVegKart.war C:\\Users\\ankit\\tomcat\\apache-tomcat-9.0.113\\webapps'
            }
        }
    }
}
