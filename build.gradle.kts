import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  //id("org.jetbrains.kotlin.jvm") version "1.3.71"
  id("org.springframework.boot") version "2.2.1.RELEASE"
  id("io.spring.dependency-management") version "1.0.8.RELEASE"

  kotlin("jvm") version "1.3.71"
  kotlin("plugin.spring") version "1.3.71"
  kotlin("plugin.jpa") version "1.3.71"
  jacoco
  application

  id("org.sonarqube") version "2.8"
}

group = "io.code.morning"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
  jcenter()
}

application {
  mainClassName = "io.code.morning.AppKt"
}

dependencies {
  // Spring Settings
  implementation("org.springframework.boot:spring-boot-starter-jetty")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-actuator")

  // Libraries
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.3")

  // Swagger
  implementation("org.springdoc:springdoc-openapi-webflux-ui:1.2.32")

  // Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  // Use the Kotlin JDK 8 standard library.
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.3")

  // AWS X-Ray
  implementation("com.amazonaws:aws-xray-recorder-sdk-core")
  implementation("com.amazonaws:aws-xray-recorder-sdk-aws-sdk")
  implementation("com.amazonaws:aws-xray-recorder-sdk-aws-sdk-instrumentor")
  // Testing
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("io.mockk:mockk:1.9")
}

dependencyManagement {
  imports {
    mavenBom("com.amazonaws:aws-java-sdk-bom:1.11.67")
    mavenBom("com.amazonaws:aws-xray-recorder-sdk-bom:1.1.0")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

jacoco {
  toolVersion = "0.8.5"
  reportsDir = file("$buildDir/reports/jacoco")
}

tasks.jacocoTestReport {
  reports {
    xml.isEnabled = true
    html.isEnabled = false
  }

  dependsOn(tasks.withType<Test>())
}

sonarqube {
  properties {
    property("sonar.sourceEncoding", "UTF-8")
    property("sonar.projectKey", "api")
    property("sonar.sources", ".")
    property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
  }
}
