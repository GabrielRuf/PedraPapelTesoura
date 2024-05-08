package tech.biotronica.rufino.gabriel.pedrapapeltesoura

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import tech.biotronica.rufino.gabriel.pedrapapeltesoura.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(){

    private val agb: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    private lateinit var current_players_numbers: String
    private lateinit var winner: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(agb.root)

        intent.getStringExtra(Constantes.NUMERBER_OF_PLAYERS)?.let{
            current_players_numbers = it
        }

        agb.papelBt.setOnClickListener {
            onClickedButton(Choices.PAPEL)
        }
        agb.pedraBt.setOnClickListener {
            onClickedButton(Choices.PEDRA)
        }
        agb.tesouraBt.setOnClickListener {
            onClickedButton(Choices.TESOURA)
        }

    }

    private fun onClickedButton(choice: Choices){
        if(current_players_numbers.toInt() == 2){
            val botchoice = getRandomChoice()
            if (botchoice.equals(choice)){
                winner = "EMPATE"
            }else{
            val result = when(choice){
                Choices.PEDRA -> if (botchoice.equals(Choices.PAPEL)){
                    winner = "BOT 1"
                }else
                    winner = "PLAYER"

                Choices.PAPEL -> if (botchoice.equals(Choices.TESOURA)){
                    winner = "BOT 1"
                }else
                    winner = "PLAYER"

                Choices.TESOURA -> if (botchoice.equals(Choices.PEDRA)){
                    winner = "BOT 1"
                }else
                    winner = "PLAYER"
            }
            }
        }else if (current_players_numbers.toInt() == 3){
            val bot1choice = getRandomChoice()
            val bot2choice = getRandomChoice()

            if (bot1choice.equals(choice) and bot2choice.equals(choice)){
                winner = "EMPATE"
            }else{
                if (bot1choice.equals(bot2choice)){
                    val result = when(choice){
                        Choices.PEDRA -> if (bot1choice.equals(Choices.PAPEL)){
                            winner = "EMPATE"
                        }else
                            winner = "PLAYER"

                        Choices.PAPEL -> if (bot1choice.equals(Choices.TESOURA)){
                            winner = "EMPATE"
                        }else
                            winner = "PLAYER"

                        Choices.TESOURA -> if (bot1choice.equals(Choices.PEDRA)){
                            winner = "EMPATE"
                        }else
                            winner = "PLAYER"
                    }
                }else{
                    if (choice.equals(bot2choice)){
                        val result = when(bot1choice){
                            Choices.PEDRA -> if (choice.equals(Choices.PAPEL)){
                                winner = "EMPATE"
                            }else
                                winner = "BOT 1"

                            Choices.PAPEL -> if (choice.equals(Choices.TESOURA)){
                                winner = "EMPATE"
                            }else
                                winner = "BOT 1"

                            Choices.TESOURA -> if (choice.equals(Choices.PEDRA)){
                                winner = "EMPATE"
                            }else
                                winner = "BOT 1"
                        }
                    }else{
                        if (choice.equals(bot1choice)){
                            val result = when(bot2choice){
                                Choices.PEDRA -> if (choice.equals(Choices.PAPEL)){
                                    winner = "EMPATE"
                                }else
                                    winner = "BOT 2"

                                Choices.PAPEL -> if (choice.equals(Choices.TESOURA)){
                                    winner = "EMPATE"
                                }else
                                    winner = "BOT 2"

                                Choices.TESOURA -> if (choice.equals(Choices.PEDRA)){
                                    winner = "EMPATE"
                                }else
                                    winner = "BOT 2"
                            }
                        }else{
                            winner = "EMPATE"
                        }
                    }

                }

            }
        }
        val resultIntent = Intent()
        resultIntent.putExtra(Constantes.WINNER,winner)
        setResult(RESULT_OK,resultIntent)
        finish()
    }

    fun getRandomChoice(): Choices{
        val choices = Choices.entries.toTypedArray()
        return choices[choices.indices.random()]
    }

}