package com.turingoven.Dominio.Dados;

import java.util.List;

import com.turingoven.Dominio.Entidades.Produto;

public interface ProdutosRepository {
    Produto recuperaProdutoPorid(long id);
    List<Produto> recuperaProdutosCardapio(long id);
}
