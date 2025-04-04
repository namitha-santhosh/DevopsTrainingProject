pipeline {
    agent any
    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK17'
    }
    stages {
        stage('Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/namitha-santhosh/DevopsTrainingProject.git'
            }
        }
        stage('Build') {
            steps {
                dir('Employee-Project/employeeapp') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                dir('Employee-Project/employeeapp') {
                    withSonarQubeEnv('Sonar-server') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
        stage('Upload to Nexus') {
            steps {
                dir('Employee-Project/employeeapp') {
                    sh 'mvn deploy'
                }
            }
        }
        stage('Image Build') {
            steps {
                script {
                    sh "docker build -t namitha612/jenkins-app:latest ."
                }
            }
        }
        stage('Push to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-pat', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh """
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push namitha612/jenkins-app:latest
                    """
                    }
                }
            }
        }

    }
}
