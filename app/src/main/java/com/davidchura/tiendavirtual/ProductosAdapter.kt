package com.davidchura.tiendavirtual

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*

class ProductosAdapter(val activity: FragmentActivity,
                       arrayList: ArrayList<HashMap<String,String>>
                        ):BaseAdapter() {

    private var arrayList = ArrayList<HashMap<String,String>>()
    init {
        this.arrayList = arrayList
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View
        var inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.item_producto,null)

        val mtvIdproducto = view.findViewById<TextView>(R.id.tvIdproducto)
        val mtvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val mtvDetalle = view.findViewById<TextView>(R.id.tvDetalle)
        val mtvPrecio = view.findViewById<TextView>(R.id.tvPrecio)
        val mivFoto = view.findViewById<ImageView>(R.id.ivFoto)

        var map = arrayList.get(position)
        mtvIdproducto.text = map.get("idproducto").toString()
        mtvNombre.text = map.get("nombre").toString()
        mtvDetalle.text = map.get("detalle").toString()
        mtvPrecio.text = map.get("precio").toString()
        val ruta = "https://pix.pe/servicioandroid/" + map.get("imagengrande").toString()
        Picasso.get().load(ruta).into(mivFoto);


        return view
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size //Para saber cuantas veces va a iterar
    }

}