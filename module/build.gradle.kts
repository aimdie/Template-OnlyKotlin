apply(from = "publish.gradle.kts")

plugins {
  id("kotlin-platform-common")
}

group = "kill.vanity"
version = "29.7.7"

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.5.0")
//  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
}
