package com.turingoven.Aplicacao.Responses;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.turingoven.Dominio.Entidades.Cardapio;
import com.turingoven.Dominio.Entidades.Produto;

/* CardapioResponse funciona como um objeto que transporta o resultado do caso de uso para o Controller.
Ele não consulta o banco, não executa SQL e não altera os cardápios. */

public class CardapioResponse {
    private Cardapio cardapio;
    private List<Produto> sugestoesDoChef;
    
    public CardapioResponse(Cardapio cardapio, List<Produto> sugestoesDoChef) {
        this.cardapio = cardapio;
        this.sugestoesDoChef = sugestoesDoChef;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public List<Produto> getSugestoesDoChef() {
        return sugestoesDoChef;
    }
}
