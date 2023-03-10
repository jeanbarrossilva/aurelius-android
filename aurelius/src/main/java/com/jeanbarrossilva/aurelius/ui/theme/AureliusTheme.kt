package com.jeanbarrossilva.aurelius.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import com.jeanbarrossilva.aurelius.composition.LocalMinimumTouchTargetProvider
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.animation
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.colors
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.shapes
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.sizes
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.text
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.visibility
import com.jeanbarrossilva.aurelius.ui.theme.animation.Animation
import com.jeanbarrossilva.aurelius.ui.theme.animation.AnimationProvider
import com.jeanbarrossilva.aurelius.ui.theme.animation.LocalAnimation
import com.jeanbarrossilva.aurelius.ui.theme.colors.Colors
import com.jeanbarrossilva.aurelius.ui.theme.colors.ColorsProvider
import com.jeanbarrossilva.aurelius.ui.theme.colors.LocalColors
import com.jeanbarrossilva.aurelius.ui.theme.shapes.LocalShapes
import com.jeanbarrossilva.aurelius.ui.theme.shapes.Shapes
import com.jeanbarrossilva.aurelius.ui.theme.shapes.ShapesProvider
import com.jeanbarrossilva.aurelius.ui.theme.sizes.LocalSizes
import com.jeanbarrossilva.aurelius.ui.theme.sizes.Sizes
import com.jeanbarrossilva.aurelius.ui.theme.sizes.SizesProvider
import com.jeanbarrossilva.aurelius.ui.theme.text.LocalText
import com.jeanbarrossilva.aurelius.ui.theme.text.Text
import com.jeanbarrossilva.aurelius.ui.theme.text.TextProvider
import com.jeanbarrossilva.aurelius.ui.theme.visibility.LocalVisibility
import com.jeanbarrossilva.aurelius.ui.theme.visibility.Visibility
import com.jeanbarrossilva.aurelius.ui.theme.visibility.VisibilityProvider

/**
 * Provider of the theme's configuration values, such as [animation], [colors], [sizes], [text] and
 * [visibility].
 **/
object AureliusTheme {
    /** Whether or not an [AureliusTheme] has been provided. **/
    internal val isProvided
        @Composable get() = animation != Animation.Unspecified && colors != Colors.Unspecified &&
            shapes != Shapes.Unspecified && sizes != Sizes.Unspecified &&
            text != Text.Unspecified && visibility != Visibility.Unspecified

    /** Current [Animation] from [LocalAnimation]. **/
    val animation
        @Composable get() = LocalAnimation.current

    /** Current [Colors] from [LocalColors]. **/
    val colors
        @Composable get() = LocalColors.current

    /** Current [Shapes] from [LocalShapes]. **/
    val shapes
        @Composable get() = LocalShapes.current

    /** Current [Sizes] from [LocalSizes]. **/
    val sizes
        @Composable get() = LocalSizes.current

    /** Current [Text] from [LocalText]. **/
    val text
        @Composable get() = LocalText.current

    /** Current [Visibility] from [LocalVisibility]. **/
    val visibility
        @Composable get() = LocalVisibility.current

    /**
     * Throws an [IllegalStateException] if an [AureliusTheme] hasn't been provided.
     *
     * @param context Context in which the theme is required.
     **/
    @Composable
    @Suppress("ComposableNaming")
    internal fun requireFor(context: String) {
        assert(context.isNotBlank()) {
            "Context should be provided for a more descriptive error message."
        }

        if (!isProvided) {
            error("$context requires an AureliusTheme.")
        }
    }
}

/**
 * Themes the given [content] by providing [colors], [text], [sizes] and [visibility][visibility]
 * values to their respective [CompositionLocal]s and sets up the system bars.
 *
 * @param colors [Colors] for coloring the [content].
 * @param content Content to be themed.
 **/
@Composable
fun AureliusTheme(colors: Colors = Colors.default, content: @Composable () -> Unit) {
    AnimationProvider {
        ColorsProvider(colors) {
            SystemBarsConfigurationEffect()

            LocalMinimumTouchTargetProvider {
                ShapesProvider {
                    SizesProvider {
                        TextProvider {
                            VisibilityProvider {
                                MaterialTheme(
                                    colors.material,
                                    shapes.material,
                                    text.material,
                                    content
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
