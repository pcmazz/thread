package com.falcotech.mazz.scope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falcotech.mazz.scopelibrary.core.DefaultPromiseManager
import com.falcotech.mazz.scopelibrary.factory.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val scope = mainCoroutineScope()


    private val factory = viewModelFactory {
        MainViewModel(DefaultPromiseManager(), SomeRepository(iOCoroutineScope(), unconfinedCoroutineScope()))
    }

    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        // share the same Job as the `scope` property above, but use the IO dispatcher as default
        val ioDefaultScope = scope + Dispatchers.IO

        viewModel.messageFlow
            .onEach { Timber.v("I'm using the default dispatcher!") }
            // extract the default dispatcher from the CoroutineScope and apply it upstream
            .flowOn(scope.defaultDispatcher)
            .onEach { message ->
                Timber.v("I'm using the main dispatcher!")
                tvMessage.text = message
            }
            .onCompletion { tvMessage.text = tvMessage.text.toString() + "\nAll done!" }
            // the .flowOn____() operator pulls the desired dispatcher out of the CoroutineScope
            // and applies it.  So in this case .flowOnMain() is pulling
            // the dispatcher assigned to "main" out of `ioDefaultScope`
            // and dispatching upstream execution
            .flowOnMain()
            .onEach { Timber.v("I'm using the IO dispatcher!") }
            // the default dispatcher in this scope is now Dispatchers.IO
            .launchIn(ioDefaultScope)
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> viewModelFactory(crossinline f: () -> VM):
        ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
    }

