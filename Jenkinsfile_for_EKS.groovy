node {
        stage("Pull Repo"){
            git 'https://github.com/hakten/terraform-aws-eks.git'
    }
        stage("stage2"){
            sh "terraform version"
    }
        stage("stage3"){

    }
        stage("stage4"){

    }
}