   pipeline {
    agent any
    stages{
             stage("Build & unit test"){
                steps{
                    script {
                             println "Stage: ${env.STAGE_NAME}"
                    }
                }
            }
            

  

            stage("Run"){
                steps{
                    script {
                          println "Stage: ${env.STAGE_NAME}"    

                    }
                }
            }
			
          stage("Testing Application"){
                steps{
                    script {
                             println "Stage: ${env.STAGE_NAME}"
                    }
                }
            }

          stage("Nexus"){
                steps{
                    script {
                            println "Stage: ${env.STAGE_NAME}" 
                    }
                }
            }

            
		}
   }	