package com.example.firebaseejemplo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firebaseejemplo.dto.FirestoreUsuarioDto
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    val CODIGO_INICIO_SESION = 102
    val textoNoLogeado = "Dale clic al botón ingresar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonIngresar = findViewById<Button>(R.id.btn_ingresar)
        botonIngresar
                .setOnClickListener {
                    solicitarIngresarAlAplicativo()
                }
        val botonSalir = findViewById<Button>(R.id.btn_salir)
        botonSalir
                .setOnClickListener {
                    solicitarSalirDelAplicativo()
                }

        val texto = findViewById<TextView>(R.id.textView)

        val instanciaAuth = FirebaseAuth.getInstance()
        if (instanciaAuth.currentUser != null) {
            texto.text = "Bienvenido ${instanciaAuth.currentUser?.email}"
            setearUsuarioFirebase()
            mostrarBotonesOcultos()
        } else {
            texto.text = textoNoLogeado
        }

        val botonFirestore = findViewById<Button>(R.id.btn_firestore)

        botonFirestore
                .setOnClickListener {
                    irActividad(
                            BFirestore::class.java
                    )
                }

    }

    fun solicitarIngresarAlAplicativo() {
        val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.logo)
                        .setTosAndPrivacyPolicyUrls(
                                "https://example.com/terms.html",
                                "https://example.com/privacy.html"
                        )
                        .build(),
                CODIGO_INICIO_SESION
        )
    }

    fun solicitarSalirDelAplicativo() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {

                    val texto = findViewById<TextView>(R.id.textView)
                    texto.text = textoNoLogeado

                    BAuthUsuario.usuario = null
                    mostrarBotonesOcultos()

                    Log.i("firebase-login", "Salio del app")
                }
    }

    override fun onActivityResult(
            requestCode: Int, resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CODIGO_INICIO_SESION -> {
                if (resultCode == Activity.RESULT_OK) {
                    val usuario = IdpResponse.fromResultIntent(data)
                    if (usuario?.isNewUser == true) {
                        // Logica para crear el usuario en nuestra coleccion

                        if (usuario.email != null) {
                            val db = Firebase.firestore
                            val rolesUsuario = arrayListOf("usuario")
                            val nuevoUsuario = hashMapOf<String, Any>(
                                    "roles" to rolesUsuario
                            )
                            val identificadorUsuario = usuario.email.toString()

                            db.collection("usuario")
                                    .document(identificadorUsuario)
                                    .set(nuevoUsuario)
                                    .addOnSuccessListener {

                                        setearUsuarioFirebase()
                                        mostrarBotonesOcultos()

                                        Log.i("firebase-firestore", "Se creo")
                                    }
                                    .addOnFailureListener {
                                        Log.i("firebase-firestore", "Fallo")
                                    }

                        }

                    } else {
                        val texto = findViewById<TextView>(R.id.textView)
                        texto.text = "Bienvenido ${usuario?.email}"
                        setearUsuarioFirebase()
                        mostrarBotonesOcultos()
                    }
                } else {
                    Log.i("firebase-login", "El usuario cancelo")
                }
            }
        }
    }

    fun setearUsuarioFirebase() {
        val instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser

        if (usuarioLocal != null) {
            if (usuarioLocal.email != null) {
                val usuarioFirebase = BUsuarioFirebase(
                        usuarioLocal.uid,
                        usuarioLocal.email!!,
                        null
                )
                BAuthUsuario.usuario = usuarioFirebase
                cargarRolesUsuario(usuarioFirebase.email)
            }
        }
    }

    fun cargarRolesUsuario(uid: String) {
        val db = Firebase.firestore

        val referencia = db
                .collection("usuario")
                .document(uid)

        referencia
                .get()
                .addOnSuccessListener {
                    Log.i("firebase-firestore", "Datos ${it.data}")
                    val firestoreUsuario = it.toObject(FirestoreUsuarioDto::class.java)
                    BAuthUsuario.usuario?.roles = firestoreUsuario?.roles
                    mostrarRolesEnPantalla()
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "Fallo cargar usuario")
                }

    }

    fun mostrarRolesEnPantalla() {
        var cadenaTextoRoles = ""
        BAuthUsuario.usuario?.roles?.forEach {
            cadenaTextoRoles = cadenaTextoRoles + " " + it
        }
        val textoRoles = findViewById<TextView>(R.id.tv_roles)
        textoRoles.text = cadenaTextoRoles
    }

    fun mostrarBotonesOcultos() {
        val botonEscondidoFirestore = findViewById<Button>(R.id.btn_firestore)
        if (BAuthUsuario.usuario != null) {
            botonEscondidoFirestore.visibility = View.VISIBLE
        } else {
            botonEscondidoFirestore.visibility = View.INVISIBLE
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

}