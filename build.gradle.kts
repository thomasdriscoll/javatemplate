//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
    id("org.sonarqube") version "3.0"
    id("jacoco")
    id("io.freefair.lombok") version "5.3.0"
}

group = "com.thomas-driscoll"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

allprojects{
    repositories{
        mavenCentral()
    }
}

subprojects{
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "java")
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        runtimeOnly("org.postgresql:postgresql")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    implementation("org.springdoc:springdoc-openapi-ui:1.4.8")
    implementation(project(":api"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestCoverageVerification) // report is always generated after tests run
}
//tasks.jacocoTestCoverageVerification {
//    dependsOn(tasks.test)
//    violationRules {
//        rule {
//            limit {
//                counter = "LINE"
//                minimum = "0.7".toBigDecimal()
//            }
//        }
//    }
//}
