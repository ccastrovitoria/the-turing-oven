package com.turingoven.Dominio.Dados;

import java.util.List;

import com.turingoven.Dominio.Entidades.CabecalhoCardapio;
import com.turingoven.Dominio.Entidades.Cardapio;
import com.turingoven.Dominio.Entidades.Produto;

public interface CardapioRepository {
    List<CabecalhoCardapio> cardapiosDisponiveis();
    Cardapio recuperaPorId(long id);

    /* UC5: Toda classe que implementar CardapioRepository precisa oferecer uma operação chamada 
    recuperaCardapioAtual, que retorna o cardápio definido como atual, sem precisar receber um ID. */

    Cardapio recuperaCardapioAtual();
    List<Produto> indicacoesDoChef();
    
    /* UC2: Toda classe que implementar CardapioRepository precisa oferecer uma operação 
    chamada definirCardapioAtual, que recebe o ID de um cardápio. */

    void definirCardapioAtual(long id);
}
