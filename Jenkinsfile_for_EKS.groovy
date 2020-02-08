node {
        stage("Pull Repo"){
            git 'https://github.com/hakten/terraform-aws-eks.git'
            sh "pwd"
    }
        stage("stage2"){
            sh "pwd"
            sh "terraform version"
            sh "wget https://releases.hashicorp.com/terraform/0.12.19/terraform_0.12.19_linux_amd64.zip"
            sh "unzip -o terraform_0.12.19_linux_amd64.zip"
            sh "./terraform version"
    }
        stage("stage3"){
            sh "pwd"
            sh "source setenv.sh configurations/dev/eu-west-1/dev.tfvars"

    }
        stage("stage4"){

    }
}