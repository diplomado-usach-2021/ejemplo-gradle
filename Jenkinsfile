   pipeline {
    agent any
    stages{
             stage("Build & unit test"){
                steps{
                    script {
                             println "Stage: ${env.STAGE_NAME}"
                             sh "./gradlew clean build "
                    }
                } 
            }
            

            stage("sonar"){
                steps{
                    script {
                          println "Stage: ${env.STAGE_NAME}"  
                            def scannerHome = tool 'sonar-scanner';
                             withSonarQubeEnv('sonarqube-server') { 
                                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle-key  -Dsonar.sources=src -Dsonar.java.binaries=build "
         

                    }
                }
            }

            stage("Run"){
                steps{
                    script {
                          println "Stage: ${env.STAGE_NAME}"    
                         sh " ./gradlew bootRun "
                    }
                }
            }
			
          stage("Testing Application"){
                steps{
                    script {
                             println "Stage: ${env.STAGE_NAME}"
                             sh "curl -X GET 'http://localhost:8082/rest/mscovid/test?msg=testing'"
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