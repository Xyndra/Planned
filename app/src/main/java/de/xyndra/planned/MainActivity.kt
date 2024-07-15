package de.xyndra.planned

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import de.xyndra.planned.ui.theme.PlannedTheme
import java.lang.ref.WeakReference

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContextStore.setContext(this)
        setContent {
            PlannedTheme {
                AppNavigator()
            }
        }
    }
}

object ContextStore {
    private var ctx: WeakReference<Context?> = WeakReference(null)

    fun setContext(context: Context?) {
        ctx = WeakReference(context)
    }

    fun getContext(): Context? = ctx.get()
}