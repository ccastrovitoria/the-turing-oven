package com.turingoven.Aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turingoven.Aplicacao.Responses.CardapioResponse;
import com.turingoven.Dominio.Entidades.Cardapio;
import com.turingoven.Dominio.Entidades.Produto;
import com.turingoven.Dominio.Servicos.CardapioService;

/* Solicita ao CardapioService o cardápio atual e as sugestões do chef. Depois, reúne essas informações 
em um CardapioResponse para que o CardapioController possa utilizá-las. */

@Component // Indica que esta classe é um componente gerenciado pelo Spring, permitindo a injeção de dependências.
public class RecuperarCardapioUC {
    private CardapioService cardapioService;

    @Autowired
    public RecuperarCardapioUC(CardapioService cardapioService){
        this.cardapioService = cardapioService;
    }

    /* UC5: O cliente consulta só o cardapio definido como atual então o método run não precisa receber long idCardapio 
    e ao invés de chamar o método recuperaCardapio(idCardapio) ele chama do recuperaCardapioAtual()   
    
    CardapioResponse run(long idCardapio) → substituido → CardapioResponse run()
    cardapioService.recuperaCardapio(idCardapio); → substituido → cardapioService.recuperaCardapioAtual(); */

    public CardapioResponse run() {
        Cardapio cardapio = cardapioService.recuperaCardapioAtual();
        List<Produto> sugestoes = cardapioService.recuperaSugestoesDoChef();

        return new CardapioResponse(cardapio, sugestoes);
    }
}
