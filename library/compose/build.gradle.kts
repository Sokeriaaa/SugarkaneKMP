import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.mavenPublish)
}

group = "io.github.sokeriaaa"
version = "0.1.0-SNAPSHOT"

mavenPublishing {
    coordinates(
        groupId = group.toString(),
        artifactId = "sugarkane-compose",
        version = version.toString(),
    )

    pom {
        name = "Sugarkane Compose"
        description = "Shared Compose Multiplatform widgets and logic."
        inceptionYear = "2026"
        url = "https://github.com/Sokeriaaa/SugarkaneKMP"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://apache.org"
            }
        }
        developers {
            developer {
                id = "Sokeriaaa"
                name = "Sokeriaaa"
            }
        }
        scm {
            url = "https://github.com"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Sokeriaaa/SugarkaneKMP")
            credentials {
                credentials {
                    username = project.findProperty("github.packages.username") as String?
                        ?: System.getenv("GITHUB_ACTOR")
                    password = project.findProperty("github.packages.password") as String?
                        ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}

kotlin {
    android {
        // AGP 9.0: New DSL for host tests (Unit Tests)
        withHostTest {
            isIncludeAndroidResources = true
        }
        namespace = "sokeriaaa.sugarkane.compose"
        compileSdk = libs.versions.android.compileSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
        androidResources {
            enable = true
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SugarkaneCompose"
            isStatic = true
        }
    }
    
    jvm()
    
    js {
        browser()
        binaries.executable()
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.ui.tooling.preview)
        }
        commonMain.dependencies {
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.ui.tooling)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "sokeriaaa.sugarkane.compose.res"
}