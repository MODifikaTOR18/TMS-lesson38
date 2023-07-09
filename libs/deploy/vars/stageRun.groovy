def call (app, path) {
    return {
        stage(app) {
            sh "docker run -d ${app}:${env.TAG}"
        }
    }
}