package com.jeanbarrossilva.aurelius.ui.layout.scaffold.menudrawer

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.swipeable
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.plus
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

internal val backgroundColor
    @Composable get() = AureliusTheme.colors.background

/**
 * A sliding drawer.
 *
 * @param title Brief description of what the [items] represent.
 * @param items [Item]s to be shown. Usually the [MenuDrawer] closes when any of them gets
 * clicked.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 * @param scope [MenuDrawerScope] that'll be used for the [items] and the [content].
 * @param content Main content, displayed behind this [MenuDrawer].
 **/
@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
fun MenuDrawer(
    title: @Composable () -> Unit,
    items: @Composable MenuDrawerScope.() -> Unit,
    modifier: Modifier = Modifier,
    scope: MenuDrawerScope = rememberMenuDrawerScope(),
    content: @Composable MenuDrawerScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeableState = scope.swipeableState
    var menuDrawerWidth by remember { mutableStateOf<MenuDrawerWidth>(MenuDrawerWidth.Unspecified) }
    val isMenuDrawerOpen = swipeableState.currentValue == DrawerValue.Open

    Box(
        modifier.swipeable(
            swipeableState,
            anchors = mapOf(-menuDrawerWidth.value to DrawerValue.Closed, 0f to DrawerValue.Open),
            Orientation.Horizontal
        )
    ) {
        scope.content()

        if (isMenuDrawerOpen) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(AureliusTheme.colors.scrim)
                    .alpha(swipeableState.progress.fraction)
                    .clickable(MutableInteractionSource(), indication = null) {
                        coroutineScope.launch {
                            scope.close()
                        }
                    }
            )
        }

        MenuDrawer(
            title,
            Modifier
                .onPlaced {
                    if (menuDrawerWidth is MenuDrawerWidth.Unspecified) {
                        menuDrawerWidth = MenuDrawerWidth.Specified(it.size.width.toFloat())
                    }
                }
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), y = 0) },
            scope,
            items
        )
    }
}

/**
 * A sliding drawer.
 *
 * @param title Brief description of what this [MenuDrawer] holds.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 * @param content Main content, displayed behind this [MenuDrawer].
 **/
@Composable
fun MenuDrawer(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    scope: MenuDrawerScope = rememberMenuDrawerScope(),
    content: @Composable MenuDrawerScope.() -> Unit
) {
    val scrollableState = rememberScrollState()
    val shape =
        AureliusTheme.shapes.huge.copy(topStart = ZeroCornerSize, bottomStart = ZeroCornerSize)
    val spacing = AureliusTheme.sizes.spacing.huge

    Column(
        modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight()
            .clip(shape)
            .background(backgroundColor)
            .scrollable(scrollableState, Orientation.Vertical)
            .padding(
                AureliusTheme.sizes.margin.statusBar + AureliusTheme.sizes.margin.navigationBar
            )
            .padding(spacing),
        Arrangement.spacedBy(spacing)
    ) {
        ProvideTextStyle(AureliusTheme.text.headline, title)
        scope.Items(content = content)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MenuDrawerPreview() {
    AureliusTheme {
        MenuDrawer(
            title = { Text("Title") },
            items = {
                Item(
                    Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    isSelected = true,
                    onClick = { }
                ) {
                    Text("Settings")
                }

                Item(
                    Icons.Rounded.ThumbUp,
                    contentDescription = "Rate",
                    isSelected = false,
                    onClick = { }
                ) {
                    Text("Rate")
                }
            }
        ) {
            Background {
            }
        }
    }
}
