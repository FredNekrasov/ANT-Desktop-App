import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("app.cash.sqldelight") version "2.0.2"
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
            packageName = "data.local"
        }
    }
}
dependencies {
    // database
    val sqlDelight = "2.0.2"
    implementation("app.cash.sqldelight:sqlite-driver:$sqlDelight")
    implementation("app.cash.sqldelight:runtime:$sqlDelight")
    // network & serialization
    val ktor = "3.0.1"
    implementation("io.ktor:ktor-client-core:$ktor")
    implementation("io.ktor:ktor-client-okhttp:$ktor")
    implementation("io.ktor:ktor-client-logging:$ktor")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    // DI Koin
    val koin = "4.0.0"
    implementation("io.insert-koin:koin-core:$koin")
    implementation("io.insert-koin:koin-compose:$koin")
    // Compose
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation("io.coil-kt.coil3:coil-compose:3.0.3")
    implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ANT-Desktop-App"
            packageVersion = "1.0.0"
        }
    }
}
