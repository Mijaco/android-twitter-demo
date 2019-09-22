package com.davidchura.tiendavirtual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {

    private var handler: Handler? = null
    //? indica que la variable puede asumir valores nulos

    val TIEMPO_ESPERA:Long = 3000 //Para definir el tiempo en milisegundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Para que desaparezca la barra de estado
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        handler = Handler();
        handler!!.postDelayed(Runnable {
            startActivity(Intent(this,MainActivity::class.java))
            finish()//Para que se cierre este activity
        },TIEMPO_ESPERA)
    }
}
