package com.example.rxjavasample.presentation.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rxjavasample.R
import com.example.rxjavasample.databinding.FragmentLoginBinding
import com.example.rxjavasample.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.emailET.doOnTextChanged { text, start, before, count ->
            viewModel.isEmailValid = Patterns.EMAIL_ADDRESS.matcher(binding.emailET.text).matches()
            binding.invalidEmail.visibility = if (!viewModel.isEmailValid) View.VISIBLE else View.INVISIBLE
            binding.submitBtn.isEnabled = viewModel.enableSubmitButton()
        }
        binding.passwordET.doOnTextChanged { text, start, before, count ->
            viewModel.isPasswordValid = binding.passwordET.text.length in 8..15
            binding.invalidPass.visibility = if (!viewModel.isPasswordValid)  View.VISIBLE else View.INVISIBLE
            binding.submitBtn.isEnabled = viewModel.enableSubmitButton()
        }
        binding.submitBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_postListFragment)
        }
    }
}