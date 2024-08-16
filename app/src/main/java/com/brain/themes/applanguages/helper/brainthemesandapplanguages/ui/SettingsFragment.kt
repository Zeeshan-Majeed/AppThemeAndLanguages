package com.brain.themes.applanguages.helper.brainthemesandapplanguages.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.R
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.databinding.BottomSheetThemesBinding
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.databinding.FragmentSettingsBinding
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.MyPreference
import com.google.android.material.bottomsheet.BottomSheetDialog


class SettingsFragment : Fragment() {
    private val binding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }

    private val bottomSheet by lazy {
        BottomSheetDialog(requireContext())
    }
    private val bottomSheetBinding by lazy {
        BottomSheetThemesBinding.inflate(layoutInflater)
    }
    private val myPref by lazy {
        MyPreference(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.tvScreen.text = getString(R.string.settings)
            updateThemeText()
            btnTheme.setOnClickListener {
                showBottomSheet()
            }
        }
    }

    private fun showBottomSheet() {
        bottomSheet.setContentView(bottomSheetBinding.root)
        with(bottomSheetBinding) {
            btnConfirm.setOnClickListener {
                bottomSheet.dismiss()
                if (btnSystemDefault.isChecked) {
                    myPref.setAppTheme(0)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else if (btnLight.isChecked) {
                    myPref.setAppTheme(1)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else if (btnNight.isChecked) {
                    myPref.setAppTheme(2)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                updateThemeText()
            }
        }
        bottomSheet.show()
    }

    private fun updateThemeText() {
        with(bottomSheetBinding) {
            when (myPref.getAppTheme()) {
                0 -> {
                    btnSystemDefault.isChecked = true
                    binding.tvTheme.text = getString(R.string.system_default)
                }

                1 -> {
                    btnLight.isChecked = true
                    binding.tvTheme.text = getString(R.string.light)
                }

                2 -> {
                    btnNight.isChecked = true
                    binding.tvTheme.text = getString(R.string.night)
                }
            }
        }
    }

}