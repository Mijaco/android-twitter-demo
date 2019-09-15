package com.mijail.tiendavirtual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnCrearCuenta -> mostrarCrearCuenta()
            R.id.tvIniciarSesion -> mostrarIniciarSesion()


        }
    }

    private fun mostrarIniciarSesion() {
        startActivity(Intent(this,IniciarSesionActivity::class.java))
    }

    private fun mostrarCrearCuenta() {
        startActivity(Intent(this,CrearCuentaActivity::class.java))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementando el evento click
        btnCrearCuenta.setOnClickListener(this)
        tvIniciarSesion.setOnClickListener(this)


    }
}
