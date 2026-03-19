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
    version = "23.0.1"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.media", "javafx.graphics")
    // Add this configuration
    configuration = "implementation"
}

dependencies {
    // Explicitly add JavaFX to compile and runtime classpath
    implementation("org.openjfx:javafx-controls:23.0.1")
    implementation("org.openjfx:javafx-fxml:23.0.1")
    implementation("org.openjfx:javafx-media:23.0.1")
    implementation("org.openjfx:javafx-graphics:23.0.1")

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