package com.davidchura.mijaco

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.davidchura.mijaco.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*

class IniciarSesionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnIniciarSesion -> iniciarSesion();
        }
    }

    private fun iniciarSesion() {
        //startActivity(Intent(this,EscritorioActivity::class.java))
        val usuario = etUsuario.text
        val clave = etClave.text
        val url = Total.rutaServicio + "iniciarsesion.php"


        val queue = Volley.newRequestQueue(this)
        //getActivity() hace referencia al activity que contiene al fragment

        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                Log.d("RESPUESTA",response)
                evaluarInicioSesion(response)
            },
            Response.ErrorListener {
                Log.d("RESPUESTA",it.message.toString())
            })
        {
            override fun getParams(): MutableMap<String, String> {
                var params = HashMap<String,String>();
                params.put("usuario",usuario.toString())
                params.put("clave",clave.toString())
                return params
            }
        }
// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun evaluarInicioSesion(response: String?) {
        if(response == "-1"){
            Toast.makeText(this,"El usuario no existe",
                Toast.LENGTH_SHORT).show()
        }
        else if(response == "-2"){
            Toast.makeText(this,"La contraseña es incorrecta",
                Toast.LENGTH_SHORT).show()
        }
        else{
            verificarGuardarSesion(response)
            Toast.makeText(this,"Bienvenido",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EscritorioActivity::class.java))
            finish()
        }
    }

    private fun verificarGuardarSesion(response: String?) {
        if(chkSesion.isChecked){
            //SharedPreferences guarda datos localmente (en el dispositivo)
            var pref: SharedPreferences = getSharedPreferences("datosSesion",
                Context.MODE_PRIVATE)
            var editor = pref.edit()
            editor.putString("datosUsuario",response)
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)
        btnIniciarSesion.setOnClickListener(this)

        var ruta = "https://www.lifeder.com/wp-content/uploads/2017/12/frases-de-Wonder-Woman.jpg"

        Picasso.get().load(ruta).into(ivFoto);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_inicio_sesion, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Maneja la selección de items con el evento clic
        return when (item.itemId) {
            R.id.nav_informacion -> {
                mostrarInformacion()
                true
            }
            R.id.nav_acerca_de -> {
                mostrarAcercade()
                true
            }
            R.id.nav_terminos_condiciones -> {
                mostrarTerminos()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarTerminos() {
        startActivity(Intent(this, TerminosActivity::class.java))
    }

    private fun mostrarAcercade() {
        startActivity(Intent(this, AcercadeActivity::class.java))
    }

    private fun mostrarInformacion() {
        startActivity(Intent(this, InformacionActivity::class.java))
    }
}
