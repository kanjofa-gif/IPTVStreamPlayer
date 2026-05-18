package com.iptvstream.ui

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.iptvstream.R

class SplashActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let {
            it.hide(WindowInsetsCompat.Type.systemBars())
            it.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        setContentView(R.layout.activity_splash)

        val letterS = findViewById<View>(R.id.letterS)
        val letterH = findViewById<View>(R.id.letterH)
        val letterF = findViewById<View>(R.id.letterF)
        val eye1 = findViewById<View>(R.id.eye1)
        val eye2 = findViewById<View>(R.id.eye2)
        val textIptv = findViewById<View>(R.id.textIptv)

        letterS.translationX = -1500f
        letterH.translationX = -1500f
        letterF.translationX = 1500f
        eye1.translationY = -1500f
        eye1.alpha = 0f
        eye2.translationY = -1500f
        eye2.alpha = 0f
        textIptv.alpha = 0f
        textIptv.translationY = 200f

        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.intro_sound)
            mediaPlayer?.start()
        } catch (_: Exception) { }

        letterS.animate()
            .translationX(0f)
            .setStartDelay(100)
            .setDuration(700)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        letterH.animate()
            .translationX(0f)
            .setStartDelay(200)
            .setDuration(700)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        letterF.animate()
            .translationX(0f)
            .setStartDelay(100)
            .setDuration(700)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        Handler(Looper.getMainLooper()).postDelayed({
            eye1.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(700)
                .setInterpolator(OvershootInterpolator(1.5f))
                .start()
        }, 1100)

        Handler(Looper.getMainLooper()).postDelayed({
            eye2.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(700)
                .setInterpolator(OvershootInterpolator(1.5f))
                .start()
        }, 1250)

        Handler(Looper.getMainLooper()).postDelayed({
            textIptv.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(700)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }, 2200)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 5000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
    }
}
