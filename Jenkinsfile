pipeline {

  agent any

  parameters {
    string(name: 'server', defaultValue: "C:\\HexawareTraining\\Cohort1\\JenkinsLabs\\apache-tomcat-")
    string(name: 'emailTo', defaultValue: "timothyjames.short@gmail.com")
  }

  triggers {
    pollSCM('* * * * *')
  }

  tools {
    maven 'jenkinsMaven'
  }

  stages {
    stage('Build') {
      steps {
          bat """
            cd freddie-app
            mvn clean package
          """
        }
    }
    stage('Deploy to Staging') {
      steps {
        echo 'Deploy to staging environment'

        // Launch tomcat
        bat """
          cd ${params.server}qa\\bin
          startup
        """
        bat """
          cd ${params.server}staging\\bin
          startup
        """

        // Code to move WAR to Tomcat
        bat "xcopy /y freddie-app\\webapp\\target\\webapp.war ${params.server}qa\\webapps"
        bat "xcopy /y freddie-app\\webapp\\target\\webapp.war ${params.server}staging\\webapps"
      }
      post {
        success {
          emailNotification('Successfully Deployed to Staging')
        }
        failure {
          emailNotification('FAILED to deploy to staging')
        }

      } //end of post

    } //end of stage

    stage('Automated Tests') {
      steps {
          bat """
            cd freddie-Tests
            mvn clean package
          """
        }
    }

    stage('Acceptance Tests') {
      steps {
          bat """
            cd freddie-cucumber
            mvn clean package
          """
        }
    }

    stage('Deploy to production') {
      steps {

        //Send email to test lead, release manager
        //READY FOR APPROVAL (will expire in 24 hours)
        timeout(time:1, unit:"DAYS"){
          input message: 'WARNING: Deploy to LIVE production'
        }

        echo 'Deploy to production environment'

        // Launch tomcat
        bat """
          cd ${params.server}prod\\bin
          startup
        """

        // Code to move WAR to Tomcat
        bat "xcopy /y freddie-app\\webapp\\target\\webapp.war ${params.server}prod\\webapps"

      }
      post {
        success {
          // Send email to successful deployment to production
          emailNotification('Success! Deployed to production')
        }
        failure {
          // Send email to notify failed
          emailNotification('FAILED to deploy to production')
        }
        abort{
          // Send email to notify REJECTED
          echo 'deployment REJECTED'
        }

      } //end of post

    } //end of stage


  } //end of stages

} //end of pipeline

def emailNotification(status) {
  emailext(
  to: "${params.emailTo}",
  subject: "${status}",
  body: "Job Name: <b>${env.JOB_NAME}</b> <br>" +
      "Build: <b>${env.BUILD_NUMBER}</b> <br>" +
      "<a href=${env.BUILD_URL}>Check Console Output</a>"
  )
}
