package com.example.xls2sql.domain.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ElementoSqlTest {

    @Test
    public  void guardaPosicaoLihaAoCriarElementoSql(){
        ElementoSql elementoSql = new ElementoSql(1);
        Assertions.assertEquals(1, elementoSql.getLinha());
    }

    @Test
    public void guardaCelulaAoUsarSetCelula(){
        ElementoSql elementoSql = new ElementoSql(1);
        elementoSql.setCelula("arroz");
        Assertions.assertEquals("arroz",elementoSql.getCelula());
    }
}
