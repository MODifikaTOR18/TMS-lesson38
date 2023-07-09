def call(app) {
    return {
        stage(app) {
            sh "docker run -d ${app}:${env.TAG}"
        }
    }
}