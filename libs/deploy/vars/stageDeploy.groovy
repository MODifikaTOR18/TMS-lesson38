def call(apps) {
    apps.eachWithIndex { app, path, i ->
        stageMap.put(app, stageRun(app))
    }
    stageList.add(stageMap)
    return stageList
}