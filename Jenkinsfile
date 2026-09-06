pipeline {
    agent any

    environment {
        TOMCAT_URL = 'http://localhost:7080'
        TOMCAT_CREDENTIALS_ID = 'tomcat-credential'
        APP_CONTEXT = 'student-management-system'
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
                echo 'Building WAR file...'
                bat 'mvn package'
            }
        }

        stage('Check WAR File') {
            steps {
                script {
                    if (!fileExists('target/student-management-system.war')) {
                        echo 'WAR file not found'
                        error 'WAR file was not generated.'
                    }

                    echo 'WAR file found: target/student-management-system.war'
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    withCredentials([
                        usernamePassword(
                            credentialsId: env.TOMCAT_CREDENTIALS_ID,
                            usernameVariable: 'TOMCAT_USER',
                            passwordVariable: 'TOMCAT_PASSWORD'
                        )
                    ]) {
                        bat '''
                            curl --fail --upload-file "target/student-management-system.war" ^
                            "%TOMCAT_URL%/manager/text/deploy?path=/%APP_CONTEXT%&update=true" ^
                            --user "%TOMCAT_USER%:%TOMCAT_PASSWORD%"
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully.'
        }

        failure {
            echo 'Build failed. Check Console Output.'
        }
    }
}
