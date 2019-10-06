package com.davidchura.mijaco


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.davidchura.tiendavirtual.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_categorias.*
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 */
class CategoriasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categorias, container, false)
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
        var nombreCategoria: Array<String> = Array(jsonArray.length()){""}

        //Asi se llena el arreglo simple
        for(i in 0 until jsonArray.length()){
            //getJSONObject, permite obtener cada fila
            nombreCategoria[i] = jsonArray.getJSONObject(i).getString("nombre")
        }
        var listAdapter = getActivity()?.let {
            ArrayAdapter<String>(
                it,android.R.layout.simple_list_item_1,nombreCategoria
            )
        }
        lvCategorias.adapter = listAdapter
    }

}
