package pe.edu.crisol.libreria.retrofit
import pe.edu.crisol.libreria.model.UsuarioVo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
interface LoginApiService {

    @POST("login/register")
    fun registrarUsuario(@Body usuario: UsuarioVo) : Call<UsuarioVo>
}