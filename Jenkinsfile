pipeline {

    agent any

    parameters {

        choice(
            name: 'BROWSER',
            choices: ['chromium', 'firefox', 'webkit'],
            description: 'Select browser'
        )

        choice(
            name: 'HEADLESS',
            choices: ['true', 'false'],
            description: 'Run browser headless'
        )

        choice(
            name: 'ENVIRONMENT',
            choices: ['qa', 'uat'],
            description: 'Select environment'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/jogendradurgamahesh/playwright-sdet-framework.git'
            }
        }

        stage('Run Tests') {
            steps {
                bat """
                    mvn clean test ^
                    -Dbrowser=${params.BROWSER} ^
                    -Dheadless=${params.HEADLESS} ^
                    -Denvironment=${params.ENVIRONMENT}
                """
            }
        }

        stage('Archive Test Artifacts') {
            steps {
                archiveArtifacts artifacts: 'test-output/screenshots/**/*, test-output/traces/**/*',
                    allowEmptyArchive: true
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {

        success {
            emailext(
                subject: "Jenkins SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build SUCCESS. All tests passed.\n\n${env.BUILD_URL}",
                to: "ugginamahesh98@gmail.com"
            )
        }

        failure {
            emailext(
                subject: "Jenkins FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build FAILURE. Please check Jenkins.\n\n${env.BUILD_URL}",
                to: "ugginamahesh98@gmail.com"
            )
        }
    }
}