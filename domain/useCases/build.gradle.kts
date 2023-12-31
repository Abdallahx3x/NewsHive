plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    api(project(Modules.DOMAIN_ENTITIES))
    implementation(Dependencies.coroutines)
    implementation(Dependencies.dagger)
    implementation(Dependencies.paging)

}