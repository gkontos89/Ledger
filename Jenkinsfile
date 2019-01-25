pipeline {
    agent any

    ws('C:/Jenkins/workspace/') {
        stages {
            stage('Build') {
                steps {
                    echo 'Building..'
                }

            }

            stage('Unit Test') {
                steps {
                    echo 'Unit testing..'
                }
            }

            stage('UI Test') {
                steps {
                    echo 'UI Test..'
                }
            }

            stage ('Report Results') {
                steps {
                    echo 'Reporting'
                }
            }
        }
    }
}