package com.ogurt.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ogurt.login.R
import com.ogurt.login.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private val binding by lazy { LoginFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        activity?.title = getString(R.string.sign_in)
        authorization()
        subscribeLiveData()
        return binding.root
    }

    private fun authorization() {
        binding.btnContinue.setOnClickListener {
            viewModel.login(
                binding.etPhoneNumber.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun subscribeLiveData() = with(viewModel) {
        userInfo.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        error.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
        }
        onInvalidPhoneNumber.observe(viewLifecycleOwner) {
            binding.etPhoneNumber.error = "Incorect phone"
        }
    }
}