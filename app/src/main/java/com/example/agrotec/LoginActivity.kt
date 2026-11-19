package com.example.agrotec

import android.content.Intent
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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val content = findViewById<LinearLayout>(R.id.content)
        val dp = resources.displayMetrics.density
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_root)) { _, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            content.setPadding(
                (24 * dp).toInt(),
                bars.top + (32 * dp).toInt(),
                (24 * dp).toInt(),
                bars.bottom + (24 * dp).toInt()
            )
            insets
        }

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val password = etPassword.text.toString()

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: validar credenciales
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        findViewById<TextView>(R.id.tvOlvidaste).setOnClickListener { }
        findViewById<TextView>(R.id.tvCrearCuenta).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}