package com.example.englishapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (!isNetworkAvailable()) {
            val intent = Intent(this, NoInternetActivity::class.java)
            startActivity(intent)
            return
        }


        if (OnboardingManager.isOnboardingCompleted(this)) {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {

            val onboardingStep = OnboardingManager.getOnboardingStep(this)
            val intent = when (onboardingStep) {
                1 -> Intent(this, Onboarding1Activity::class.java)
                2 -> Intent(this, Onboarding2Activity::class.java)
                3 -> Intent(this, Onboarding3Activity::class.java)
                else -> Intent(this, Onboarding1Activity::class.java)
            }
            startActivity(intent)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        return network != null
    }
}

