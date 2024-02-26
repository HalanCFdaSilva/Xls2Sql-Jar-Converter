package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.DadosSql;
import com.example.xls2sql.sql.domain.LinhaSql;
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
        LinhaSql linhaSql = new LinhaSql(1);
        DadosSql dadosSql = new DadosSql();
        dadosSql.adicionar(linhaSql);

        Assertions.assertEquals(linhaSql,dadosSql.getAgregadorElementosSql().get(0));
    }


}
