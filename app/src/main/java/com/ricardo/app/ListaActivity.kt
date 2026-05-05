package com.ricardo.app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import com.ricardo.app.Carrinho
import com.ricardo.app.Produto


class ListaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                TelaListaProdutos()
            }
        }
    }
}

@Composable
fun TelaListaProdutos() {
    val context = LocalContext.current

    var mensagem by remember { mutableStateOf("") }

    val produtos = listOf(
        Produto("Café", 10.0),
        Produto("Pão de queijo", 6.5),
        Produto("Suco", 8.0),
        Produto("Bolo", 7.5),
        Produto("Água", 4.0),
        Produto("Refrigerante", 6.0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Lista de produtos")

        Spacer(modifier = Modifier.height(8.dp))

        if (mensagem.isNotEmpty()) {
            Text(text = mensagem)
            Spacer(modifier = Modifier.height(8.dp))
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(produtos) { produto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = produto.nome)
                        Text(text = "R$ %.2f".format(produto.preco))

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                Carrinho.adicionarProduto(produto)
                                mensagem = "${produto.nome} adicionado ao carrinho"
                            }
                        ) {
                            Text("Adicionar")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                (context as Activity).finish()
            }
        ) {
            Text("Voltar")
        }
    }
}

