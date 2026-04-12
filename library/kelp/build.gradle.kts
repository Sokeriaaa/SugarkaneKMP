import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.mavenPublish)
}

group = "io.github.sokeriaaa"
version = project.findProperty("sugarkane.kmp.version") as String

mavenPublishing {
    coordinates(
        groupId = group.toString(),
        artifactId = "sugarkane-kelp",
        version = version.toString(),
    )

    pom {
        name = "Sugarkane Kelp"
        description = "Shared Kotlin Multiplatform common code."
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
        namespace = "sokeriaaa.sugarkane.kelp"
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
            baseName = "SugarkaneKelp"
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
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}