node {
properties([parameters([choice(choices: ['ansible-tower', 'consul', 'elk', 'gitlab', 'golden_image', 'jenkins', 'nagiosxi', 'nexus', 'r1soft-server', 'vault'], description: 'Which tool\'s ami would you like to build?', name: 'TOOL-TO-PROVISION'), choice(choices: ['us-east-1', 'us-east-2', 'us-west-1', 'us-west-2'], description: 'Where would you like to build your AMI?', name: 'REGION'), string(defaultValue: 't2.micro', description: 'Instance type for the ami, please enter in t2.micro or m4.large format.', name: 'INSTANCE-TYPE', trim: false)])])

stage('Pull Repo') {
    git 'https://github.com/hakten/packer.git'
}

stage('Build Image') {
    sh "packer build --var region=${REGION} --var instance_type=${INSTANCE-TYPE} --var-file ${TOOL-TO-PROVISION}/variable.json tools/${TOOL-TO-PROVISION}.json"
    
}


}
