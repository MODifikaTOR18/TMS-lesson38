def call(apps) {
    apps.eachWithIndex { app, path, i ->
        stageMap.put(app, stageRun(app, path))
    }
    stageList.add(stageMap)
    return stageList
}