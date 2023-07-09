def call(app, tag) {
    return {
        stage(app) {
            sh "docker run -d ${app}:${tag}"
        }
    }
}