package com.turingoven.Aplicacao.Responses;

import java.util.List;

import com.turingoven.Dominio.Servicos.PoliticaDesconto;

// Organiza a lista de políticas de desconto que será devolvida pelo caso de uso.
public record PoliticasDescontoResponse(List<PoliticaDesconto> politicas) {
}