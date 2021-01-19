node {
    def branch = '*/master'
    def remote_directory = '/home/jar'
    def source_files = 'esearch/es-jd/target/**'
    def remove_prefix = "esearch/es-jd/target"

    stage('pull code') { // for display purposes
        checkout([$class: 'GitSCM', branches: [[name: "${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'edeabfcb-f7c2-486c-8ba7-e722516e6a0b', url: 'https://gitee.com/lonelyxiao/learning.git']]])
    }
    stage('Build') {
        sh 'mvn clean package -Dmaven.test.skip=true '
    }
    stage('publish code') {
        sshPublisher(publishers: [sshPublisherDesc(configName: '131', transfers: [sshTransfer(cleanRemote: false, excludes: '',
         execCommand: "", execTimeout: 120000, flatten: false,
         makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
         remoteDirectory: "${remote_directory}", remoteDirectorySDF: false,
         removePrefix: '', sourceFiles: "${source_files}")], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
    }
}