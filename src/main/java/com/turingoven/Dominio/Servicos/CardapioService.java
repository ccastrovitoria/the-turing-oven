package com.turingoven.Dominio.Servicos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turingoven.Dominio.Dados.CardapioRepository;
import com.turingoven.Dominio.Entidades.CabecalhoCardapio;
import com.turingoven.Dominio.Entidades.Cardapio;
import com.turingoven.Dominio.Entidades.Produto;

/* A interface CardapioRepository define quais operações de acesso aos dados devem estar disponíveis 
para as classes que precisam consultar os cardápios, principalmente o CardapioService sem depender 
de uma implementação específica de persistência. */

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
    atual, sem precisar escrever em SQL ou usar JdbcTemplate ou conhecer a classe CardapioRepositoryJDBC. */

    public void definirCardapioAtual(long id) {
        cardapioRepository.definirCardapioAtual(id);
    }
}
