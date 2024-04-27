plugins {
    application
    checkstyle
    jacoco
}

application {
    mainClass = "hexlet.code.App"
}
group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0-M1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

    compileOnly ("org.projectlombok:lombok:1.18.32")
    annotationProcessor ("org.projectlombok:lombok:1.18.32")

    testCompileOnly ("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.32")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports { xml.required.set(true) }
}

