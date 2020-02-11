pipeline {
  agent any
  stages {
    stage('Stage 1') {
      parallel {
        stage('Stage 1') {
          steps {
            echo 'Hello'
          }
        }

        stage('Test') {
          steps {
            sh 'echo "Hello"'
          }
        }

      }
    }

    stage('Stage 2') {
      steps {
        echo 'Hello Stage 2'
      }
    }

    stage('Stage 3') {
      steps {
        echo 'Stage 3'
      }
    }

    stage('Stage 4') {
      steps {
        echo 'Stage 4'
      }
    }

  }
}