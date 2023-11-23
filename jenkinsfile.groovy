def app

node {
    stage('Checkout') {
        checkout scm
    }

    stage('Ready') {
        echo 'Ready to build'
        gradleHome = tool 'Gradle'
    }

    stage('Build') {
        steps {
            sh "${gradleHome}/bin/gradle clean build"
        }
    }

    stage('Build image') {
        app = docker.build('192.168.45.205/uahannam/order-service')
    }

    stage('Push image') {
        docker.withRegistry('https://192.168.45.205', 'harbor')
        app.push("${env.BUILD_NUMBER}")
        app.push("latest")
    }

    post {
        success {
            slackSend (
                    channel: '#build-log',
                    color: '#00FF00',
                    message: """
SUCCESS 
Job : ${env.JOB_NAME} - [#${env.BUILD_NUMBER}]
<${env.BUILD_URL}|OPEN>
"""
            )
        }
        failure {
            slackSend (
                    channel: '#build-log',
                    color: '#FF0000',
                    message: """
FAIL 
- Job : ${env.JOB_NAME} - [#${env.BUILD_NUMBER}]
<${env.BUILD_URL}|OPEN>
} 
                """
            )
        }
    }
}
