package com.example.testram.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testram.R
import com.example.testram.databinding.ActivityLogoBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class FirstLogoActivity : AppCompatActivity(){

    private var _binding: ActivityLogoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transition()
                .delay(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                },
                        {
                            Timber.d(it)
                        })
    }

    private fun transition(): Single<Class<MainActivity>>{
        return Single.just(MainActivity::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}