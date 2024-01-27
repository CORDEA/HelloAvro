plugins {
    id("org.jetbrains.kotlin.jvm")
    id("io.ktor.plugin")
    application
}

application {
    mainClass.set("jp.cordea.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(":objects"))
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("io.github.serpro69:kotlin-faker:1.15.0")
    testImplementation("io.ktor:ktor-server-tests-jvm")
}
