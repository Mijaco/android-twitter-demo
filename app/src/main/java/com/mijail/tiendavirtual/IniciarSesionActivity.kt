package com.mijail.tiendavirtual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class IniciarSesionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_inicio_sesion, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.nav_informacion -> {
                mostrarInformacion()
                true
            }
            R.id.nav_acerca_de-> {
                mostrarAcercade()
                true
            }
            R.id.nav_acerca_de-> {
                mostrartTerminos()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrartTerminos() {
        startActivity(Intent(this,TerminosActivity::class.java))
    }

    private fun mostrarAcercade() {
        startActivity(Intent(this,AcercadeActivity::class.java))
    }

    private fun mostrarInformacion() {
        startActivity(Intent(this,InformacionActivity::class.java))
    }
}
