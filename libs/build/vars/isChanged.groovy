def isChanged(diff, path) {
    for (file in diff) {
        if (file =~ path) {
            return true
        }
    }
}