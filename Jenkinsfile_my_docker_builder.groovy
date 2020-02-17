node {
    properties([[$class: 'JiraProjectProperty'], parameters([choice(choices: ['terraform_0.11.14', 'terraform_0.12.19', 'packer_1.5.0'], description: 'Please choose the tool to build Docker image.', name: 'TOOL')])])
    
    stage("Pull Repo"){
            git 'https://github.com/hakten/Dockerfiles.git'
            sh "ls"
    }

    stage("Build Image") {
            sh "ls"
            sh "docker build -t ${TOOl} Dockerfiles/${TOOL}/."
    }

    stage("login to ECR"){
            sh '''$(aws ecr get-login --no-include-email --region eu-west-1)'''
    }

    stage("push image"){
            sh "docker images"
            sh "docker push 103872286656.dkr.ecr.eu-west-1.amazonaws.com/${TOOL}"
    }
}