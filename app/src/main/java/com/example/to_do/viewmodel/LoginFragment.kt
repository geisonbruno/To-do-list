package com.example.to_do.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.to_do.R
import androidx.navigation.fragment.findNavController
import com.example.to_do.viewmodel.LoginFragmentDirections

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonLogin = view.findViewById<ImageView>(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToTaskFragment()
            findNavController().navigate(action)
        }
    }
}
