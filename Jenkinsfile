pipeline {
    agent any
    
    environment {
        TOMCAT_HOME = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0'
        TOMCAT_URL = 'http://localhost:8080'
        APP_NAME = 'HMS'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build with Maven') {
            steps {
                echo 'Building with Maven (matching NetBeans)...'
                script {
                    // Use the Maven wrapper or system maven
                    // We skip tests to ensure quick deployment for now
                    bat 'mvn clean package -DskipTests'
                }
            }
        }
        
        stage('Deploy to Tomcat') {
            steps {
                echo 'Deploying to Tomcat...'
                script {
                    // Stop Tomcat (if running) to release file locks
                    try {
                         bat 'taskkill /F /IM tomcat9.exe /T'
                    } catch (Exception e) {
                        echo "Tomcat was not running."
                    }
                    
                    sleep 3
                    
                    // Deploy WAR file
                    bat """
                        echo Removing old deployment...
                        if exist "${TOMCAT_HOME}\\webapps\\${APP_NAME}" rmdir /s /q "${TOMCAT_HOME}\\webapps\\${APP_NAME}"
                        if exist "${TOMCAT_HOME}\\webapps\\${APP_NAME}.war" del /f "${TOMCAT_HOME}\\webapps\\${APP_NAME}.war"
                        
                        echo Copying WAR file...
                        if exist target\\HMS.war copy /Y target\\HMS.war "${TOMCAT_HOME}\\webapps\\${APP_NAME}.war"
                    """
                    
                    // Start Tomcat
                    echo "Starting Tomcat..."
                    bat "start \"\" \"${TOMCAT_HOME}\\bin\\startup.bat\""
                }
            }
        }
    }
    
    post {
        success {
            echo "✅ Successfully deployed! Access at: ${TOMCAT_URL}/${APP_NAME}"
        }
        failure {
            echo "❌ Build failed. Please check logs."
        }
    }
}
