package com.example.firebaseejemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class COrdenes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_ordenes)

        val arreglo = arrayListOf<BUsuarioFirebase>(
            BUsuarioFirebase("a", "a@a", null),
            BUsuarioFirebase("b", "b@b", null),
            BUsuarioFirebase("c", "c@c", null)
        )
        val spinnerRestaurantes = findViewById<Spinner>(R.id.sp_restaurantes)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arreglo
        )


        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRestaurantes.setAdapter(adaptador)

        spinnerRestaurantes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.i("firebase-firestore", "${position}, ${id}")
                Log.i("firebase-firestore", "${arreglo[position].uid}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("firebase-firestore", "No selecciono nada")
            }
        }

    }
}