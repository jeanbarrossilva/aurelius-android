package com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.annotation.RestrictTo
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.R
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

/** Tag that identifies the [DeleteAction] for testing purposes. **/
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
const val TOP_APP_BAR_DELETE_ACTION_TAG = "${TOP_APP_BAR_TAG}_delete_action"

/**
 * [ActionButton] for deleting.
 *
 * @param onClick Callback run whenever it's clicked.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 **/
@Composable
fun DeleteAction(onClick: () -> Unit, modifier: Modifier = Modifier) {
    ActionButton(onClick, modifier.testTag(TOP_APP_BAR_DELETE_ACTION_TAG)) {
        Icon(
            Icons.Rounded.Delete,
            contentDescription = stringResource(R.string.aurelius_top_app_bar_delete_action)
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DeleteActionPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            DeleteAction(onClick = { })
        }
    }
}
