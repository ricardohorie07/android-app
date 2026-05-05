package com.ricardo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.app.Activity
import com.ricardo.app.Carrinho

class VendaFinalizadaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                TelaVendaFinalizada()
            }
        }
    }
}

@Composable
fun TelaVendaFinalizada() {
    val context = LocalContext.current
    val itens = Carrinho.listarItens()
    val total = Carrinho.calcularTotal()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Venda finalizada")

        Spacer(modifier = Modifier.height(16.dp))

        itens.forEach { item ->
            Text(
                text = "${item.produto.nome} x${item.quantidade} - R$ %.2f".format(
                    item.produto.preco * item.quantidade
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Total pago: R$ %.2f".format(total))

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                Carrinho.limpar()
                (context as Activity).finish()
            }
        ) {
            Text("Nova venda")
        }
    }
}