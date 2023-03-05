package com.example.fooddelivery.presentation.homeScreen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fooddelivery.core.ApiState
import com.example.fooddelivery.core.snackBar
import com.example.fooddelivery.databinding.FragmentHomeBinding
import com.example.fooddelivery.presentation.BaseFragment
import com.example.fooddelivery.presentation.CustomLoadingDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val args by navArgs<HomeFragmentArgs>()
    private val viewModel by viewModels<HomeViewModel>()
    private val customLoadingDialog = CustomLoadingDialog()


    override fun initView() {
        initUser()
        viewModel.response()
    }

    override fun initObserves() {
        binding.button5.setOnClickListener {
            Firebase.auth.signOut()
        }
        viewModel.responseInfo.observe(this) {
            when (it) {
                ApiState.Loading -> {
                    customLoadingDialog.startLoadingDialog(requireContext(), requireActivity())
                }
                ApiState.ApiEmpty -> viewModel.response()

                is ApiState.Error -> {
                    snackBar(binding.root, "something wrong")
                }
                is ApiState.Success<*> -> {
                    customLoadingDialog.closeDialog()
                }
                else -> {}
            }
        }
    }

    private fun initUser() {
        Glide.with(requireActivity())
            .load(args.currentUser.imageURL)
            .centerCrop()
            .circleCrop()
            .into(binding.imageView5)

        binding.tvUserName.text = args.currentUser.name
    }
}