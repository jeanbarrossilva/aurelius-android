package com.jeanbarrossilva.aurelius.ui.theme.animation

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf

/** [CompositionLocal] that provides an [Animation]. **/
internal val LocalAnimation = compositionLocalOf {
    Animation.Unspecified
}
