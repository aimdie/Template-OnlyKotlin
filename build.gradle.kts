buildscript {
  repositories {
    gradlePluginPortal()
    jcenter()
    google()
    mavenCentral()
  }
  
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
  }
}

allprojects {
  repositories {
    maven("https://www.jetbrains.com/intellij-repository/releases")
    maven("https://repo.huaweicloud.com/repository/maven/")
    google()
    jcenter()
    mavenCentral()
    mavenLocal()
  }
}