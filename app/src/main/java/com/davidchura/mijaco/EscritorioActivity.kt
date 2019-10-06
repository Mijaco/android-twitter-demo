package com.davidchura.mijaco

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.davidchura.mijaco.R

class EscritorioActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escritorio)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_tools,
                R.id.nav_share,
                R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.escritorio, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_categorias -> mostrarCategorias()
            R.id.nav_productos -> mostrarProductos()
            R.id.nav_cerrar_sesion -> cerrarSesion()
            R.id.nav_salir -> salir()

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    private fun salir() {
        finish()
    }

    private fun cerrarSesion() {
        val builder =  AlertDialog.Builder(this, R.style.DialogTheme)
        builder.setTitle("Cierre de sesión")
        builder.setMessage("¿Está seguro que desea cerrar la sesión?")

        builder.setPositiveButton(android.R.string.yes){
            dialog, which -> cierreSesion()
        }
        builder.setNegativeButton(android.R.string.no){
            dialog, which ->
                Toast.makeText(this,
                    "Se cancelo el cierre de sesión",
                    Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
    private fun cierreSesion() {
        var pref: SharedPreferences = getSharedPreferences("datosSesion",
            Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.clear()
        editor.apply()
        startActivity(Intent(this, IniciarSesionActivity::class.java))
        finish()
    }

    private fun mostrarProductos() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.nav_host_fragment,
                ProductosFragment()
            )
            .commit()
    }

    private fun mostrarCategorias() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.nav_host_fragment,
                CategoriasFragment()
            )
            .commit()
    }

}
