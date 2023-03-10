package com.jeanbarrossilva.aurelius.ui.theme.animation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.spring
import kotlin.time.Duration

/** Holds configuration for animations played in the app.**/
abstract class Animation internal constructor() {
    /** Determines for how long an animation will be run. **/
    protected abstract val durations: AnimationDurations

    /**
     * [FiniteAnimationSpec] for finite animations.
     *
     * @param duration Returns the [Duration] that determines for how long an animation will be run.
     **/
    abstract fun <T> spec(duration: AnimationDurations.() -> Duration = { medium }):
        FiniteAnimationSpec<T>

    companion object {
        /** [Animation] with an [AnimationDurations.Unspecified] and a [spring][spring] [spec]. **/
        internal val Unspecified = object : Animation() {
            override val durations = AnimationDurations.Unspecified

            override fun <T> spec(duration: AnimationDurations.() -> Duration):
                FiniteAnimationSpec<T> {
                return spring()
            }
        }
    }
}
