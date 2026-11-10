package com.turingoven.Aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turingoven.Aplicacao.Responses.CardapioResponse;
import com.turingoven.Dominio.Entidades.Cardapio;
import com.turingoven.Dominio.Entidades.Produto;
import com.turingoven.Dominio.Servicos.CardapioService;

/* Recupera o cardápio solicitado e as sugestões do chef, reunindo essas informações em um objeto de resposta. */

@Component // Indica que esta classe é um componente gerenciado pelo Spring, permitindo a injeção de dependências.
public class RecuperarCardapioUC {
    private CardapioService cardapioService;

    @Autowired
    public RecuperarCardapioUC(CardapioService cardapioService){
        this.cardapioService = cardapioService;
    }

    public CardapioResponse run(long idCardapio){
        Cardapio cardapio = cardapioService.recuperaCardapio(idCardapio);
        List<Produto> sugestoes = cardapioService.recuperaSugestoesDoChef();
        return new CardapioResponse(cardapio,sugestoes);
    }
}
