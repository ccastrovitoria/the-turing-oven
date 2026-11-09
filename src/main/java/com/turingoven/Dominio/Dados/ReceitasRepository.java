package com.turingoven.Dominio.Dados;

import com.turingoven.Dominio.Entidades.Receita;

public interface ReceitasRepository {
    Receita recuperaReceita(long id);
    
}
