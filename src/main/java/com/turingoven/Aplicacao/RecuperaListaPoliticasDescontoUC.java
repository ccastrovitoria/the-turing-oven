package com.turingoven.Aplicacao;

import org.springframework.stereotype.Component;

import com.turingoven.Aplicacao.Responses.PoliticasDescontoResponse;
import com.turingoven.Dominio.Servicos.PoliticasDescontoService;

// Indica ao Spring que esta classe representa um caso de uso gerenciado pela aplicação.
@Component
public class RecuperaListaPoliticasDescontoUC {

    private final PoliticasDescontoService politicasDescontoService;

    // O Spring injeta o serviço de domínio necessário para executar este caso de uso.
    public RecuperaListaPoliticasDescontoUC(
            PoliticasDescontoService politicasDescontoService) {
        this.politicasDescontoService = politicasDescontoService;
    }

    // Executa a UC3: solicita ao serviço a lista de políticas e organiza o resultado na Response.
    public PoliticasDescontoResponse run() {
        return new PoliticasDescontoResponse(
            politicasDescontoService.listarPoliticasDisponiveis()
        );
    }
}