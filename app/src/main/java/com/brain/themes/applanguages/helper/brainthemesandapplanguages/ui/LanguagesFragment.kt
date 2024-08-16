package com.brain.themes.applanguages.helper.brainthemesandapplanguages.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.adapters.LanguageAdapter
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.models.Languages
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.R
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.databinding.FragmentLanguagesBinding
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.LANGUAGES_LIST
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.LanguageUtils.setUserSelectedLanguageForApp
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.MyPreference
import kotlinx.coroutines.launch


class LanguagesFragment : Fragment() {

    private val binding by lazy {
        FragmentLanguagesBinding.inflate(layoutInflater)
    }

    private var selectedLang: String? = null
    private var selectedItem: Languages? = null
    private var adapter: LanguageAdapter? = null

    private val myPref by lazy {
        MyPreference(requireContext())
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
            toolbar.tvScreen.text = getString(R.string.languages)
            adapter = LanguageAdapter(
                LANGUAGES_LIST,
                object : LanguageAdapter.OnItemClicked {
                    override fun onClicked(item: Languages, position: Int) {
                        LANGUAGES_LIST.forEach {
                            it.isSelected = false
                        }
                        item.isSelected = true
                        selectedItem = item
                        notifyAdapter()
                    }
                })
            recyclerView.adapter = adapter

            selectedLang = myPref.getAppLanguagesName()
            for (i in LANGUAGES_LIST.indices) {
                if (LANGUAGES_LIST[i].languageName == selectedLang) {
                    LANGUAGES_LIST[i].isSelected = true
                }
            }

            btnConfirm.setOnClickListener {
                Log.i(
                    "check_current", "selectedItem: $selectedItem\n selectedLang: $selectedLang\n" +
                            "name: ${selectedItem?.languageName}"
                )
                if (selectedItem == null || selectedLang == selectedItem?.languageName) {
                    // do noting
                } else {

                    Log.i("check_current", "in else: ")
                    selectedItem?.languageName?.let { it1 ->
                        Log.i("check_current", "name to set: $it1")
                        myPref.setAppLanguageName(it1)
                    }
                    selectedItem?.languageCode?.let { it1 ->
                        Log.i("check_current", "code to set: $it1")
                        myPref.setAppLanguageCode(it1)
                    }
                    setUserSelectedLanguageForApp(selectedItem!!)

                }
            }
        }
    }

    private fun notifyAdapter() {
        adapter?.notifyDataSetChanged()
    }

}