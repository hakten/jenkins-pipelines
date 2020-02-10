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
    git 'https://github.com/farrukh90/packer.git'
}

stage('Build Image') {
    sh "packer version"
    sh "packer build -var region=${REGION} tools/${TOOL-TO-PROVISION}.json"

}


}
