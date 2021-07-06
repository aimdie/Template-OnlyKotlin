val mGroupId = "kill.vanity"
val mDevId = "aimdie"
val mLicenseName = "The Apache License, Version 2.0"
val mLicenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"

val mArtifactId = "lib-test"
val mLibVersion = "29.1.1"
val mLibName = "Lib Test"
val mLibDescription = "类库。"
val mLibUrl = "git@github.com:aimdie/Test.git"
val mMvnUrl = ""

/**
 * 之后：构建project。
 */
afterEvaluate {
  extra.set("signing.keyId", System.getenv("SIGN_ID"))
  extra.set("signing.password", System.getenv("SIGN_PASSWORD"))
  extra.set("signing.secretKeyRingFile", System.getenv("SIGN_FILE"))
  
  plugins.apply("signing")
  plugins.apply("maven-publish")
  extensions.configure<PublishingExtension>("publishing") {
    publications.create<MavenPublication>("maven") {
      addSourceJar(this)
      addLibKotlin(this)
      sign(this)
      
      setPomPersonal(mGroupId,mArtifactId,
        mLibVersion,mLibName,mLibDescription, mLibUrl,
        mLicenseName,mLicenseUrl,mDevId)
  
    }
    setMvnRepository(uri(mMvnUrl))
  }
}

//val mArtifactId = ""
//val mLibVersion = ""
//val mLibName = ""
//val mLibDescription = ""
//val mLibUrl = ""
//val mMvnUrl = ""

