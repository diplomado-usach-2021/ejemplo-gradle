   pipeline {
    agent any

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


        }
  }  	


  