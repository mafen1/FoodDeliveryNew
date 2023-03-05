package com.example.fooddelivery.presentation.loginScreen

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.core.log
import com.example.fooddelivery.core.snackBar
import com.example.fooddelivery.data.login.models.Login
import com.example.fooddelivery.data.login.models.LoginGoogle
import com.example.fooddelivery.databinding.FragmentLoginBinding
import com.example.fooddelivery.presentation.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignIn: GoogleSignInClient
    private lateinit var auth: FirebaseAuth


    override fun initView() {
        googleSignInAccount()
    }

    override fun initObserves() {
        binding.button.setOnClickListener {
            insertToDataBase()
        }
        binding.button3.setOnClickListener {
            signAccount()
            googleSignInAccount()

        }
        binding.button4.setOnClickListener {
            googleSignIn.signOut()
            Firebase.auth.signOut()
        }
    }

    private fun signAccount() {
        val signIntent = googleSignIn.signInIntent
        startActivityForResult(signIntent, 1)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        log(requestCode.toString())
        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                log(e.toString())
                snackBar(binding.root, "something went wrong")
            }
        }
    }

    private fun googleSignInAccount() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignIn = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth
        log(auth.toString())

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    log("signInWithCredential:success")
                    val user = auth.currentUser
                    val loginGoogle = LoginGoogle(
                        imageURL = user?.photoUrl!!,
                        email = user.email!!,
                        name = user.displayName!!
                    )
                    log(loginGoogle.toString())
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment(loginGoogle)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    log("signInWithCredential:failure" + task.exception)
                }
            }
    }

    private fun insertToDataBase() {
        val email = binding.editTextTextPersonName.text.toString()
        val password = binding.editTextTextPersonName2.text.toString()

        val login = Login(
            email = email,
            password = password
        )
//        val loginGoogle = LoginGoogle(
//            imageURL = "fghgf",
//            email = user.email!!,
//            name = user.displayName!!
//        )
//        log(loginGoogle.toString())
//        val action =
//            LoginFragmentDirections.actionLoginFragmentToHomeFragment(loginGoogle)
//        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        viewModel.loginPeople(login)


        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }


}