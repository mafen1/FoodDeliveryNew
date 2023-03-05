package com.example.fooddelivery.presentation.welcomeScreen

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.core.log
import com.example.fooddelivery.data.login.models.LoginGoogle
import com.example.fooddelivery.databinding.FragmentWelcomeScreenBinding
import com.example.fooddelivery.presentation.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint

class WelcomeScreen :
    BaseFragment<FragmentWelcomeScreenBinding>(FragmentWelcomeScreenBinding::inflate) {

    private lateinit var mAuth: FirebaseAuth

    override fun initView() {

        activity?.lifecycleScope?.launch(Dispatchers.Main) {
            delay(1500)
            mAuth = FirebaseAuth.getInstance()
            log(mAuth.currentUser.toString())
            userGoogle()
        }
    }

    override fun initObserves() {

    }

    private fun userGoogle() {

        val user = mAuth.currentUser
        if (user == null) {
            log(mAuth.currentUser.toString())

            findNavController().navigate(R.id.action_welcomeScreen_to_welcomeScreen2)
        } else {
            loginGoogle()
        }
    }

    fun loginGoogle() {
        val loginGoogle = LoginGoogle(
            imageURL = mAuth.currentUser?.photoUrl!!,
            email = mAuth.currentUser!!.email!!,
            name = mAuth.currentUser!!.displayName!!
        )
        log(loginGoogle.toString())
        val action = WelcomeScreenDirections.actionWelcomeScreenToHomeFragment(loginGoogle)
        findNavController().navigate(action)
    }

}