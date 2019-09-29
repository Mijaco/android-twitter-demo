package com.davidchura.tiendavirtual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*

class IniciarSesionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnIniciarSesion -> iniciarSesion();
        }
    }

    private fun iniciarSesion() {
        startActivity(Intent(this,EscritorioActivity::class.java))
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
        startActivity(Intent(this,TerminosActivity::class.java))
    }

    private fun mostrarAcercade() {
        startActivity(Intent(this,AcercadeActivity::class.java))
    }

    private fun mostrarInformacion() {
        startActivity(Intent(this,InformacionActivity::class.java))
    }
}
