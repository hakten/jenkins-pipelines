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
            'vault'
            ], 
            description: 'What tool would you like to build?', name: 'TOOL_TO_PROVISION'),
        choice(choices: [
            'us-east-1', 
            'us-east-2', 
            'us-west-1', 
            'us-west-2'
            ], 
           description: 'Please choose a region', name: 'AMI_REGION')])])

stage('Pull Repo') {
    git 'https://github.com/hakten/packer.git'
}

stage('Build Image') {
    sh "packer version"
    sh "packer build -var region=${AMI_REGION} tools/${TOOL_TO_PROVISION}.json"

}


}
