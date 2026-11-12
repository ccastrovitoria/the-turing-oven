package com.turingoven.Adaptadores.Dados;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turingoven.Dominio.Dados.CardapioRepository;
import com.turingoven.Dominio.Dados.ProdutosRepository;
import com.turingoven.Dominio.Entidades.CabecalhoCardapio;
import com.turingoven.Dominio.Entidades.Cardapio;
import com.turingoven.Dominio.Entidades.Produto;

/* JDBC — Java Database Connectivity permite que uma aplicação Java se comunique com um banco de dados 
utilizando comandos SQL. 

No projeto vamos ter que trocar essa implementação para JPA que permite mapear objetos Java para tabelas 
e trabalhar com esses objetos. Usa JDBC por baixo dos panos (normalmente por meio de uma implementação como o Hibernate).  */

@Component // // Anotação para indicar que esta classe é um componente gerenciado pelo Spring
public class CardapioRepositoryJDBC implements CardapioRepository{ // 
    private JdbcTemplate jdbcTemplate;
    private ProdutosRepository produtosRepository;

    @Autowired // Indica qual construtor o Spring deve usar para injetar as dependências.
    public CardapioRepositoryJDBC(JdbcTemplate jdbcTemplate,ProdutosRepository  produtosRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.produtosRepository = produtosRepository;
    }

    @Override
    public Cardapio recuperaPorId(long id) {
        String sql = "SELECT id, titulo FROM cardapios WHERE id = ?";
        List<Cardapio> cardapios = this.jdbcTemplate.query(
            sql,
            ps -> ps.setLong(1, id),
            (rs, rowNum) -> new Cardapio(new CabecalhoCardapio(rs.getLong("id"), rs.getString("titulo")), null)
        );
        if (cardapios.isEmpty()) {
            return null;
        }
        Cardapio cardapio = cardapios.getFirst();
        List<Produto> produtos = produtosRepository.recuperaProdutosCardapio(id);
        cardapio.setProdutos(produtos);
        return cardapio;
    }

    @Override
    // Por enquanto retorna sempre a pizza de queijo e presunto como indicação do "chef"
    public List<Produto> indicacoesDoChef() {
        return List.of(produtosRepository.recuperaProdutoPorid(2L));   
    }

    @Override
    public List<CabecalhoCardapio> cardapiosDisponiveis(){
        String sql = "SELECT id, titulo FROM cardapios";
        
        List<CabecalhoCardapio> cabCardapios = this.jdbcTemplate.query( 
            sql, // Executa a consulta SQL
            ps->{}, // Não há parâmetros a serem definidos na consulta, então o PreparedStatement é deixado vazio
            
            // Transforma cada linha retornada em um objeto CabecalhoCardapio.
            (rs, rowNum) -> new CabecalhoCardapio(rs.getLong("id"), rs.getString("titulo"))

        );
        return cabCardapios;
    }

    /* UC2: definirCardapioAtual altera no banco de dados o cardápio atual da pizzaria */

    @Override
    @Transactional // Garante que, se alguma operação dentro do método falhar, todas serão revertidas.
    public void definirCardapioAtual(long id) {
        
        // Conta quantos cardápios possuem o identificador informado
        Integer quantidade = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM cardapios WHERE id = ?",
            Integer.class,
            id 
        );

        // // Impede a seleção de um cardápio que não existe
        if (quantidade == null || quantidade.intValue() == 0) {
            throw new IllegalArgumentException(
                "Cardápio não encontrado: " + id 
            );
        }

        // Desmarca todos os cardápios como não atuais
        jdbcTemplate.update(
            "UPDATE cardapios SET atual = false"
        );

        // Seleciona o novo cardápio atual pelo ID fornecido
        jdbcTemplate.update(
            "UPDATE cardapios SET atual = true WHERE id = ?",
            id
        );
    }

    /* UC5: Implementa a operação definida na interface CardapioRepository. Consulta o banco para encontrar o ID 
    do cardápio marcado como atual e utiliza o método recuperaPorId para carregar seus dados completos. */

    @Override
    public Cardapio recuperaCardapioAtual() {
    String sql = "SELECT id FROM cardapios WHERE atual = true";

    try {
        Long id = jdbcTemplate.queryForObject(sql, Long.class);
        return recuperaPorId(id);

    } catch (EmptyResultDataAccessException e) { // Exceção caso nenhum cardápio esteja definido como atual
        throw new IllegalStateException(
            "Nenhum cardápio atual foi definido."
        );
    }
}
}

