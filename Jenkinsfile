pipeline {
    agent any

    options {
        timeout(time: 60, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '50'))
        timestamps()
    }

    parameters {
        string(
            name: 'branch',
            defaultValue: 'master',
            description: 'Please type in name of the branch you wish to deploy')

        booleanParam(
	        name: 'build_only',
	        defaultValue: false,
	        description: 'Check this if you only want to build the application and not deploy it to servers')

        booleanParam(
	        name: 'test',
	        defaultValue: false,
	        description: 'Check this if you only want to build the application and not deploy it to servers')

        choice(
            name: 'environment',
            choices: ['staging', 'dev', 'production'],
            description: 'Choose environment to deploy to')
    }

    stages {

        stage('Prepare workspace') {

            steps {
                script {
                    echo "Cao Cao"
                }

            }

        }
    }
}
