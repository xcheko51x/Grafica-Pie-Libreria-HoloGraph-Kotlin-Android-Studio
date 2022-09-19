package com.example.piegraphholograpthkotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.echo.holographlibrary.PieGraph
import com.echo.holographlibrary.PieSlice
import com.example.piegraphholograpthkotlin.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var pieGrafica: PieGraph

    private var listaProductos = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pieGrafica = findViewById(R.id.graphPie) as PieGraph

        binding.btnAgregar.setOnClickListener {
            listaProductos.add(
                Producto(
                    binding.etConcepto.text.toString(),
                    binding.etCantidad.text.toString().toInt(),
                    generarColorHexAleatorio()
                )
            )
            binding.etConcepto.text = null
            binding.etCantidad.text = null
        }

        binding.btnGraficar.setOnClickListener { graficarPie() }
    }

    fun graficarPie() {
        for (i in 0 until listaProductos.size) {
            val rebanada = PieSlice()
            rebanada.color = Color.parseColor(listaProductos[i].color)
            rebanada.value = listaProductos[i].cantVendida.toString().toFloat()
            pieGrafica.addSlice(rebanada)

            when(i) {
                0 -> {
                    binding.tvProducto1.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto1.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                1 -> {
                    binding.tvProducto2.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto2.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                2 -> {
                    binding.tvProducto3.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto3.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
            }
        }
    }

    fun generarColorHexAleatorio(): String {
        var letras = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var color = "#"
        for (i in 0..5) {
            color += letras[(Math.random() *15).roundToInt()]
        }
        return color
    }
}