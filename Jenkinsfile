pipeline {
    agent any

    /*
     * Jenkins Pipeline for:
     * Maven Build -> WAR Check -> Apache Tomcat Deployment
     *
     * IMPORTANT:
     * 1. Change TOMCAT_CREDENTIALS_ID to the ID of your Tomcat credential
     *    in Jenkins.
     * 2. Keep TOMCAT_URL as http://localhost:7080 if your Tomcat uses port 7080.
     */

    environment {
        TOMCAT_URL = 'http://localhost:7080'
        TOMCAT_CREDENTIALS_ID = 'tomcat-credentials'
        APP_CONTEXT = 'student-management'
    }

    stages {

        stage('Clean Project') {
            steps {
                echo 'Cleaning Maven project...'
                bat 'mvn clean'
            }
        }

        stage('Build Project') {
            steps {
                echo 'Building Maven project and generating WAR file...'
                bat 'mvn package'
            }
        }

        stage('Check WAR File') {
            steps {
                script {
                    def warFiles = findFiles(glob: 'target/*.war')

                    if (warFiles.length == 0) {
                        echo 'WAR file not found'
                        error 'WAR file was not generated. Please check the Maven build.'
                    }

                    echo "WAR file found: ${warFiles[0].path}"
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    def warFiles = findFiles(glob: 'target/*.war')

                    if (warFiles.length == 0) {
                        error 'WAR file not found. Deployment stopped.'
                    }

                    def warFile = warFiles[0].path

                    withCredentials([
                        usernamePassword(
                            credentialsId: env.TOMCAT_CREDENTIALS_ID,
                            usernameVariable: 'TOMCAT_USER',
                            passwordVariable: 'TOMCAT_PASSWORD'
                        )
                    ]) {
                        echo "Deploying ${warFile} to ${env.TOMCAT_URL}"

                        bat """
                            curl --fail --upload-file "${warFile}" ^
                            "${env.TOMCAT_URL}/manager/text/deploy?path=/${env.APP_CONTEXT}&update=true" ^
                            --user "%TOMCAT_USER%:%TOMCAT_PASSWORD%"
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully.'
            echo 'Application has been deployed to Apache Tomcat.'
        }

        failure {
            echo 'Build failed. Check Console Output for the exact error.'
        }
    }
}
