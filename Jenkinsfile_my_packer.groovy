node {
properties([
    buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '5')),
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
            description: 'Please choose a region', name: 'REGION'),
        string(defaultValue: 't2.micro', description: 'Please enter instance type for your AMI in t2.micro, m4.large format.', name: 'INSTANCE_TYPE', trim: false),
        string(defaultValue: 'husakten@mail.com', description: 'Please enter your email address.', name: 'EMAIL', trim: false)])])


stage('Pull Repo') {
    git 'https://github.com/hakten/packer_files.git'
}

stage('Build Image') {
    sh "packer version"
    sh "packer build -var region=${REGION} -var instance_type=${INSTANCE_TYPE} -var ssh_username=centos ${TOOL_TO_PROVISION}.json"
}

stage('Send Email') {
    mail bcc: '', 
    body: "Your AMI is ready in region ${REGION}.", 
    cc: '', 
    from: 'husakten@gmail.com', 
    replyTo: '', 
    subject: 'Your AMI request is completed.', 
    to: "${EMAIL}"
}

}
