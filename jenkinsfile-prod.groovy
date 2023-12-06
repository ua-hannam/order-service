def app

node {
    stage('Checkout') {
        checkout scm 
    }

    stage('Ready') {      
        echo 'Ready to build'
        gradleHome = tool 'gradle' 
    }

    stage('Build') {
        sh "${gradleHome}/bin/gradle clean build"
    }
 
    stage('Build image') {
        app = docker.build('uahannam/order-service')
    }

    stage('Push image') {
        docker.withRegistry('http://211.205.161.133:5000', 'harbor') {
            app.push("latest")
        }
    }
}
