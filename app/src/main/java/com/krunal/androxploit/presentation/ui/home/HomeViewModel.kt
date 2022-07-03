package com.krunal.androxploit.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.krunal.androxploit.presentation.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val navigationManager: NavigationManager
) : ViewModel() {

}