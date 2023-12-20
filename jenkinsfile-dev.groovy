def app
def massage = "_${env.JOB_NAME}_[#${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>"

node {
    try {
    //Slack send notify - start
    slackSend(channel: '#bulid-log', message: "*Build start(${massage})*")
        
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

    stage('SonarQube Analysis') {
        withSonarQubeEnv() {
            sh "./gradlew -Dsonar.login=squ_d3bfd77d128148710aadd41852ce48d5fcd078b9 sonar"
        }
    }

    //Slack send notify - result
    slackSend(channel: '#bulid-log', color: '#00FF00', message: """
*Build successful*
Job : ${massage}
""")
    } catch (Exception e) {
        slackSend(channel: '#bulid-log', color: 'danger', message: """ 
*Build failed*
Job : ${massage}
""")
    }
}
