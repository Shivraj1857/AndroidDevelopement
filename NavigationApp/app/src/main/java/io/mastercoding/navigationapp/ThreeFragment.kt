package io.mastercoding.navigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import io.mastercoding.navigationapp.databinding.FragmentHomeBinding
import io.mastercoding.navigationapp.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {
    private lateinit var binding: FragmentThreeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_three, container, false)
        binding.btn3.setOnClickListener {
            it.findNavController().navigate(R.id.action_threeFragment_to_homeFragment)
        }
        return binding.root
    }



    }
