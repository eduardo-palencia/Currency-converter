package mx.tec.divisas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.w3c.dom.Text
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {

    var currency1 = 0.0
    var currency2 = 0.0
    var resultado = 0.0

    lateinit var divisa_one: Spinner
    lateinit var divisa_two: Spinner
    lateinit var result: TextView
    lateinit var change: TextView
    lateinit var input: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        divisa_one = findViewById(R.id.spn_divisa1) as Spinner
        divisa_two = findViewById(R.id.spn_divisa2) as Spinner
        result = findViewById(R.id.tv_result) as TextView
        val btnConvert = findViewById<Button>(R.id.btn_convert)
        change = findViewById(R.id.tv_cambio) as TextView
        input = findViewById(R.id.edt_input) as TextView
        btnConvert.setOnClickListener { change() }


        val options = listOf("MXN", "EUR", "USD", "GBP", "YEN")

        divisa_one.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        divisa_two.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)


        divisa_one.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                result.text = "Opciones"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    currency1 = 0.050
                }
                if (position == 1) {
                    currency1 = 1.18
                }
                if (position == 2) {
                    currency1 = 1.0
                }
                if (position == 3) {
                    currency1 = 1.38
                }
                if (position == 4) {
                    currency1 = 0.0091
                }
            }
        }


        divisa_two.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                result.text = "Opciones"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result.text = options.get(position)
                if (position == 0) {
                    currency2 = 0.050
                }
                if (position == 1) {
                    currency2 = 1.18
                }
                if (position == 2) {
                    currency2 = 1.0
                }
                if (position == 3) {
                    currency2 = 0.15
                }
                if (position == 4) {
                    currency2 = 1.09
                }

            }
        }

    }
    private fun change() {
        val resultado_cambio = findViewById<TextView>(R.id.tv_cambio)
        val cantidadInput = findViewById<EditText>(R.id.edt_input)

        if(cantidadInput.text.toString() == "")
        {
            resultado_cambio.text = "Ingrese un valor"
        }
        else{

            resultado = currency1/currency2
            resultado = resultado*cantidadInput.text.toString().toFloat()
            resultado_cambio.text = resultado.toString()


            if(currency1 == currency2){
                resultado = 1.00*cantidadInput.text.toString().toFloat()
                resultado_cambio.text = resultado.toString()
            }

        }

    }
}
