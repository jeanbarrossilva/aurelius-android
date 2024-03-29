import org.gradle.api.JavaVersion

@Suppress("SpellCheckingInspection")
object Versions {
    const val ACCOMPANIST = "0.25.1"
    const val APPCOMPAT = "1.5.1"
    const val COMPOSE_COMPILER = "1.4.2"
    const val COMPOSE_MATERIAL = "1.3.1"
    const val COMPOSE_MATERIAL_3 = "1.0.1"
    const val COMPOSE_UI = "1.3.3"
    const val CONSTRAINTLAYOUT_COMPOSE = "1.0.1"
    const val CUSTOMVIEW = "1.1.0"
    const val CUSTOMVIEW_POOLINGCONTAINER = "1.0.0"
    const val FRAGMENT = "1.5.3"
    const val GRADLE = "7.4.1"
    const val JUNIT = "4.13.2"
    const val KOTLIN = "1.8.10"
    const val LIFECYCLE = "2.5.1"
    const val MATERIAL = "1.6.1"
    const val TEST_RUNNER = "1.5.2"

    val java = JavaVersion.VERSION_11

    object Aurelius {
        const val CODE = 12
        const val NAME = "1.7.0"
        const val SDK_COMPILE = 33
        const val SDK_MIN = 21
        const val SDK_TARGET = SDK_COMPILE
    }
}
