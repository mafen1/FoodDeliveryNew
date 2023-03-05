package com.example.fooddelivery.presentation.welcomeScreen

import androidx.navigation.fragment.findNavController
import com.example.fooddelivery.presentation.BaseFragment
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FragmentWelcomeScreen2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeScreen2 :
    BaseFragment<FragmentWelcomeScreen2Binding>(FragmentWelcomeScreen2Binding::inflate) {
    override fun initView() {
        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreen2_to_loginFragment)
        }
    }

    override fun initObserves() {

    }

}