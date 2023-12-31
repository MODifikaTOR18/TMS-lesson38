def call(app, path, lock_id, builtApps) {
    return {
        stage(app) {
            lock("Build-lock-${lock_id}") {
                dir(path) {
                    sh """
                        docker rm -f ${app}
                        [ -d target] || mkdir target
                        docker build -t ${app} -f Dockerfile-build .
                        docker run --name ${app} ${app} mvn test &&
                        docker cp ${app}:/app/target/ target/
                        docker rm -f ${app}
                    """
                    builtApps.put(app, path)
                }
            }
        }
    }
}