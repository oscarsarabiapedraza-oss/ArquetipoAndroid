package com.example.arquetipo.ui.login

import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    private val app = ApplicationProvider.getApplicationContext<android.app.Application>()

    @Test
    fun testInvalidEmailShowsError() {
        val vm = LoginViewModel(app)
        var lastStatus: String? = null
        vm.status.observeForever { lastStatus = it }
        vm.login("invalid-email", "password", {})
        assertEquals("Email inválido", lastStatus)
    }

    @Test
    fun testShortPasswordShowsError() {
        val vm = LoginViewModel(app)
        var lastStatus: String? = null
        vm.status.observeForever { lastStatus = it }
        vm.login("a@b.com", "123", {})
        assertEquals("La contraseña debe tener al menos 6 caracteres", lastStatus)
    }
}
