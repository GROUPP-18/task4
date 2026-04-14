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

        stage('Code Quality (SonarQube)') {
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

    // THIS IS THE UPDATED NOTIFICATION BLOCK FOR TASK 7
    post {
        always {
            // This sends a message to your '#jenkins-build' channel every time
            slackSend(
                channel: '#jenkins-build', 
                color: currentBuild.currentResult == 'SUCCESS' ? 'good' : 'danger',
                tokenCredentialId: 'slack-webhook', 
                message: "Build: ${env.JOB_NAME} - #${env.BUILD_NUMBER}\nStatus: ${currentBuild.currentResult}\nLink: ${env.BUILD_URL}"
            )
        }
        
        failure {
            // This part runs ONLY if the build fails
            echo 'Build Failed! Notifying the team via Email and Teams...'
            
            // We will add the specific 'emailext' and 'office365' code here 
            // once you have the plugins for those configured.
        }
        
        success {
            echo 'Build Succeeded!'
        }
    }
}
