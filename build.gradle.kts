plugins {
    id("java")
    id("application")
}

group = "net.regsirius06.matrix"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains:annotations:24.0.0")
}

distributions {
    main {
        contents {
            from("src/main/resources")
        }
    }
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Main-Class" to "net.regsirius06.matrix.Start"))
    }
}

tasks.test {
    useJUnitPlatform()
}