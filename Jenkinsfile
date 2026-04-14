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
            // This sends a notification to Slack every time the build runs
            bat """
            curl -X POST -H "Content-type: application/json" --data "{\\"text\\":\\"Build #${env.BUILD_NUMBER} for ${env.JOB_NAME} is ${currentBuild.currentResult}\\nLink: ${env.BUILD_URL}\\"}" https://hooks.slack.com/services/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
            """
        }
        
        failure {
            echo 'Build Failed! Follow the same steps to add your Teams webhook here next.'
        }
    }
}
