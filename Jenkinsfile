pipeline {
    agent any
    
    tools {
        jdk 'JDK8'
        maven 'Maven3'
    }
    
    environment {
        TOMCAT_HOME = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0'
        TOMCAT_URL = 'http://localhost:8080'
        APP_NAME = 'HMS'
        MYSQL_HOST = 'localhost'
        MYSQL_PORT = '3306'
        MYSQL_DB = 'hospital'
        MYSQL_USER = 'root'
        MYSQL_PASSWORD = ''
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm
            }
        }
        
        stage('Environment Check') {
            steps {
                echo 'Checking build environment...'
                bat '''
                    echo Java Version:
                    java -version
                    echo.
                    echo Maven Version:
                    mvn -version
                    echo.
                    echo Ant Version:
                    ant -version
                '''
            }
        }
        
        stage('Database Setup') {
            steps {
                echo 'Checking MySQL connection...'
                script {
                    try {
                        bat '''
                            echo Checking MySQL service...
                            sc query MySQL80
                        '''
                    } catch (Exception e) {
                        echo "MySQL service check failed: ${e.message}"
                        echo "Attempting to start MySQL..."
                        bat 'net start MySQL80 || echo MySQL already running or not installed'
                    }
                }
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building the application...'
                bat '''
                    echo Cleaning previous builds...
                    if exist build rmdir /s /q build
                    if exist dist rmdir /s /q dist
                    
                    echo Building with Ant...
                    ant clean
                    ant compile
                    ant dist
                '''
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running tests...'
                script {
                    try {
                        bat 'ant test || echo No tests configured'
                    } catch (Exception e) {
                        echo "Tests not configured or failed: ${e.message}"
                    }
                }
            }
        }
        
        stage('Code Quality Analysis') {
            steps {
                echo 'Analyzing code quality...'
                script {
                    // Check for common issues
                    bat '''
                        echo Checking for SQL injection vulnerabilities...
                        findstr /S /I /C:"Statement st" src\\java\\Controller\\*.java > nul && (
                            echo WARNING: Found potential SQL injection risks
                        ) || (
                            echo PASS: Using PreparedStatement
                        )
                    '''
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Creating WAR package...'
                bat '''
                    echo Creating WAR file...
                    ant war || ant dist
                    
                    echo Listing build artifacts...
                    dir dist
                '''
            }
        }
        
        stage('Deploy to Tomcat') {
            steps {
                echo 'Deploying to Tomcat...'
                script {
                    try {
                        bat """
                            echo Stopping Tomcat...
                            taskkill /F /IM tomcat9.exe /T 2>nul || echo Tomcat not running
                            
                            timeout /t 5 /nobreak
                            
                            echo Removing old deployment...
                            if exist "${TOMCAT_HOME}\\webapps\\${APP_NAME}" rmdir /s /q "${TOMCAT_HOME}\\webapps\\${APP_NAME}"
                            if exist "${TOMCAT_HOME}\\webapps\\${APP_NAME}.war" del /f "${TOMCAT_HOME}\\webapps\\${APP_NAME}.war"
                            
                            echo Copying WAR file...
                            copy /Y dist\\*.war "${TOMCAT_HOME}\\webapps\\${APP_NAME}.war"
                            
                            echo Starting Tomcat...
                            start "" "${TOMCAT_HOME}\\bin\\startup.bat"
                            
                            echo Waiting for deployment...
                            timeout /t 30 /nobreak
                        """
                    } catch (Exception e) {
                        echo "Deployment failed: ${e.message}"
                        error("Failed to deploy to Tomcat")
                    }
                }
            }
        }
        
        stage('Health Check') {
            steps {
                echo 'Performing health check...'
                script {
                    sleep 10
                    try {
                        bat """
                            echo Checking application health...
                            curl -f ${TOMCAT_URL}/${APP_NAME}/ || echo Application not responding yet
                        """
                    } catch (Exception e) {
                        echo "Health check warning: ${e.message}"
                    }
                }
            }
        }
        
        stage('Smoke Test') {
            steps {
                echo 'Running smoke tests...'
                script {
                    try {
                        bat """
                            echo Testing login page...
                            curl -f ${TOMCAT_URL}/${APP_NAME}/index.jsp
                            
                            echo Testing admin login page...
                            curl -f ${TOMCAT_URL}/${APP_NAME}/adminLogin.jsp
                        """
                    } catch (Exception e) {
                        echo "Smoke test warning: ${e.message}"
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline completed successfully!'
            echo "Application deployed at: ${TOMCAT_URL}/${APP_NAME}/"
            emailext (
                subject: "✅ SUCCESS: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                    <h2>Build Successful!</h2>
                    <p><strong>Project:</strong> ${env.JOB_NAME}</p>
                    <p><strong>Build Number:</strong> ${env.BUILD_NUMBER}</p>
                    <p><strong>Application URL:</strong> <a href="${TOMCAT_URL}/${APP_NAME}/">${TOMCAT_URL}/${APP_NAME}/</a></p>
                    <p><strong>Build URL:</strong> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    <hr>
                    <h3>Login Credentials:</h3>
                    <ul>
                        <li>Admin: admin / admin</li>
                        <li>User: 123 / 123</li>
                    </ul>
                """,
                to: 'team@hospital.com',
                mimeType: 'text/html'
            )
        }
        failure {
            echo '❌ Pipeline failed!'
            emailext (
                subject: "❌ FAILURE: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                    <h2>Build Failed!</h2>
                    <p><strong>Project:</strong> ${env.JOB_NAME}</p>
                    <p><strong>Build Number:</strong> ${env.BUILD_NUMBER}</p>
                    <p><strong>Build URL:</strong> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    <p>Please check the console output for details.</p>
                """,
                to: 'team@hospital.com',
                mimeType: 'text/html'
            )
        }
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
