package com.turingoven.Aplicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turingoven.Aplicacao.Responses.CabecalhoCardapioResponse;
import com.turingoven.Dominio.Servicos.CardapioService;

@Component // Anotação para indicar que esta classe é um componente gerenciado pelo Spring
public class RecuperaListaCardapiosUC {
    private CardapioService cardapioService;

    /* Diz para o Spring que para criar um RecuperaListaCardapiosUC, eu preciso receber um CardapioService, 
    ao invés de criar uma instância diretamente. Garante e injeção de dependência */

    @Autowired
    public RecuperaListaCardapiosUC(CardapioService cardapioService){ 
        this.cardapioService = cardapioService;
    }

    /* Chama recuperaListaDeCardapios() para obter os cardápios e coloca o resultado dentro de um CabecalhoCardapioResponse. */

    public CabecalhoCardapioResponse run(){ // Método que executa a UC1 para recuperar a lista de cardápios
        return new CabecalhoCardapioResponse(cardapioService.recuperaListaDeCardapios()); 
    }    
}
