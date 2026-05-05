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
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts



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
    val context = LocalContext.current

    var atualizarTela by remember { mutableStateOf(0) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        atualizarTela++
    }

    // força a tela a reler o carrinho quando atualizarTela muda
    atualizarTela

    val itens = Carrinho.listarItens()
    val total = Carrinho.calcularTotal()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "PDV - Caixa")

            Spacer(modifier = Modifier.height(16.dp))

            if (itens.isEmpty()) {
                Text(text = "Carrinho vazio")
            } else {
                itens.forEach { item ->
                    Text(
                        text = "${item.produto.nome} x${item.quantidade} - R$ %.2f".format(
                            item.produto.preco * item.quantidade
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Total: R$ %.2f".format(total))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val intent = Intent(context, ListaActivity::class.java)
                    launcher.launch(intent)
                }
            ) {
                Text("Selecionar produtos")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    Carrinho.limpar()
                    atualizarTela++
                }
            ) {
                Text("Limpar venda")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                enabled = !Carrinho.estaVazio(),
                onClick = {
                    val intent = Intent(context, VendaFinalizadaActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text("Finalizar venda")
            }
        }
    }
}