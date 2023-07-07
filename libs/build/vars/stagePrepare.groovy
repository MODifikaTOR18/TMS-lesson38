def stagePrepare(flag, apps, parallel_count) {
    stageList = []
    stageMap = [:]
    apps.eachWithIndex { app, path, i ->
        Integer lock_id = i % parallel_count
        if (flag == 'build') {
            stageMap.put(app, stageBuildCreate.stageBuildCreate(app, path, lock_id))
        } else {
            stageMap.put(app, stageImageCreate.stageImageCreate(app, path, lock_id))
        }
    }
    stageList.add(stageMap)
    return stageList
}