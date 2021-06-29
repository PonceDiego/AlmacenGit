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
        sh 'rm -f $ftp_war_path/almacenapi.war'
        sh 'cp target/almacenapi.war "$ftp_war_path"'
        sh '''docker container stop $containerName

docker container rm $containerName'''
        sh '''cd $ftp_war_path

docker build -t $containerName .

docker run -d --name $containerName -p 4455:8080 $containerName'''
      }
    }

  }
  environment {
    containerName = 'tomcat'
    ftp_war_path = '/root/Alexis/Proyectos/almacen'
  }
}