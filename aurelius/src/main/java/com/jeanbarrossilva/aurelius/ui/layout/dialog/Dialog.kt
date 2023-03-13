package com.jeanbarrossilva.aurelius.ui.layout.dialog

import android.content.res.Configuration
import androidx.annotation.RestrictTo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

/** Tag that identifies the [Dialog] for testing purposes. **/
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
const val DIALOG_TAG = "dialog"

/**
 * Displays information that should, ideally, be prioritized over what the user was doing before in
 * the form of an overlay.
 *
 * @param title Concise description of the main purpose of the appearance of this [Dialog].
 * @param body Thorough explanation of what's being done and the possible consequences of it.
 * @param onDismissalRequest Callback run when this [Dialog] gets dismissed.
 * @param modifier [Modifier] to be applied to the underlying
 * [Dialog][androidx.compose.ui.window.Dialog].
 * @param buttons Actions that can be taken regarding what's being communicated through the [title]
 * and the [body]. Usually there's a confirmation action, represented by a [ConfirmationButton], and
 * a denial one, normally represented by a [NeutralButton] (and the latter often dismisses the
 * [Dialog]).
 **/
@Composable
fun Dialog(
    title: @Composable () -> Unit,
    body: @Composable () -> Unit,
    onDismissalRequest: () -> Unit,
    modifier: Modifier = Modifier,
    buttons: @Composable (RowScope.() -> Unit)? = null
) {
    AureliusTheme.requireFor("Dialog")

    Dialog(onDismissalRequest) {
        Column(
            modifier
                .clip(AureliusTheme.shapes.large)
                .background(AureliusTheme.colors.background)
                .testTag(DIALOG_TAG)
        ) {
            Split(Modifier.padding(AureliusTheme.sizes.spacing.large)) {
                Headline(title, body)
            }

            buttons?.let {
                Split(
                    Modifier
                        .fillMaxWidth()
                        .background(AureliusTheme.colors.container.tertiary)
                        .padding(AureliusTheme.sizes.spacing.medium),
                    it
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DialogPreview() {
    AureliusTheme {
        Dialog(
            title = { Text("Attention!") },
            body = { Text("This is a very important dialog. Tap \"OK\" to proceed.") },
            onDismissalRequest = { }
        ) {
            NeutralButton(onClick = { }) {
                Text("Nah, I'm good")
            }

            ConfirmationButton(onClick = { })
        }
    }
}
