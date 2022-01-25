   pipeline {
    agent any

    environment{
        STAGE = '';
    }


    parameters {
        choice choices: ['gradle', 'maven'], description: 'Indicar herramienta de construcción', name: 'builtTool'
    }

    stages{
        
      stage("Pipeline"){
                steps{
                    script {

                            if (params.builtTool == "gradle") {
                                	def ejecucion = load 'gradle.groovy'
	                                ejecucion.call()
                            } else {
                                	def ejecucion = load 'maven.groovy'
	                                ejecucion.call()
                            }

                           echo "pipeline"
                    }
                } 
            }

/*

            stage("Mensaje Slack"){
                steps{
                    script {                           
                    try {
                          slackSend (color: '#00FF00', message: "Build Success Víctor Menares  [${env.JOB_NAME}] [${params.builtTool}] Ejecución exitosa")

                    } catch(e) {
                          slackSend (color: '#FF0000', message: "Build Failure: Víctor Menares  [${env.JOB_NAME}] [${params.builtTool}] Ejecución fallida en stage  ${STAGE}' ")
                          error "Ejecución fallida en stage ${STAGE}"

                    }
                        
                    }
                } 
            }
   */


        }

      post {
		success {
			   slackSend (color: '#00FF00', message: "Build Success [${env.USER}][${env.JOB_NAME}] [${params.builtTool}] Ejecución exitosa")
		}
		
		failure {
            slackSend (color: '#FF0000', message: "Build Failure [${env.USER}][${env.JOB_NAME}] [${params.builtTool}] Ejecución fallida en stage : ${STAGE}")
			error "Ejecución fallida en stage ${STAGE}"
		}
	 }


  }  	


  