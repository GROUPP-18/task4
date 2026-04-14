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
    } // <--- End of stages

    post { // <--- This must start right after stages ends
        always {
            // This 'bat' command uses Windows 'curl' to send your Slack message
            // BE CAREFUL: Replace the URL below with your actual Slack Webhook URL
            bat """
            curl -X POST -H "Content-type: application/json" --data "{\\"text\\":\\"Build #${env.BUILD_NUMBER} for ${env.JOB_NAME} is ${currentBuild.currentResult}\\"}" https://hooks.slack.com/services/YOUR/REAL/WEBHOOK/HERE
            """
        }
        
        failure {
            echo 'Build Failed! You can add your Teams/Email code here next.'
        }
        
        success {
            echo 'Build Succeeded!'
        }
    } // <--- End of post
} // <--- End of pipeline
