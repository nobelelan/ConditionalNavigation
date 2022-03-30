package com.example.conditionalnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentBackStateEntry = findNavController().currentBackStackEntry
        val savedStateHandle = currentBackStateEntry!!.savedStateHandle

        savedStateHandle.getLiveData<Boolean>(LogInFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStateEntry){
                if (!it){
                    Toast.makeText(requireContext(), "Please login to see your profile", Toast.LENGTH_SHORT ).show()

                    val startDest = findNavController().graph.startDestinationId
                    val navOption = NavOptions.Builder()
                        .setPopUpTo(startDest, true)
                        .build()
                    findNavController().navigate(startDest, null, navOption)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        if (UserLoginInfo.user == null){
            Toast.makeText(requireContext(), "Please login first.", Toast.LENGTH_SHORT ).show()

            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()

            findNavController().navigate(R.id.logInFragment,null,navOptions)
        }else{
            val username = UserLoginInfo.user!!.username
            Toast.makeText(requireContext(),"Hi, $username!", Toast.LENGTH_SHORT).show()

            val text = getString(R.string.welcome_to_your_profile,username)

            binding.tvProfile.text = text
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}