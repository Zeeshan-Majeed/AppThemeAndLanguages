package com.brain.themes.applanguages.helper.brainthemesandapplanguages.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.R
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

}