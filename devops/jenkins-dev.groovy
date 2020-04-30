@Library('ZceeUtil')
import shared.JenkinsUtil
def utils = new JenkinsUtil(this)
def artifactoryUrl="http://host.docker.internal:8081/artifactory"
def project="zcee_api_STDA_v1.0.0"
def type="aar"
def deploymentEnviroment="DEV"
node{
 
       stage('checkout'){
            checkout scm
            utils.prepare()
            env.artifactoryUrl="${artifactoryUrl}"
       }
      stage('Build') {
            utils.buildZconnect("${project}")
         
      }
      stage('upload snapshot to artifactory'){
        utils.uploadSnapshot("${project}","${type}")
        archiveArtifacts(artifacts:"*.${type}")
      }

      stage("Deploy to ${deploymentEnviroment}"){
            
      }
   
}
