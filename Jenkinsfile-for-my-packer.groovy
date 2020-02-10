node {
properties([
    parameters([
        choice(choices: [
            'ansible-tower', 
            'consul', 
            'elk', 
            'gitlab', 
            'golden_image', 
            'jenkins', 
            'nagiosxi', 
            'nexus', 
            'r1soft-server', 
            'vault'], 
        description: 'Which tool ami would you like to build?', name: 'TOOL-TO-PROVISION'), 
        choice(choices: [
            'us-east-1', 
            'us-east-2', 
            'us-west-1', 
            'us-west-2'], 
        description: 'Where would you like to build your AMI?', name: 'REGION')])])

stage('Pull Repo') {
    git 'https://github.com/hakten/packer.git'
}

stage('Build Image') {
    sh "packer build -var region=${REGION} instance_type="t2.micro" ssh_username="centos" tools/${TOOL-TO-PROVISION}.json"

}


}
