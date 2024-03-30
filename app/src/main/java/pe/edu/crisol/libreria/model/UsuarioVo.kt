package pe.edu.crisol.libreria.model

data class UsuarioVo (
    val nombres: String,
    val apellidos: String,
    val correo: String,
    val contrasena: String,
    val estado: Int
)