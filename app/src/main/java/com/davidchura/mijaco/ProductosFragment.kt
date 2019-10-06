package com.davidchura.mijaco


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.davidchura.tiendavirtual.R
import kotlinx.android.synthetic.main.fragment_categorias.*
import kotlinx.android.synthetic.main.fragment_categorias.lvCategorias
import kotlinx.android.synthetic.main.fragment_productos.*
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 */
class ProductosFragment : Fragment(), AdapterView.OnItemClickListener {


    var arrayList = ArrayList<HashMap<String,String>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leerCategorias()
    }

    private fun leerCategorias() {
        val queue = Volley.newRequestQueue(getActivity())
        //getActivity() hace referencia al activity que contiene al fragment
        val url = Total.rutaServicio + "serviciocategorias.php"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                Log.d("CATEGORIAS",response)
                llenarLista(response)
            },
            Response.ErrorListener {

                Log.d("CATEGORIAS",it.message.toString())
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
    private fun llenarLista(response: String) {
        var jsonArray = JSONArray(response)
        //Para que el texto lo interprete como un objeto JSON


        //Asi se llena el arreglo simple
        for(i in 0 until jsonArray.length()){
            //getJSONObject, permite obtener cada fila
            var map = HashMap<String,String>()
            map.put("idcategoria",jsonArray.getJSONObject(i).getString("idcategoria"))
            map.put("nombre",jsonArray.getJSONObject(i).getString("nombre"))
            map.put("descripcion",jsonArray.getJSONObject(i).getString("descripcion"))
            arrayList.add(map)
        }
        val origen = arrayOf<String>("idcategoria","nombre","descripcion")
        val destino: IntArray = intArrayOf(
            R.id.tvIdcategoria,
            R.id.tvNombre,
            R.id.tvDescripcion
        )
        val listAdapter = SimpleAdapter(getActivity(),arrayList,
            R.layout.item_categoria,
            origen,destino);
        lvCategorias.adapter = listAdapter

        //Para programar en la lista
        lvCategorias.setOnItemClickListener(this)
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("SELECCIONADO:", position.toString() )
        //La variable position, permite obtener la posicion del elemento seleccionado
        var map = arrayList.get(position);
        var idcategoria = map.get("idcategoria");
        var nombre = map.get("nombre");
        var descripcion = map.get("descripcion");


        Toast.makeText(getActivity(),nombre,Toast.LENGTH_SHORT).show();


        var bundle = Bundle().apply {
            putString("idcategoria",idcategoria)
            putString("nombre",nombre)
            putString("descripcion",descripcion)
        }
        //Se almacen en el objeto bundle
        //los datos que viajarán al fragment destino

        val productosCategoriaFragment = ProductosCategoriaFragment();

        productosCategoriaFragment.arguments = bundle;
        //Asi se indica que al fragment viajaran los valores
        //de bundle


        //La !! indica que el activity nunca va a ser nulo
        //supportFragmentManager se invoca desde el activity
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment,productosCategoriaFragment)
            .commit()
    }
}
