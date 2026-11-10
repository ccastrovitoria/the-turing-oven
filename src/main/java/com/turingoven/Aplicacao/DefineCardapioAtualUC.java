package com.turingoven.Aplicacao;

import org.springframework.stereotype.Component;
import com.turingoven.Dominio.Servicos.CardapioService;

@Component // Anotação para indicar que esta classe é um componente gerenciado pelo Spring
public class DefineCardapioAtualUC {
    private final CardapioService cardapioService;

    /* Chama a classe CardapioService que disponibiliza operações relacionadas às regras e aos conceitos do negócio */
    public DefineCardapioAtualUC(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    // Chama o método definirCardapioAtual do nosso CardapioService, passando o ID recebido.
    public void run(long id) {
        cardapioService.definirCardapioAtual(id);
    }
}
