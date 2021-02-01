package com.example.firebaseejemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.firebaseejemplo.dto.FirestoreRestauranteDto
import com.example.firebaseejemplo.dto.FirestoreUsuarioOrdenDto
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class COrdenes : AppCompatActivity() {

    val arregloRestaurantes = arrayListOf<FirestoreRestauranteDto>()
    var adaptadorRestaurantes: ArrayAdapter<FirestoreRestauranteDto>? = null

    val arregloTiposComida = arrayListOf<String>()

    var restauranteSeleccionado: FirestoreRestauranteDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_c_ordenes)

        if (adaptadorRestaurantes == null) {
            adaptadorRestaurantes = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item,
                    arregloRestaurantes
            )
            adaptadorRestaurantes?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarRestaurantes()
        }

        val botonAnadirTipoComida = findViewById<Button>(R.id.btn_anadir_tipo_comida)
        botonAnadirTipoComida
                .setOnClickListener {
                    agregarTipoComida()
                }

        val textViewTipoComida = findViewById<TextView>(R.id.tv_arreglo_tipo_comida)
        textViewTipoComida.setText("")

        val botonAnadirOrden = findViewById<Button>(R.id.btn_crear_orden)
        botonAnadirOrden
                .setOnClickListener {
                    crearOrden()
                }

        buscarOrdenes()

    }

    fun buscarOrdenes() {
        val db = Firebase.firestore
        val referencia = db.collection("orden")

        // Buscar por un solo campo ==
//        referencia
//                .whereEqualTo("review", 3)
//                .whereEqualTo("restaurante.nombre", "fridays")
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it) {
//                        Log.i("firebase-consultas", "${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore", "Error")
//                }

        // Buscar por dos o mas elementos campo '==' 'array-contains'
//        referencia
//                .whereEqualTo("review", 3)
//                .whereEqualTo("restaurante.nombre", "fridays")
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it) {
//                        Log.i("firebase-consultas", "${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore", "Error")
//                }


        // Buscar por dos o mas elementos campo '==' 'array-contains'
//        referencia
//                .whereEqualTo("restaurante.nombre", "fridays")
//                .whereArrayContains("tiposComida", "ecuatoriana")
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it) {
//                        Log.i("firebase-consultas", "${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore", "Error")
//                }


        // Buscar por dos o mas elementos campo '==' '>='
//        referencia
//                .whereEqualTo("restaurante.nombre", "fridays")
//                .whereGreaterThanOrEqualTo("review", 3)
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it) {
//                        Log.i("firebase-consultas", "${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore", "Error")
//                }


        // Buscar por dos o mas elementos campo '==' '>=' ordenar descentente los nombres

        // import com.google.firebase.firestore.Query

//        referencia
//                .whereEqualTo("restaurante.nombre", "fridays") // NO ES NECESARIO ORDENAR CUANDO ES == o array-contains
//                .whereGreaterThanOrEqualTo("review", 3) // SE PUEDE ORDENAR CUANDO NO ES == o array-contains
//                .orderBy("review", Query.Direction.DESCENDING) // enviar la busqueda NO IGUAL primero
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it) {
//                        Log.i("firebase-consultas", "${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore", "Error")
//                }

        // Buscar por dos o mas elementos campo '==' 'array-contains-any'

//        referencia
//                .whereEqualTo("restaurante.nombre", "fridays")
//                .whereArrayContainsAny("tiposComida", arrayListOf("española", "peruana")) // SOLO EN ARREGLOS
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it) {
//                        Log.i("firebase-consultas", "${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore", "Error")
//                }

        // Buscar por dos o mas elementos campo '==' 'array-contains-any'

        referencia
                .whereIn("restaurante.nombre", arrayListOf("fridays", "Papas de la Maria"))
                .whereGreaterThanOrEqualTo("review", 1) // SOLO EN ARREGLOS
                .get()
                .addOnSuccessListener {
                    for (orden in it) {
                        Log.i("firebase-consultas", "${orden.id} ${orden.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "Error")
                }

    }

    fun cargarRestaurantes() {
        val spinnerRestaurantes = findViewById<Spinner>(R.id.sp_restaurantes)

        spinnerRestaurantes.setAdapter(adaptadorRestaurantes)

        spinnerRestaurantes
                .onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                restauranteSeleccionado = arregloRestaurantes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("firebase-firestore", "No selecciono nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("restaurante")
        Log.i("firebase-firestore", "Antes de consultar")
        referencia.get()
                .addOnSuccessListener {
                    Log.i("firebase-firestore", "Ya consulto ${it}")
                    for (document in it) {
                        Log.i("firebase-firestore", "${document.id} => ${document.data}")
                        val restaurante = document.toObject(FirestoreRestauranteDto::class.java)
                        restaurante.uid = document.id
                        arregloRestaurantes.add(restaurante)
                        adaptadorRestaurantes?.notifyDataSetChanged()
                    }
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "Error ${it}")
                }

    }

    fun agregarTipoComida() {
        val etTipoComida = findViewById<EditText>(R.id.et_tipo_comida)
        val texto = etTipoComida.text.toString()
        arregloTiposComida.add(texto)
        val textViewTipoComida = findViewById<TextView>(R.id.tv_arreglo_tipo_comida)
        val textoAnterior = textViewTipoComida.text.toString()
        textViewTipoComida.setText("${textoAnterior}, ${texto}")
        etTipoComida.setText("")

    }

    fun crearOrden() {
        if (restauranteSeleccionado != null && FirebaseAuth.getInstance().currentUser != null) {
            val restaurante = restauranteSeleccionado
            val instanciaAuth = FirebaseAuth.getInstance()
            val usuario = FirestoreUsuarioOrdenDto(instanciaAuth.currentUser!!.uid)
            val editTextReview = findViewById<EditText>(R.id.et_review)

            val nuevaOrden = hashMapOf<String, Any?>(
                    "restaurante" to restaurante,
                    "usuario" to usuario,
                    "review" to editTextReview.text.toString().toInt(),
                    "tiposComida" to arregloTiposComida,
                    "fechaCreacion" to Timestamp(Date())
            )
            val db = Firebase.firestore
            val referencia = db.collection("orden")
                    .document()
                    .set(nuevaOrden)
            referencia
                    .addOnSuccessListener {}
                    .addOnFailureListener {}
        }

    }

}