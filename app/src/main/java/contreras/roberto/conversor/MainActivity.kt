package contreras.roberto.conversor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var op : Int = 0 //0 longitud, 1 peso, 2 temp, 3 vol
    var n : Double = 0.0
    var r : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cambiarOperacion(0)

        findViewById<TextView>(R.id.resultado).text = ""

        findViewById<Button>(R.id.convertirBtn).setOnClickListener {
            n = findViewById<EditText>(R.id.input).text.toString().toDouble()

            r = when (op) {
                0 -> n/1.609
                1 -> n*2.205
                2 -> (n*9/5)+32
                3 -> n*33.814
                else -> 0.0
            }

            val s: String = when(op) {
                0 -> "millas"
                1 -> "libras"
                2 -> "°F"
                3 -> "onzas"
                else -> ""
            }

            findViewById<TextView>(R.id.resultado).text = "%.4f %s.".format(r,s)
        }

        findViewById<Button>(R.id.longitudBtn).setOnClickListener    {cambiarOperacion(0)}
        findViewById<Button>(R.id.pesoBtn).setOnClickListener        {cambiarOperacion(1)}
        findViewById<Button>(R.id.temperaturaBtn).setOnClickListener {cambiarOperacion(2)}
        findViewById<Button>(R.id.volumenBtn).setOnClickListener     {cambiarOperacion(3)}
    }

    fun cambiarOperacion(op: Int) {
        val texto: TextView = findViewById(R.id.operacion)

        when (op) {
            0 -> texto.text = "Convertir km a millas."
            1 -> texto.text = "Convertir kg a libras."
            2 -> texto.text = "Convertir °C a °F."
            3 -> texto.text = "Convertir litros a onzas."
        }

        this.op = op
    }
}