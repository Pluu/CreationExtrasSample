package com.pluu.sample.creationextras

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CreationExtrasTest {
    @Test
    fun testInitialCreationExtras() {
        val initial = MutableCreationExtras()
        val key = object : CreationExtras.Key<Bundle> {}
        initial[key] = bundleOf("value" to "initial")
        val mutable = MutableCreationExtras(initial)
        initial[key] = bundleOf("value" to "overridden")
        assertEquals(mutable[key]?.getString("value"), "initial")
    }
}