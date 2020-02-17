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

    stage("Checkout") {
        checkout([$class: 'GitSCM', branches: [[name: "*/${VERSION}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/hakten/artemis.git']]])

    }
	stage("Install Prerequisites"){
				sh '''
					ssh centos@${ENVIR} sudo yum install epel-release -y
					ssh centos@${ENVIR} sudo yum install python-pip -y 
					ssh centos@${ENVIR} sudo pip install Flask
					'''
		}
	stage("Copy Artemis"){
				sh '''
					scp -r * centos@${ENVIR}:/tmp
					'''
		}
	stage("Run Artemis"){
				sh '''
					ssh centos@${ENVIR} nohup python /tmp/artemis.py  &
					'''
		}
	stage("Send slack notifications"){
				echo "Slack"
				//slackSend  message: 'Hello, World!'
			}

    stage("Send Email"){
    mail bcc: '', 
    body: "Hello, your artemis app is deployed to ${ENVIR}", 
    cc: '', 
    from: 'husakten@gmail.com', 
    replyTo: '', 
    subject: "Artemis ${Version} has been deployed", 
    to: "${EMAIL_TO_SEND}"
    }


}