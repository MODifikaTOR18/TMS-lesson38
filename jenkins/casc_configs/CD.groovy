
folder('builds') {
    description('<div style="border-radius:10px; text-align: center; font-size:120%; padding:15px; background-color: powderblue;">Simple app CD</div>')
}


multibranchPipelineJob('builds/cd') {
    branchSources {
        branchSource {
            source {
                github {
                    // Specify the name of the GitHub Organization or GitHub User Account.
                    repoOwner('modifikator18')
                    // The repository to scan.
                    repository('TMS-lesson38')
                    // Specify the HTTPS URL of the GitHub Organization / User Account and repository.
                    repositoryUrl('https://github.com/MODifikaTOR18/TMS-lesson38.git')
                    configuredByUrl(true)
                    // Credentials used to scan branches and pull requests, check out sources and mark commit statuses.
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
          	scriptPath('Jenkinsfile-CD')
        }
    }
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
                        // If checked, scripts may select a custom version of the library by appending @someversion in the @Library annotation.
                        allowVersionOverride(true)
                        // A default version of the library to load if a script does not select another.
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
                        // If checked, scripts may select a custom version of the library by appending @someversion in the @Library annotation.
                        allowVersionOverride(true)
                        // A default version of the library to load if a script does not select another.
                        defaultVersion('master')
                    }
                }
	        }
	    }
    }
}