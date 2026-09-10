pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Vedhawathy/aws-devops-project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t vedhawathy/aws-devops-app:latest .'
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
                    docker push vedhawathy/aws-devops-app:latest
                    '''
                }
            }
        }
        stage('Deploy') {

    steps {

        sh '''
        docker stop devops-app || true
        docker rm devops-app || true

        docker pull vedhawathy/aws-devops-app:latest

        docker run -d \
        --name devops-app \
        -p 8081:8080 \
        vedhawathy/aws-devops-app:latest
        '''
    }
}

    }
}
