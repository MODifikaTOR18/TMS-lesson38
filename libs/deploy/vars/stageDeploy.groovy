def call(apps) {
    stageList = []
    stageMap = [:]
    apps.each { app, path ->
        stageMap.put(app, stageRun(app, env.TAG))
    }
    stageList.add(stageMap)
    return stageList
}