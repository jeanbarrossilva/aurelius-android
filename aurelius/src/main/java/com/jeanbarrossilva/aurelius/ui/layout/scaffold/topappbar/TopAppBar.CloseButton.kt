package com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.annotation.RestrictTo
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.R
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

/** Tag that identifies the [CloseButton] for testing purposes. **/
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
const val TOP_APP_BAR_CLOSE_BUTTON_TAG = "${TOP_APP_BAR_TAG}_close_button"

/**
 * Navigation button for closing.
 *
 * @param onClick Callback run whenever it's clicked.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 * @param contentDescription Describes what this [CloseButton] does.
 * @param tint [Color] to color the icon with.
 **/
@Composable
fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(R.string.aurelius_top_app_bar_close_button),
    tint: Color = LocalContentColor.current
) {
    IconButton(onClick, modifier.testTag(TOP_APP_BAR_CLOSE_BUTTON_TAG)) {
        Icon(
            Icons.Rounded.Close,
            contentDescription,
            Modifier.size(TopAppBarDefaults.NavigationButtonIconSize),
            tint
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CloseButtonPreview() {
    AureliusTheme {
        CloseButton(onClick = { })
    }
}
