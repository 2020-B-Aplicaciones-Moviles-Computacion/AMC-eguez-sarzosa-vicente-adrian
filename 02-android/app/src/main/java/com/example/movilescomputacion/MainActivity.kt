package com.example.movilescomputacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val CODIGO_ACTUALIZAR_DATOS = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BBaseDatosMemoria.cargaInicialDatos()

        val buttonCicloVida = findViewById<Button>(R.id.button_ir_ciclo_vida)
        buttonCicloVida.setOnClickListener {
            irActividad(ACicloVida::class.java)
        }

        val buttonListView = findViewById<Button>(R.id.btn_ir_list_view)
        buttonListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

        val buttonIntentExplicitoParametros =
                findViewById<Button>(R.id.btn_ir_intent_explicito_parametros)
        buttonIntentExplicitoParametros.setOnClickListener {

//            val intentExplicito = Intent(
//                    this,
//                    CIntentExplicitoParametros::class.java
//            )
//            intentExplicito.putExtra("nombre", "Adrian")
//            intentExplicito.putExtra("apellido", "Eguez")
//            intentExplicito.putExtra("edad", 31)
//
//            Log.i("intent-explicito", "${intentExplicito.extras}")
//
//            startActivityForResult(intentExplicito, CODIGO_ACTUALIZAR_DATOS)

            val liga = DLiga("Kanto", "Pokemon")
            val entrenador = BEntrenador(
                    "Ash",
                    "Pueblo paleta",
                    liga
            )
            val parametros = arrayListOf<Pair<String, *>>(
                    Pair("nombre", "Adrian"),
                    Pair("apellido", "Eguez"),
                    Pair("edad", 31),
                    Pair("ash", entrenador)

            )
            irActividad(
                    CIntentExplicitoParametros::class.java,
                    parametros,
                    CODIGO_ACTUALIZAR_DATOS // 102
            )

        }

    }

    fun irActividad(
            clase: Class<*>,
            parametros: ArrayList<Pair<String, *>>? = null,
            codigo: Int? = null
    ) {
        val intentExplicito = Intent(
                this,
                clase
        )
        if (parametros != null) {
            parametros.forEach {
                val nombreVariable = it.first
                val valorVariable = it.second

                var tipoDato = false

                tipoDato = it.second is String // instanceOf()
                if (tipoDato == true) {
                    intentExplicito.putExtra(nombreVariable, valorVariable as String)
                }

                tipoDato = it.second is Int // instanceOf()
                if (tipoDato == true) {
                    intentExplicito.putExtra(nombreVariable, valorVariable as Int)
                }

                tipoDato = it.second is Parcelable // instanceOf()
                if (tipoDato == true) {
                    intentExplicito.putExtra(nombreVariable, valorVariable as Parcelable)
                }

            }
        }
        if (codigo != null) {
            startActivityForResult(intentExplicito, codigo)
        } else {
            startActivity(intentExplicito)
        }


    }

    override fun onActivityResult(
            requestCode: Int,  // Codigo de petición  - Codigo: 102
            resultCode: Int,  //  Codigo de Resultado - RESULT_OK o RESULT_CANCELED
            data: Intent?  // Datos (opcionales)  Ej: nombre = Vicente y edad = 30
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("intent-exlicito", "${requestCode} ${resultCode} ${data}")
        when (requestCode) {
            CODIGO_ACTUALIZAR_DATOS -> { // 102

                if (resultCode == RESULT_OK) {  //  RESULT_OK
                    Log.i("intent-explicito", "SI actualizo los datos")

                    if (data != null) {
                        val nombre = data.getStringExtra("nombre")
                        val edad = data.getIntExtra("edad", 0)
                        Log.i("intent-explicito", "Nombre: ${nombre} Edad: ${edad}")
                    } else {
                        // AQUI ES EL OTRO BLOQUE SIN PARAMETROS DE RESPUESTA PERO OK
                    }


                } else { // RESULT_CANCELED
                    Log.i("intent-explicito", "Usuario no lleno los datos")
                }

            }
        }
    }


}
















