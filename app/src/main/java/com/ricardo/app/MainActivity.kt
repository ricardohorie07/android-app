package com.ricardo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                TelaPDV()
            }
        }
    }
}

@Composable
fun TelaPDV() {
    var quantidade by remember { mutableStateOf(0) }
    val preco = 10.0
    val total = quantidade * preco
    val context = LocalContext.current

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "PDV - Caixa")
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Produto: Café")
            Text(text = "Preço: R$ $preco")
            Text(text = "Quantidade: $quantidade")
            Text(text = "Total: R$ $total")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { quantidade++ }) {
                Text("Adicionar Café")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { quantidade = 0 }) {
                Text("Limpar venda")

            }

            Button(
                onClick = {
                    val intent = Intent(context, ListaActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text("Abrir lista")
            }
        }
    }
}