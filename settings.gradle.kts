pluginManagement {
    repositories {
        google()       // 🔥 necesario para com.android.application
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()       // 🔥 necesario para los artefactos de Android
        mavenCentral()
    }
}

rootProject.name = "arquetipo_android_project"
include(":app")
