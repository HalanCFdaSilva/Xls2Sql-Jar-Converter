package com.example.xls2sql.domain.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DadosSqlTest {

    @Test
    public void adicionarColunaSql(){
        Coluna coluna =new Coluna();
        coluna.adicionar("Pratos[int]",1);
        DadosSql dadosSql = new DadosSql();
        dadosSql.adicionar(coluna);
        Assertions.assertEquals(coluna,dadosSql.getColunas().get(0));

    }

    @Test
    public void adicionarElementoSql() {
        ElementosSql elementosSql = new ElementosSql();
        DadosSql dadosSql = new DadosSql();
        dadosSql.adicionar(elementosSql);

        Assertions.assertEquals(elementosSql,dadosSql.getElementos().get(0));
    }


}
