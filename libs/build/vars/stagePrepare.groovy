def call(flag, apps, parallel_count, builtApps = [:]) {
    stageList = []
    stageMap = [:]
    apps.eachWithIndex { app, path, i ->
        Integer lock_id = i % parallel_count
        if (flag == 'build') {
            stageMap.put(app, stageBuildCreate.call(app, path, lock_id, builtApps))
        } else {
            stageMap.put(app, stageImageCreate.call(app, path, lock_id))
        }
    }
    stageList.add(stageMap)
    return (stageList, builtApps)
}