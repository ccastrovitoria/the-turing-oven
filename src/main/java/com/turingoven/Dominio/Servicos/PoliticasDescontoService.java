
package com.turingoven.Dominio.Servicos;

import java.util.List;

import org.springframework.stereotype.Service;

@Service // Informa ao Spring que essa classe representa um serviço e deve ser gerenciada por ele.
public class PoliticasDescontoService {

    private final List<PoliticaDesconto> politicas;

    public PoliticasDescontoService( // constructor injection: o Spring injeta as implementações de PoliticaDesconto no construtor da classe
            List<PoliticaDesconto> politicas) { // recebe uma lista de implementações de PoliticaDesconto, que são injetadas pelo Spring
        this.politicas = List.copyOf(politicas);
    }

    public List<PoliticaDesconto> listarPoliticasDisponiveis() {
        return politicas;
    }
}
