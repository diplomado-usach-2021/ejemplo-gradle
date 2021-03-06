/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
  
             stage("Build & unit test"){

                             println "Stage: ${env.STAGE_NAME}"
                             sh " whoami; ls -ltr "
                             sh  "chmod +x gradlew "
                             sh "./gradlew clean build "
                             echo "${env.WORKSPACE}"
                             echo "${WORKSPACE}";
         
            }
            

            stage("sonar"){
   
                          println "Stage: ${env.STAGE_NAME}"  
                            def scannerHome = tool 'sonar-scanner';
                             withSonarQubeEnv('sonarqube-server') { 
                                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle-key  -Dsonar.sources=src -Dsonar.java.binaries=build "
         

  
              }
            }

/*
           stage('Guardando WAR') {             
                              archiveArtifacts 'build/libs/*.jar'

            }
*/

            stage("Run"){
        
                          println "Stage: ${env.STAGE_NAME}"    
                          sh " nohup bash gradlew bootRun & "
                          sleep 20
          
            }
			
          stage("Testing Application"){

                             println "Stage: ${env.STAGE_NAME}"
                             sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
            }


           
            stage('nexus') {
   
                nexusPublisher nexusInstanceId: 'nexus_test',
                nexusRepositoryId: 'test-nexus',
                packages: [
                    [
                        $class: 'MavenPackage',
                        mavenAssetList: [
                            [classifier: '', extension: '', filePath: "${env.WORKSPACE}/build/libs/DevOpsUsach2020-0.0.1.jar"]
                        ],
                        mavenCoordinate: [
                            artifactId: 'DevOpsUsach2020',
                            groupId: 'com.devopsusach2020',
                            packaging: 'jar',
                            version: '0.0.3'
                        ]
                    ]
                ]
            
        }

            
		
   
  	

}

return this;