   pipeline {
    agent any

    parameters {
        choice choices: ['gradle', 'maven'], description: 'Indicar herramienta de construcción', name: 'builtTool'
    }

    stages{
        
      stage("Pipeline"){
                steps{
                    script {
                           echo "pipeline"
                    }
                } 
            }


        }
  }  	


  