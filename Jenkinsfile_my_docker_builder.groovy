node {
    properties([[$class: 'JiraProjectProperty'], parameters([choice(choices: ['terraform0.11.14', 'terraform0.12.19'], description: 'Please choose the tool to build Docker image.', name: 'IMAGE')])])
    stage("Pull Repo"){
        ws ("tmp/"){
            git 'https://github.com/hakten/Dockerfiles.git'
            sh "ls"
        }
    }
    stage("Build Image") {
        ws ("tmp/Dockerfiles"){ 
            sh "ls"
            sh "docker build -t ${IMAGE} ${IMAGE}/."
        }
    }
}