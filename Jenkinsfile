pipeline {
    agent {
        label 'java-build-agent' 
    }

    tools {
        maven 'khalidabdi1' 
    }

    environment {
        DEPLOY_TARGET = 'staging'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/GROUPP-18/task4.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Code Quality') {
            steps {
                echo 'Simulating SonarQube Analysis...'
            }
        }

        stage('Deploy') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo "Deploying to ${env.DEPLOY_TARGET}..."
            }
        }
    }

    post {
        always {
            // Task 7.1: Professional Slack Notification
            // Ensure you have a 'Secret Text' credential in Jenkins with ID 'slack-secret'
            slackSend(
                channel: '#jenkins-build', 
                color: currentBuild.currentResult == 'SUCCESS' ? 'good' : 'danger',
                tokenCredentialId: 'slack-secret', 
                message: "Build: ${env.JOB_NAME} - #${env.BUILD_NUMBER}\nStatus: ${currentBuild.currentResult}\nLink: ${env.BUILD_URL}"
            )
        }
        
        failure {
            // Placeholder for Teams and Email (Task 7.2 & 7.3)
            echo 'Build Failed! Follow the same logic for Teams and Email next.'
        }
    }
}
