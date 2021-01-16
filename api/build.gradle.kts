plugins {
}

group = "com.thomas-driscoll"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.16")
}

tasks.bootJar{
    enabled = false
}

tasks.jar{
    enabled = true
}