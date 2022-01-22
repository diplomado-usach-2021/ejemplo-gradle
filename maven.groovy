 pipeline {
    agent any
    stages{
             stage("Compile Code"){
                steps{
                    script {
                          sh  "chmod +x mvnw "
                                sh " ./mvnw clean compile -e"
                    }
                }
            }
            
    stage('SonarQube analysis') {
        steps{
         script {
                def scannerHome = tool 'sonar-scanner';
                withSonarQubeEnv('sonarqube-server') { 
                                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-maven-key  -Dsonar.sources=src -Dsonar.java.binaries=build "
                         }
                }
       }
   }
  
         
   
   
      stage("Test Code"){
   
   
                steps{
                    script {
                               sh  " ./mvnw clean test -e "
                        

                    }
                }
            }
            
            stage("Jar Code"){
                steps{
                    script {
                               sh  " ./mvnw clean package -e "
                        

                    }
                }
            }



             stage('Guardando WAR') {             
                steps{
                    script {
                              archiveArtifacts 'build/*.jar'

                }
             }
            }

/*
             stage("Upload to Nexus"){
               
                steps{
                     sh 'echo ${WORKSPACE}'
                    script {
                       nexusPublisher nexusInstanceId: 'nexus_test', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
                }
             }
            }
*/

 
            
            stage("Run Jar"){
                steps{
                    script {
                               sh  "nohup bash mvnw spring-boot:run &"
                               sleep 20

                    }
                }
            }
			
          stage("Testing Application"){
                steps{
                    script {
                               sh  " curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing' "
                    }
                }
            }

         stage('nexus') {
            steps {
                nexusPublisher nexusInstanceId: 'nexus_test',
                nexusRepositoryId: 'test-nexus',
                packages: [
                    [
                        $class: 'MavenPackage',
                        mavenAssetList: [
                            [classifier: '', extension: '', filePath: "${env.WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar"]
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

  
            
		}
   }