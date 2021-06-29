pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''cd Almacen
mvn -B -DskipTests clean package'''
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo "${env.PIPELINE_ID}"'
      }
    }

  }
}