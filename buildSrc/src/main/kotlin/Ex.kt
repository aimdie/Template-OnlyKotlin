import org.gradle.api.Project
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*
import org.gradle.plugins.signing.SigningExtension
import java.net.URI

/**
 * 总源码。
 */
val Project.sourceSets: SourceSetContainer
  get() = extensions.getByName("sourceSets") as SourceSetContainer

/**
 * main目录的源码。
 */
val SourceSetContainer.main: SourceSet
  get() = getByName("main")

/**
 * 签名类。
 */
val Project.signing: SigningExtension
  get() = extensions.getByName<SigningExtension>("signing")

//////////////////////////////////////////////////
/**
 * src目录的所有类，作为库。
 */
fun Project.addLibKotlin(publication: MavenPublication) {
  publication.from(components["kotlin"])
}

/**
 * 打包源码。
 */
fun Project.addSourceJar(mavenPublication: MavenPublication) {
  val sourceTask: TaskProvider<Jar>
    by tasks.registering(Jar::class) {
      archiveClassifier.set("source")
      from(sourceSets.main.allSource)
    }
  mavenPublication.artifact(sourceTask.get())
}
//////////////////////////////////////////////////

/**
 * 签名发布。
 */
fun Project.sign(pc: PublicationContainer) {
  signing.sign(pc)
}

/**
 * 签名发布。
 */
fun Project.sign(mavenPublication: MavenPublication) {
  signing.sign(mavenPublication)
}

//////////////////////////////////////////////////
fun MavenPublication.setPomPersonal(
  mGroupId: String,
  mArtifactId: String,
  mLibVersion: String,
  mLibName: String,
  mLibDescription: String,
  mLibUrl: String,
  mLicenseName: String,
  mLicenseUrl: String,
  mDevId: String
) {
  groupId = mGroupId
  artifactId = mArtifactId
  pom {
    version = mLibVersion
    name.set(mLibName)
    description.set(mLibDescription)
    url.set(mLibUrl)
    licenses {
      license {
        name.set(mLicenseName)
        url.set(mLicenseUrl)
      }
    }
    
    developers {
      developer {
        id.set(mDevId)
      }
    }
  }
}

fun PublishingExtension.setMvnRepository(uri: URI) {
  repositories {
    maven {
      url = uri
    }
  }
}