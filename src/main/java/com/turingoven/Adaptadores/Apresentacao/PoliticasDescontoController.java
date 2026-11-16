package com.turingoven.Adaptadores.Apresentacao;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingoven.Adaptadores.Apresentacao.Presenters.PoliticaDescontoPresenter;
import com.turingoven.Aplicacao.RecuperaListaPoliticasDescontoUC;

// Indica que esta classe recebe requisições HTTP e disponibiliza respostas pela API.
@RestController
@RequestMapping("/politicas-desconto")
public class PoliticasDescontoController {

    private final RecuperaListaPoliticasDescontoUC recuperaListaPoliticasDescontoUC;

    // O Spring injeta o caso de uso responsável por recuperar as políticas disponíveis.
    public PoliticasDescontoController(
            RecuperaListaPoliticasDescontoUC recuperaListaPoliticasDescontoUC) {
        this.recuperaListaPoliticasDescontoUC = recuperaListaPoliticasDescontoUC;
    }

    // UC3: disponibiliza a lista de políticas de desconto por meio de uma requisição GET.
    @GetMapping("/lista")
    @CrossOrigin("*")
    public List<PoliticaDescontoPresenter> recuperaListaPoliticasDesconto() {

        // Executa o caso de uso e obtém a lista de políticas disponíveis.
        return recuperaListaPoliticasDescontoUC.run().politicas().stream()

            // Transforma cada política em um Presenter com código e descrição.
            .map(politica -> new PoliticaDescontoPresenter(
                politica.codigo(),
                politica.descricao()
            ))

            // Converte o resultado em uma lista.
            .toList();
    }
}