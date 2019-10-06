package com.davidchura.mijaco


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.davidchura.tiendavirtual.R
import kotlinx.android.synthetic.main.fragment_categorias.*
import kotlinx.android.synthetic.main.fragment_productos_categoria.*
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 */
class ProductosCategoriaFragment : Fragment() {
    var arrayList = ArrayList<HashMap<String,String>>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_productos_categoria, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Se leen los valores que recibe el fragment
        val idcategoria = arguments!!.getString("idcategoria")
        val nombre = arguments!!.getString("nombre")
        val descripcion = arguments!!.getString("descripcion")
         //activity?.setTitle(descripcion) //Para modificar el valor de la
        //barra de titulo del activity (que contiene al fragment)
        tvNombreCategoria.text = nombre
        leerProductos(idcategoria)
    }
    private fun leerProductos(idcategoria: String?) {
        val queue = Volley.newRequestQueue(getActivity())
        //getActivity() hace referencia al activity que contiene al fragment
        val url = Total.rutaServicio + "servicioproductos.php"
// Request a string response from the provided URL.
        val stringRequest = object:StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                Log.d("PRODUCTOS",response)
                llenarLista(response)
            },
            Response.ErrorListener {
                Log.d("PRODUCTOS",it.message.toString())
            })
        {
            override fun getParams(): MutableMap<String, String> {
                var params = HashMap<String,String>();
                idcategoria?.let { params.put("caty", it) }
                return params
            }
        }
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
            map.put("idproducto",jsonArray.getJSONObject(i).getString("idproducto"))
            map.put("nombre",jsonArray.getJSONObject(i).getString("nombre"))
            map.put("detalle",jsonArray.getJSONObject(i).getString("detalle"))
            map.put("precio",jsonArray.getJSONObject(i).getString("precio"))
            map.put("imagengrande",jsonArray.getJSONObject(i).getString("imagengrande"))
            arrayList.add(map)
        }
        /*
        val origen = arrayOf<String>("idproducto","nombre","detalle","precio")
        val destino: IntArray = intArrayOf(R.id.tvIdproducto,R.id.tvNombre,R.id.tvDetalle,
            R.id.tvPrecio)
        val listAdapter = SimpleAdapter(getActivity(),arrayList,R.layout.item_producto,
            origen,destino);
        lvProductos.adapter = listAdapter
         */
        var productosAdapter = getActivity()?.let {
            ProductosAdapter(
                it,
                arrayList
            )
        };
        lvProductos.adapter = productosAdapter

    }
}
