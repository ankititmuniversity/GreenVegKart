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
                bat 'xcopy /Y /E target\\MyVegKart-0.0.1-SNAPSHOT.war C:\\Users\\ankit\\tomcat\\apache-tomcat-9.0.113\\webapps\\MyVegKart.war'
            }
        }
        stage('Publish Extent Report') {
            steps {
                publishHTML([
                    reportDir: 'extentReports',
                    reportFiles: 'report.html',
                    reportName: 'Extent Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                ])
            }
        }
    }
}
