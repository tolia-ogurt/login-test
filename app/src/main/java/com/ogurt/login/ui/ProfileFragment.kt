package com.ogurt.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ogurt.login.R
import com.ogurt.login.databinding.ProfileFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private val binding by lazy { ProfileFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        activity?.title = getString(R.string.profile)
        getUserInfo()
        return binding.root
    }


    private fun getUserInfo() {
        viewModel.getUserInfoToDB()
        viewModel.userInfo.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvSurname.text = it.surName
            binding.tvPhoneNumber.text = it.phoneCode + it.phoneNumber
        }
    }
}