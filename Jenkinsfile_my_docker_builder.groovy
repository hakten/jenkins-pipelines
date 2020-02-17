node {
    properties([[$class: 'JiraProjectProperty'], parameters([
        choice(choices: ['terraform', 'packer'], description: 'Please choose the tool to build Docker image.', name: 'TOOL'),
        string(defaultValue: '0.11.14', description: 'Please enter app version in numbers as in github folder, as 0.12.19', name: 'VERSION', trim: false)
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