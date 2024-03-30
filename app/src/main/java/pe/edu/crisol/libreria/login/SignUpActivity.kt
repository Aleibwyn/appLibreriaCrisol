package pe.edu.crisol.libreria.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.crisol.libreria.databinding.ActivitySignUpBinding
import android.widget.Toast
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.model.UsuarioVo
import pe.edu.crisol.libreria.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class SignUpActivity : AppCompatActivity(), Callback<UsuarioVo>, View.OnClickListener {
    private lateinit var binding: ActivitySignUpBinding
    private val apiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegistrar.setOnClickListener(this)
    }

    override fun onResponse(call: Call<UsuarioVo>, response: Response<UsuarioVo>) {
        if (response.isSuccessful) {
            // Manejar una respuesta exitosa aquí
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        } else {
            // Manejar una respuesta con error aquí
            Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailure(call: Call<UsuarioVo>, t: Throwable) {
        Toast.makeText(this, "Erro de conexion", Toast.LENGTH_SHORT).show()

    }

    override fun onClick(v: View?) {
        // Lógica para manejar el clic en algún elemento de la interfaz de usuario
        when (v?.id) {
            R.id.btnRegistrar -> registrarUsuario()
        }
    }

    // Registro de usuario
    private fun registrarUsuario() {
        if (validarCampos()) {

            val nombre = binding.tilNombre.editText?.text.toString()
            val apellido = binding.tilApellido.editText?.text.toString()
            val correo = binding.tilCorreo.editText?.text.toString()
            val contrasena = binding.tilContrasena.editText?.text.toString()
            val estado = 1 // Aquí puedes establecer el estado según sea necesario

            val usuario = UsuarioVo(nombre, apellido, correo, contrasena, estado)

            // Llama al método para registrar el usuario utilizando RetrofitClient
            apiService.registrarUsuario(usuario).enqueue(this)
        }
    }

    // Validación de correo
    private fun validarCorreo(correo: String): Boolean {
        val patron = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$".toRegex()
        return patron.matches(correo)
    }
    // Validación de campos
    private fun validarCampos(): Boolean {
        val nombre = binding.tilNombre.editText?.text.toString()
        val apellido = binding.tilApellido.editText?.text.toString()
        val email = binding.tilCorreo.editText?.text.toString()
        val password = binding.tilContrasena.editText?.text.toString()

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }
        // Validar formato de correo electronico
        if (!validarCorreo(email)) {
            Toast.makeText(this, "Por favor ingrese un correo válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


}