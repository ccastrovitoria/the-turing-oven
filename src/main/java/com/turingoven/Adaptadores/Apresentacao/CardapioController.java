package com.turingoven.Adaptadores.Apresentacao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.turingoven.Adaptadores.Apresentacao.Presenters.CabecalhoCardapioPresenter;
import com.turingoven.Adaptadores.Apresentacao.Presenters.CardapioPresenter;
import com.turingoven.Aplicacao.RecuperaListaCardapiosUC;
import com.turingoven.Aplicacao.RecuperarCardapioUC;
import com.turingoven.Aplicacao.Responses.CardapioResponse;
import com.turingoven.Dominio.Entidades.Produto;
import com.turingoven.Aplicacao.DefineCardapioAtualUC;

/* O CardapioController é a classe responsável por receber as requisições HTTP relacionadas aos cardápios e 
encaminhá-las aos casos de uso apropriados. */

@RestController
@RequestMapping("/cardapio")
public class CardapioController {
    private RecuperarCardapioUC recuperaCardapioUC;
    private RecuperaListaCardapiosUC recuperaListaCardapioUC;
    private final DefineCardapioAtualUC defineCardapioAtualUC;

    public CardapioController(RecuperarCardapioUC recuperaCardapioUC,
        RecuperaListaCardapiosUC recuperaListaCardapioUC, 
        DefineCardapioAtualUC defineCardapioAtualUC) {

        this.recuperaCardapioUC = recuperaCardapioUC;
        this.recuperaListaCardapioUC = recuperaListaCardapioUC;
        this.defineCardapioAtualUC = defineCardapioAtualUC;
    }

    /* Recebe o identificador de um cardápio, executa sua recuperação e utiliza o CardapioPresenter para 
    preparar a resposta da API, contendo o título, os produtos, os preços e as sugestões do chef */

    @GetMapping("/{id}")
    @CrossOrigin("*")
    public CardapioPresenter recuperaCardapio(@PathVariable(value="id")long id){ // 
        
        CardapioResponse cardapioResponse = recuperaCardapioUC.run(id);
        Set<Long> conjIdSugestoes = new HashSet<>(cardapioResponse.getSugestoesDoChef().stream()
            .map(produto->produto.getId())
            .toList()); // Cria um conjunto de IDs dos produtos que são sugestões do chef, para facilitar a verificação posterior.
        
        CardapioPresenter cardapioPresenter = new CardapioPresenter(cardapioResponse.getCardapio().getCabecalhoCardapio().titulo());
        for(Produto produto:cardapioResponse.getCardapio().getProdutos()){ // Para cada produto da lista de produtos do cardápio
            boolean sugestao = conjIdSugestoes.contains(produto.getId()); // Se o produto está no conjunto de sugestões do chef
            cardapioPresenter.insereItem(produto.getId(), 
            produto.getDescricao(), 
            produto.getPreco(), 
            sugestao
            ); // Adiciona o produto ao CardapioPresenter, incluindo se é sugestão do chef ou não.
        }
        return cardapioPresenter; // Retorna o CardapioPresenter, que será convertido em JSON e enviado como resposta da API.
    }

    /* @GetMapping("/lista") está dizendo ao Spring: "Quando alguém fizer uma requisição GET para /cardapio/lista, 
    execute este método. GET é um tipo de requisição HTTP utilizado para solicitar informações a um servidor " */

    @GetMapping("/lista") 
    @CrossOrigin("*") // Permite que qualquer origem (domínio) faça requisições para este endpoint
    public List<CabecalhoCardapioPresenter> recuperaListaCardapios(){
        List<CabecalhoCardapioPresenter> lstCardapios = 
            recuperaListaCardapioUC.run().cabecalhos().stream() // Percorre os cabeçalhos e transforma cada um em um objeto
            .map(cabCar -> new CabecalhoCardapioPresenter(cabCar.id(),cabCar.titulo()))
            .toList();
        return lstCardapios;
    }

    /* @PutMapping("/{id}/atual") indica ao Spring que, quando alguém fizer uma requisição PUT para /cardapio/{id}/atual,
    este método será executado. PUT é um tipo de requisição HTTP utilizado para atualizar informações no servidor. */

    @PutMapping("/{id}/atual")
    public void definirCardapioAtual(@PathVariable("id") long id) { // PathVariable pega o valor informado na posição {id} da URL
        defineCardapioAtualUC.run(id);
    }
}
