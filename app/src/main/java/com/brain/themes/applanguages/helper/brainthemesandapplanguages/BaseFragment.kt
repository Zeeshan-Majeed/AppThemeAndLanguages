package com.brain.themes.applanguages.helper.brainthemesandapplanguages

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


const val TAG = "general_app_tag"

open class BaseFragment : Fragment() {

    private val navOptions = NavOptions.Builder()
        .setEnterAnim(android.R.anim.fade_in)
        .setExitAnim(android.R.anim.fade_out)
        .setPopEnterAnim(android.R.anim.fade_in)
        .setPopExitAnim(android.R.anim.fade_out).build()


    fun navigateToNextScreen(
        fragmentId: Int, removeCurrent: Boolean = false, bundle: Bundle = bundleOf()
    ) {
        if (view != null) {
            activity?.runOnUiThread {
                try {
                    findNavController().apply {
                        if (currentDestination?.id != fragmentId) {
                            if (!isFragmentRemovedFromBackStack(fragmentId)) {
                                popBackStack(fragmentId, false)
                            } else {
                                try {
                                    if (removeCurrent)
                                        popBackStack()
                                    navigate(fragmentId, bundle, navOptions)

                                } catch (e: Exception) {
                                    if (removeCurrent)
                                        popBackStack()
                                    navigate(fragmentId, bundle, navOptions)
                                }
                            }
                        }
                    }
                } catch (e: IllegalStateException) {
                    Log.i(TAG, "FragmentNavigationError: ", e)
                } catch (e: java.lang.Exception) {
                    Log.i(TAG, "FragmentNavigationError 2: ", e)
                } catch (e: Exception) {
                    Log.i(TAG, "FragmentNavigationError 3: ", e)
                }
            }
        }
    }

    private fun NavController.isFragmentRemovedFromBackStack(destinationId: Int) = try {
        getBackStackEntry(destinationId)
        false
    } catch (e: Exception) {
        true
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureBackPress {
            goBackPressed()
        }

    }

    private fun configureBackPress(backPressed: () -> Unit) {
        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressed()
            }
        }
        try {
            activity?.onBackPressedDispatcher?.addCallback(
                viewLifecycleOwner, backPressedCallback
            )
        } catch (_: IllegalStateException) {
        } catch (_: java.lang.Exception) {
        } catch (_: Exception) {
        }
    }

    open fun goBackPressed() {
        activity?.runOnUiThread {
            view?.let {
                try {
                    findNavController().popBackStack()
                } catch (_: IllegalStateException) {
                } catch (_: java.lang.Exception) {
                } catch (_: Exception) {
                }
            }
        }
    }

    open fun isLocationPermissionGranted(): Boolean {
        context?.let {
            return ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    /**
     * If you want to use in Activity replace
     * @LifecycleOwner with
     * @AppCompatActivity
     */
    fun <T> LifecycleOwner.collectLatestLifecycleFlow(
        flow: Flow<T>,
        collect: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }

    /**
     * If you want to use in Activity replace
     * @LifecycleOwner with
     * @AppCompatActivity
     */
    fun <T> /*AppCompatActivity*/LifecycleOwner.collectLifecycleFlow(
        flow: Flow<T>,
        collect: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(collect)
            }
        }
    }

}