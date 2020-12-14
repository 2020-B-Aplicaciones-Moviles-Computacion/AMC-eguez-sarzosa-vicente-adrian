package com.example.movilescomputacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    var posicionItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)


        val adaptador: ArrayAdapter<Int> = ArrayAdapter(
                this, // Contexto
                android.R.layout.simple_list_item_1, // Layout (xml visual) existe en Android
                BBaseDatosMemoria.arregloEnteros // Arreglo
        )

        val listView = findViewById<ListView>(R.id.lv_entrenador)

        listView.adapter = adaptador

        /*
        listView
                .setOnItemLongClickListener { parent, view, position, id ->
                    Log.i("intent-explicito", "HOLA ${position} ${id}")

                    val builder = AlertDialog.Builder(this)

                    builder.setTitle("Titulo")
                    // builder.setMessage("Mensaje")

                    val seleccionUsuario = booleanArrayOf(
                            true,
                            false,
                            false
                    )

                    val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)
                    builder.setMultiChoiceItems(
                            opciones,
                            seleccionUsuario,
                            { dialog, which, isChecked ->
                                Log.i("intent-explicito", "${which} ${isChecked}")
                            }

                    )


                    builder.setPositiveButton(
                            "Si",
                            DialogInterface.OnClickListener { dialog, which ->
                                Log.i("intent-explicito", "Si")
                            }
                    )

                    builder.setNegativeButton(
                            "No",
                            null
                    )

                    val dialogo = builder.create()
                    dialogo.show()

                    return@setOnItemLongClickListener true
                }

        */

        adaptador.notifyDataSetChanged()

        registerForContextMenu(listView)

        val botonAnadirLV = findViewById<Button>(R.id.btn_anadir_item_lv)

        botonAnadirLV
                .setOnClickListener {
                    anadirListView(adaptador, 1, BBaseDatosMemoria.arregloEnteros)
                }
    }

    fun anadirListView(
            adaptador: ArrayAdapter<Int>,
            item: Int,
            arreglo: ArrayList<Int>
    ) {
        arreglo.add(item)
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
    }


    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            // Editar
            R.id.mi_editar -> {
                Log.i("intent-explicito", "Editar " +
                        "${BBaseDatosMemoria.arregloEnteros[posicionItemSeleccionado]}")

                return true
            }
            // Editar
            R.id.mi_editar -> {
                Log.i("intent-explicito", "Eliminar " +
                        "${BBaseDatosMemoria.arregloEnteros[posicionItemSeleccionado]}")
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

}