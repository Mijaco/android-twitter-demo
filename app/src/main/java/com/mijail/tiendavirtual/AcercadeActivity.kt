package com.mijail.tiendavirtual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_acercade.*

class AcercadeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
           R.id.rbMision -> mostrarMision()
            R.id.rbVision -> mostrarVision()
            R.id.rbHistoria -> mostrarHistoria()
        }
    }

    private fun mostrarHistoria() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.llContenedor,HistoriaFragment())
            .commit()
    }

    private fun mostrarVision() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.llContenedor,MisionFragment())
            .commit()}

    private fun mostrarMision() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.llContenedor,MisionFragment())
            .commit()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acercade)
        rbMision.setOnClickListener(this)
        rbVision.setOnClickListener(this)
        rbHistoria.setOnClickListener(this)
    }
}
