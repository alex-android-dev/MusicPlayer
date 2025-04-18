plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines.core)
}
