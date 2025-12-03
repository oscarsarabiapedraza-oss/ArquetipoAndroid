package com.example.arquetipo.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.arquetipo.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.tvRegisterLink.setOnClickListener {
            findNavController().navigate(com.example.arquetipo.R.id.action_login_to_register)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            vm.login(email, pass) {
                // on success navigate to home
                findNavController().navigate(com.example.arquetipo.R.id.action_login_to_home)
            }
        }

        vm.status.observe(viewLifecycleOwner) { binding.tvStatus.text = it }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
