package com.turingoven.Dominio.Servicos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turingoven.Dominio.Dados.CardapioRepository;
import com.turingoven.Dominio.Entidades.CabecalhoCardapio;
import com.turingoven.Dominio.Entidades.Cardapio;
import com.turingoven.Dominio.Entidades.Produto;

/* O repositório JDBC executa as operações exigidas pela interface. A classe CardapioService utiliza os resultados dessas 
operações para atender às solicitações dos casos de uso, devolvendo os dados consultados ou confirmando as alterações realizadas. */

@Service // Informa ao Spring que essa classe representa um serviço e deve ser gerenciada por ele.
public class CardapioService {
    private CardapioRepository cardapioRepository;

    @Autowired
    public CardapioService(CardapioRepository cardapioRepository){
        this.cardapioRepository = cardapioRepository;
    }

    public Cardapio recuperaCardapio(long Id){
        return cardapioRepository.recuperaPorId(Id);
    }

    /* Quando o método é chamado, Java executa a implementação concreta fornecida ao serviço — no nosso projeto, 
    CardapioRepositoryJDBC. */

    public List<CabecalhoCardapio> recuperaListaDeCardapios(){
        return cardapioRepository.cardapiosDisponiveis();
    }

    public List<Produto> recuperaSugestoesDoChef(){
        return cardapioRepository.indicacoesDoChef();
    }

    /* UC2: Chama o método responsável por atualizar no banco de dados qual cardápio está definido como 
    atual, sem precisar escrever em SQL ou usar JdbcTemplate ou conhecer a classe CardapioRepositoryJDBC */

    public void definirCardapioAtual(long id) {
        cardapioRepository.definirCardapioAtual(id);
    }

    /* UC5: Chama o método do CardapioRepository executado pelo CardapioRepositoryJDBC que busca o cardápio atual 
    no banco de dados e devolve o cardápio encontrado para RecuperarCardapioUC, que fez a solicitação */

    public Cardapio recuperaCardapioAtual() {
    return cardapioRepository.recuperaCardapioAtual();
}
}
