package sokeriaaa.sugarkane.compose.ui.nav

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun BackPressedHandler(enabled: Boolean, onBack: () -> Unit) =
    BackHandler(enabled, onBack)