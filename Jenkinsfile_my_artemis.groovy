node {
    properties(
        [parameters([
            choice(choices: [
            'version/0.1', 
            'version/0.2', 
            'version/0.3', 
            'version/0.4', 
            'version/0.5'], 
            description: 'Please choose your version.', 
            name: 'VERSION'), 
            choice(choices: [
            'dev1.huseyinakten.net', 
            'qa1.huseyinakten.net', 
            'stage1.huseyinakten.net', 
            'prod1.huseyinakten.net'], 
            description: 'Please choose the environment.', 
            name: 'ENVIR'), 
            string(defaultValue: 'husakten@gmail.com', description: 'Please enter your email, use "," multiple email.s', name: 'EMAIL', trim: false)
            ])])

    stage(Checkout) {
        checkout([$class: 'GitSCM', branches: [[name: "*/${VERSION}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/hakten/artemis.git']]])

    }
    stage(Install Prerequisities) {
    withCredentials([sshUserPrivateKey(credentialsId: '4287f71e-5803-4db2-849a-3bd252b46a60', keyFileVariable: '', passphraseVariable: '', usernameVariable: '')]) {
    sudo yum install epel-release -y
    sudo yum install python-pip -y
    sudo pip install Flask
    }
    }


}