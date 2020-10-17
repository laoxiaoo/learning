pipeline {
    agent any

    stages {
        stage('pull code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'edeabfcb-f7c2-486c-8ba7-e722516e6a0b', url: 'https://gitee.com/lonelyxiao/learning.git']]])
            }
        }
        stage('build code') {
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true '
            }
        }
        stage('publish code') {
            steps {
                echo 'publish code'
            }
        }
    }
}
