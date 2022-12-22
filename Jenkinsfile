pipeline {
    agent any

    triggers { 
        pollSCM('* * * * *') }

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

        string(
            name: 'info',
            description: 'Please type info')    

        booleanParam(
	        name: 'build_only',
	        defaultValue: true,
	        description: 'Check this if you only want to build the application and not deploy it to serversss')



        choice(
            name: 'environment',
            choices: ['staging', 'dev', 'production'],
            description: 'Choose environment to deploy to')


    }

    stages {

        stage('Pull new configuration for Jenkins job') {
            when {
                expression {
                    params.build_only
                }
            }

            steps {
                script {
                    echo "Cao Cao"
                }

            }

        }

        stage('Do something') {
            when {
                expression {
                    !params.build_only
                }
            }

            steps {
                script {
                    echo "Ja radim nesto, i to dobro"
                }
            }
        }


    }
}
