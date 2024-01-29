package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSQLString;
import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TipoDadosTest {

    @Test
    public void conseguirAcharOtipoDeDadoSqlComTextoMinusculo(){

        TipoDados tipoDados =  new TipoDados("varchar",1);
        Assertions.assertEquals(TipoDadosSQLString.VARCHAR, tipoDados.getTipo());

    }
    @Test
    public void conseguirAcharOtipoDeDadoSqlComTextoTodoMaiusculo(){

        TipoDados tipoDados =  new TipoDados("VARCHAR",1);
        Assertions.assertEquals(TipoDadosSQLString.VARCHAR, tipoDados.getTipo());

    }

    @Test
    public void conseguirAcharOtipoDeDadoSqlENumeroDeElementos(){

        TipoDados tipoDados =  new TipoDados("varchar(10)",1);
        Assertions.assertEquals(TipoDadosSQLString.VARCHAR, tipoDados.getTipo());
        Assertions.assertEquals("(10)", tipoDados.getNumeroElementosString());

    }

    @Test
    public void PorZeroQuandoNaoHaNumeroDeElementos(){

        TipoDados tipoDados =  new TipoDados("varchar",1);

        Assertions.assertEquals(0, tipoDados.getNumeroElementos());

    }

    @Test
    public void darExcessionQuandoTipoDadoSqlNãoForEncontrado(){

        Assertions.assertThrows(TipoDadoSqlNaoEncontradoException.class,
                () -> new TipoDados("String",1));


    }
}
