plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "23.0.1"  // Be more specific with version
    modules = listOf(
        "javafx.controls",
        "javafx.fxml",
        "javafx.media",
        "javafx.graphics",
        "javafx.web"  // Added for completeness
    )
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("edu.bsu.cs.GUI")
}

tasks.test {
    useJUnitPlatform()
}