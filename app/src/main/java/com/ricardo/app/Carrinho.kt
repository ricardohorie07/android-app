package com.ricardo.app

data class Produto(
    val nome: String,
    val preco: Double
)

data class ItemCarrinho(
    val produto: Produto,
    var quantidade: Int = 1
)

object Carrinho {
    private val itens = mutableListOf<ItemCarrinho>()

    fun adicionarProduto(produto: Produto) {
        val itemExistente = itens.find { it.produto.nome == produto.nome }

        if (itemExistente != null) {
            itemExistente.quantidade++
        } else {
            itens.add(ItemCarrinho(produto))
        }
    }

    fun listarItens(): List<ItemCarrinho> {
        return itens
    }

    fun calcularTotal(): Double {
        return itens.sumOf { it.produto.preco * it.quantidade }
    }

    fun limpar() {
        itens.clear()
    }

    fun estaVazio(): Boolean {
        return itens.isEmpty()
    }
}