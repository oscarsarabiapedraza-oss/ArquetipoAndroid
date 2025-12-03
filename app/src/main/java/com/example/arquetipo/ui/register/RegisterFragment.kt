package com.example.arquetipo.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.arquetipo.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            vm.register(name, email, pass) {
                // after registering, go back to login
                findNavController().navigate(com.example.arquetipo.R.id.action_register_to_login)
            }
        }

        vm.status.observe(viewLifecycleOwner) { binding.tvRegisterStatus.text = it }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
