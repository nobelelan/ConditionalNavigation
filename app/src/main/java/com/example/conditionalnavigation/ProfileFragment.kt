package com.example.conditionalnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.conditionalnavigation.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        val username = UserLoginInfo.user!!.username
        Toast.makeText(requireContext(),"Hi, $username!", Toast.LENGTH_SHORT).show()

        val text = getString(R.string.welcome_to_your_profile,username)

        binding.tvProfile.text = text

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}