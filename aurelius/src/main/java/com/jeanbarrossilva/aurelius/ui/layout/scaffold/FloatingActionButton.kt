package com.jeanbarrossilva.aurelius.ui.layout.scaffold

import android.content.res.Configuration
import androidx.annotation.RestrictTo
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FloatingActionButton as _FloatingActionButton
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

/** Tag that identifies the [FloatingActionButton] for testing purposes. **/
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
const val FLOATING_ACTION_BUTTON_TAG = "floating_action_button"

/**
 * Represents the main action that can be taken in the current context.
 *
 * @param onClick Callback run whenever it's clicked.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 * @param isVisible Whether or not this [FloatingActionButton][_FloatingActionButton] is visible.
 * @param containerColor [Color] to color the container behind the [content] with.
 * @param contentColor [Color] to color the [content] with.
 * @param content Content to be shown inside.
 **/
@Composable
fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        isVisible,
        enter = fadeIn(AureliusTheme.animation.spec()) +
            slideInVertically(AureliusTheme.animation.spec { fast }) { it },
        exit = fadeOut(AureliusTheme.animation.spec()) +
            slideOutVertically(AureliusTheme.animation.spec { fast }) { it }
    ) {
        FloatingActionButton(
            onClick,
            modifier.testTag(FLOATING_ACTION_BUTTON_TAG),
            containerColor = containerColor,
            contentColor = contentColor,
            content = content
        )
    }
}

@Composable
internal fun FloatingActionButton(modifier: Modifier = Modifier) {
    _FloatingActionButton(onClick = { }, modifier) {
        Icon(Icons.Rounded.Add, contentDescription = "Add")
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FloatingActionButtonPreview() {
    AureliusTheme {
        _FloatingActionButton()
    }
}
