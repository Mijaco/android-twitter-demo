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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mostrarVision() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mostrarMision() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acercade)
        rbMision.setOnClickListener(this)
        rbVision.setOnClickListener(this)
        rbHistoria.setOnClickListener(this)
    }
}
