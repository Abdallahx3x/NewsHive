pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NewsHive"
include(":app")
include(":data:local")
include(":data:remote")
include(":data:repositories")
include(":presentation:ui")
include(":presentation:viewModel")
include(":domain:useCases")
include(":domain:entities")
