package com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.R
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

/**
 * Navigation button for opening up a menu.
 *
 * @param onClick Callback run whenever it's clicked.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 **/
@Composable
fun MenuButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick, modifier) {
        Icon(
            Icons.Rounded.Menu,
            contentDescription = stringResource(R.string.aurelius_top_app_bar_menu_button)
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MenuButtonPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            MenuButton(onClick = { })
        }
    }
}
