package com.example.kittytinder.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Festus Kiambi on 6/15/19.
 */
abstract class BaseViewModel<T>(protected val uiContext: CoroutineContext) : ViewModel(), CoroutineScope {

    abstract fun handleEvent(event: T)

    private var jobTracker: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + jobTracker
}