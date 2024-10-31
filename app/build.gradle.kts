
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.listadecompras"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.listadecompras"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val room_version = "2.5.2"
    implementation("androidx.room:room-ktx:2.5.2")
    implementation (libs.androidx.room.runtime) //Dessas dependências, as duas primeiras, room-runtime e room-ktx , são, respectivamente, a biblioteca principal do Room e extensões ktx como a possibilidade de utilizar funções suspensas em retornos DAO.
    implementation (libs.androidx.lifecycle.viewmodel.ktx) // O viewModelScope faz parte das bibliotecas de lifecycle-viewmodel-ktx. Para corrigir esse erro, você precisa adicionar a dependência correta no arquivo build.gradle do seu projeto:

    annotationProcessor (libs.androidx.room.compiler)
    kapt("androidx.room:room-compiler:$room_version") //O kapt é a ferramenta que processa anotações em Kotlin, por isso o uso dele também é necessário. Para
    //utilizar o kapt , além de declarar sua dependência, é preciso declará-lo como plug-in. Isso é feito nas primeiras linhas do arquivo build.gradle do módulo.

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}