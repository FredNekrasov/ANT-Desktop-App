import org.gradle.kotlin.dsl.support.kotlinCompilerOptions
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    kotlin("plugin.serialization")
    id("app.cash.sqldelight")
}

group = "org.fredprojects"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}
sqldelight {
    databases {
        create("ANTDatabase") {
            packageName = "data"
        }
    }
}
dependencies {
    // database
    val sqlDelight = "2.0.2"
    implementation("app.cash.sqldelight:sqlite-driver:$sqlDelight")
    implementation("app.cash.sqldelight:runtime:$sqlDelight")
    // network & serialization
    val ktor = "3.1.2"
    implementation("io.ktor:ktor-client-core:$ktor")
    implementation("io.ktor:ktor-client-okhttp:$ktor")
    implementation("io.ktor:ktor-client-logging:$ktor")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
    // DI Koin
    val koin = "4.0.4"
    implementation("io.insert-koin:koin-core:$koin")
    implementation("io.insert-koin:koin-core-viewmodel:$koin")
    implementation("io.insert-koin:koin-compose-viewmodel:$koin")
    implementation("io.insert-koin:koin-compose:$koin")
    // Compose
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    val coil = "3.1.0"
    implementation("io.coil-kt.coil3:coil-compose:$coil")
    implementation("io.coil-kt.coil3:coil-network-okhttp:$coil")
    implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-alpha17")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ANTDesktopApp"
            packageVersion = "1.0.0"
        }
    }
}
