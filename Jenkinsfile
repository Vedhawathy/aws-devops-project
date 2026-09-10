pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/YOUR_USERNAME/aws-devops-project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t YOUR_DOCKERHUB_USERNAME/aws-devops-app:latest .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {

                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push YOUR_DOCKERHUB_USERNAME/aws-devops-app:latest
                    '''
                }
            }
        }
        stage('Deploy') {

    steps {

        sh '''
        docker stop devops-app || true
        docker rm devops-app || true

        docker pull YOUR_DOCKERHUB_USERNAME/aws-devops-app:latest

        docker run -d \
        --name devops-app \
        -p 8081:8080 \
        YOUR_DOCKERHUB_USERNAME/aws-devops-app:latest
        '''
    }
}

    }
}
