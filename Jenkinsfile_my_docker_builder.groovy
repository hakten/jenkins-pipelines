node {
    properties([[$class: 'JiraProjectProperty'], parameters([
        choice(choices: ['terraform', 'terraform', 'packer'], description: 'Please choose the tool to build Docker image.', name: 'TOOL'),
        string(defaultValue: 'latest', description: 'Please enter app version', name: 'VERSION', trim: false)
    ])])
    
    stage("Pull Repo"){
            git 'https://github.com/hakten/Dockerfiles.git'
    }

    stage("Build Image") {
            sh "docker build -t ${TOOl}:${VERSION} ${TOOL}_${VERSION}/."
    }
    
    stage("Build Image") {
            sh "docker tag ${TOOl}:${VERSION} 103872286656.dkr.ecr.eu-west-1.amazonaws.com/${TOOL}:${VERSION}"
    }

    stage("login to ECR"){
            sh '''$(aws ecr get-login --no-include-email --region eu-west-1)'''
    }

    stage("push image"){
            sh "docker images"
            sh "docker push 103872286656.dkr.ecr.eu-west-1.amazonaws.com/${TOOL}:${VERSION}"
    }
}