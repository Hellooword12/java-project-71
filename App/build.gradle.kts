plugins {
    id ("checkstyle")
    application
    id ("com.github.ben-manes.versions") version "0.52.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
application {
    mainClass = "hexlet.code.App"
}



dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.0")

}

tasks.test {
    useJUnitPlatform()
}