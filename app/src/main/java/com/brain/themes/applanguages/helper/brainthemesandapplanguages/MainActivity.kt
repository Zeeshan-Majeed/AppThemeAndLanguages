package com.brain.themes.applanguages.helper.brainthemesandapplanguages

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.databinding.ActivityMainBinding
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.LANGUAGES_LIST
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.LanguageUtils
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.LanguageUtils.getSystemDefaultLanguage
import com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils.MyPreference

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        languageConfig()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        with(binding) {
            bottomNav.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    /*R.id.splashFragment,R.id.homeFragment->{

                    }*/
                    R.id.splashFragment -> {
                        showBottomNav(false)
                    }

                    else ->
                        showBottomNav(true)
                }
            }
        }
    }

    private fun showBottomNav(hide: Boolean) {
        binding.bottomNav.isVisible = hide
    }

    private val myPref by lazy {
        MyPreference(this)
    }

    private fun languageConfig() {
        try {
            if (this.getSystemDefaultLanguage().take(2) != "en" && myPref.getAppLanguagesCode()
                    .isBlank()
            ) {

                LanguageUtils.setUserSelectedLanguageForApp(LANGUAGES_LIST.first { it.languageCode == "en" })
            }
        } catch (_: Exception) {

        } catch (_: java.lang.Exception) {
        }

        if (myPref.getAppLanguagesCode() in arrayOf(
                "ar", "ur", "fa"
            )
        ) window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        else window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
    }

}