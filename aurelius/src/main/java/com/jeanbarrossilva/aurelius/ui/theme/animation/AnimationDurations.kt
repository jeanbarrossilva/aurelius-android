package com.jeanbarrossilva.aurelius.ui.theme.animation

import kotlin.time.Duration

/**
 * [Duration]s that determine for how long an animation will run.
 *
 * @param fast Fastest [Duration].
 * @param medium Slower than [fast], faster than [slow].
 * @param slow Slowest [Duration].
 **/
data class AnimationDurations internal constructor(
    val fast: Duration,
    val medium: Duration,
    val slow: Duration
) {
    companion object {
        /** [AnimationDurations] with [Duration.ZERO] values. **/
        internal val Unspecified =
            AnimationDurations(fast = Duration.ZERO, medium = Duration.ZERO, slow = Duration.ZERO)
    }
}
