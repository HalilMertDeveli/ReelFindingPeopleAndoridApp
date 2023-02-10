import android.content.Intent
import android.view.View
import com.example.findingpeoplemyvalue.GoogleMap
import com.example.findingpeoplemyvalue.MainActivity
import com.example.findingpeoplemyvalue.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.currentCoroutineContext

class ButtonOperation{
    private lateinit var authInstance :FirebaseAuth
    val userEmail = R.id.userEmail.toString().trim()
    val userPassword = R.id.userPassword.toString().trim()
    fun singUp(view: View){
        authInstance= Firebase.auth
        if (userEmail != "" && userPassword != ""){
            authInstance.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener { task->
                if (task.isSuccessful){

                    val intent = Intent(view.context,GoogleMap::class.java)


                }
            }
        }
    }


}