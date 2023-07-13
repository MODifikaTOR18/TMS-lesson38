
folder('builds') {
    description('<div style="border-radius:10px; text-align: center; font-size:120%; padding:15px; background-color: powderblue;">Simple app CI/CD</div>')
}

multibranchPipelineJob('builds/ci') {
    blockOn ('builds/cd') {
        blockLevel('GLOBAL')
        scanQueueFor('DISABLED')
    }

    branchSources {
        branchSource {
            source {
                github {
                    repoOwner('modifikator18')
                    repository('TMS-lesson38')
                    repositoryUrl('https://github.com/MODifikaTOR18/TMS-lesson38.git')
                    configuredByUrl(true)
                    credentialsId('GitHub_ID')
                    traits {
                        gitHubBranchDiscovery {
                            strategyId(3)
                        }
                        gitHubPullRequestDiscovery {
                            strategyId(1)
                        }
                        headWildcardFilter {
                            includes('master')
                            excludes('')
                        }
                    }
                }
            }
        }
    }

    factory {
	workflowBranchProjectFactory {
            scriptPath('Jenkinsfile-CI')
        }
    }

// Вот этот блок ломает скрипт
/*    publishers {
        downstream {
            trigger('builds/cd') {
                condition('SUCCESS')
            }
        }
    }
*/
    properties {
        folderLibraries {
            libraries {
                libraryConfiguration {
                    name('general')
                    retriever {
                        modernSCM {
                            scm {
                                git {
                                    remote('https://github.com/MODifikaTOR18/TMS-lesson38.git')
                                    credentialsId('GitHub_ID')
                                }
                                libraryPath('libs/general')
                            }
                        }
                        allowVersionOverride(true)
                        defaultVersion('master')
                    }
                }
                libraryConfiguration {
                    name('build')
                    retriever {
                        modernSCM {
                            scm {
                                git {
                                    remote('https://github.com/MODifikaTOR18/TMS-lesson38.git')
                                    credentialsId('GitHub_ID')
                                }
                                libraryPath('libs/build')
                            }
                        }
                        allowVersionOverride(true)
                        defaultVersion('master')
                    }
                }
	        }
	    }
    }
}
