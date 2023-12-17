def app
def massage = "_${env.JOB_NAME}_[#${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>"

node {
    try {
    //Slack send notify - start
    slackSend(channel: '#backend-bulid-log', message: "*Build start(${massage})*")
        
    stage('Checkout') {
        checkout scm 
    }

    stage('Ready') {      
        echo 'Ready to build' 
        gradleHome = tool 'gradle'   
    }

    stage('Build') {
        sh "${gradleHome}/bin/gradle clean build -x test"
    }

    //Slack send notify - result
    slackSend(channel: '#backend-bulid-log', color: '#00FF00', message: """
*Build successful*
Job : ${massage}
""")
    } catch (Exception e) {
        slackSend(channel: '#backend-bulid-log', color: 'danger', message: """ 
*Build failed*
Job : ${massage}
""")
    }
}
