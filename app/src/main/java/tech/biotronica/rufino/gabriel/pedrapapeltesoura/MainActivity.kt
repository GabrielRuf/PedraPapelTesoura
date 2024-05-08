package tech.biotronica.rufino.gabriel.pedrapapeltesoura

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tech.biotronica.rufino.gabriel.pedrapapeltesoura.Constantes.NUMERBER_OF_PLAYERS
import tech.biotronica.rufino.gabriel.pedrapapeltesoura.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var parl: ActivityResultLauncher<Intent>
    private val amb: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var current_players_numbers: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.playBt.setOnClickListener {
            startActivity(Intent(this,GameActivity::class.java))
        }

        amb.SpinnerMode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                current_players_numbers = parent?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        amb.playBt.setOnClickListener {
            Intent(this, GameActivity::class.java).also{
                it.putExtra(NUMERBER_OF_PLAYERS, current_players_numbers)
                parl.launch(it)
            }
        }

        parl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            object: ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult) {
                    if(result.resultCode == RESULT_OK){
                        result.data?.getStringExtra(Constantes.WINNER)?.let{
                            Toast.makeText(applicationContext,it,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
    }


}