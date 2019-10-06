package com.davidchura.mijaco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.davidchura.tiendavirtual.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnCrearCuenta -> mostrarCrearCuenta()
            R.id.tvIniciarSesion -> mostrarIniciarSesion()
        }

    }

    private fun mostrarIniciarSesion() {
        startActivity(Intent(this, IniciarSesionActivity::class.java))
    }

    private fun mostrarCrearCuenta() {
        startActivity(Intent(this, CrearCuentaActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementando el evento click
        btnCrearCuenta.setOnClickListener(this)
        tvIniciarSesion.setOnClickListener(this)


        val queue = Volley.newRequestQueue(this)
        val url = "https://api.ipify.org"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

               Log.d("IP",response)
               tvIP.text = response
               title = response
            },
            Response.ErrorListener {

                Log.d("IP",it.message.toString())
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)


    }
}
