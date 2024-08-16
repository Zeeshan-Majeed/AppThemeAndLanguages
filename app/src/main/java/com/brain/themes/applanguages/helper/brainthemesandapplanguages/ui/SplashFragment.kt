package com.brain.themes.applanguages.helper.brainthemesandapplanguages.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.R
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private val binding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnContinue.setOnClickListener {
                findNavController().apply {
                    if (currentDestination?.id == R.id.splashFragment) {
                        popBackStack()
                        navigate(R.id.homeFragment)
                    }
                }
            }
        }
    }

}