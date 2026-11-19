package com.example.agrotec

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val content = findViewById<LinearLayout>(R.id.content)
        val dp = resources.displayMetrics.density
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_root)) { _, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            content.setPadding(
                (20 * dp).toInt(),
                bars.top + (16 * dp).toInt(),
                (20 * dp).toInt(),
                maxOf(bars.bottom, ime.bottom) + (24 * dp).toInt()
            )
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApPaterno = findViewById<EditText>(R.id.etApPaterno)
        val etApMaterno = findViewById<EditText>(R.id.etApMaterno)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etPass = findViewById<EditText>(R.id.etPass)
        val etConfirmPass = findViewById<EditText>(R.id.etConfirmPass)

        findViewById<TextView>(R.id.tvVolver).setOnClickListener { finish() }
        findViewById<TextView>(R.id.tvIniciaSesion).setOnClickListener { finish() }

        findViewById<Button>(R.id.btnContinuar).setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apPaterno = etApPaterno.text.toString().trim()
            val apMaterno = etApMaterno.text.toString().trim()
            val telefono = etTelefono.text.toString().filter { it.isDigit() }
            val pass = etPass.text.toString()
            val confirm = etConfirmPass.text.toString()

            when {
                nombre.isEmpty() || apPaterno.isEmpty() || apMaterno.isEmpty() ||
                        telefono.isEmpty() || pass.isEmpty() || confirm.isEmpty() ->
                    toast("Completa todos los campos")

                telefono.length != 10 ->
                    toast("El teléfono debe tener 10 dígitos")

                pass.length < 6 ->
                    toast("La contraseña debe tener al menos 6 caracteres")

                pass != confirm ->
                    toast("Las contraseñas no coinciden")

                else -> {
                    // TODO: guardar el registro (API / Firebase / base de datos)
                    toast("Cuenta creada")
                    finish() // regresa al login
                }
            }
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}