package pe.edu.crisol.libreria.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import pe.edu.crisol.libreria.databinding.ActivityLoginBinding
import pe.edu.crisol.libreria.menu.MainActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btLogin.setOnClickListener {
            val email = binding.htEmail.text.toString()
            val password = binding.htPassword.text.toString()
            isValidCredentials(email, password)
        }
    }

    private fun isValidCredentials(email: String, password: String) {
        val validEmail = "Rox"
        val validPassword = "Nga2814"

        if (email == validEmail && password == validPassword) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Credenciales incorrectas. Por favor, inténtalo de nuevo.", Toast.LENGTH_SHORT).show()
        }
    }
}