node {
        stage("Pull Repo"){
            git 'https://github.com/hakten/terraform-aws-eks.git'
    }
        stage("stage2"){
            sh "cd terraform-aws-eks"
            sh "terraform version"
            sh "wget https://releases.hashicorp.com/terraform/0.12.19/terraform_0.12.19_linux_amd64.zip"
            sh "unzip -o terraform_0.12.19_linux_amd64.zip"
            sh "./terraform version"
    }
        stage("stage3"){
    

    }
        stage("stage4"){

    }
}