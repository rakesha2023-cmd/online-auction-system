pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    environment {
        IMAGE_NAME = 'YOUR_DOCKERHUB_USERNAME/online-auction-system:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rakesha2023-cmd/online-auction-system.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Configure Docker Hub credentials before enabling this stage'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Configure kubectl/minikube before enabling this stage'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}