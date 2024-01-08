package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.TipoDadosSql;
import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class TipoDadosTest {

    @Test
    public void conseguirAcharOtipoDeDadoSqlComTextoMinusculo(){

        TipoDados tipoDados =  new TipoDados("varchar", 1);
        Assertions.assertEquals(TipoDadosSql.VARCHAR, tipoDados.getTipo());

    }
    @Test
    public void conseguirAcharOtipoDeDadoSqlComTextoTodoMaiusculo(){

        TipoDados tipoDados =  new TipoDados("VARCHAR",1);
        Assertions.assertEquals(TipoDadosSql.VARCHAR, tipoDados.getTipo());

    }

    @Test
    public void conseguirAcharOtipoDeDadoSqlENumeroDeElementos(){

        TipoDados tipoDados =  new TipoDados("varchar(10)",1);
        Assertions.assertEquals(TipoDadosSql.VARCHAR, tipoDados.getTipo());
        Assertions.assertEquals("(10)", tipoDados.getNumeroElementosString());

    }

    @Test
    public void PorNullQuandoNaoHaNumeroDeElementos(){

        TipoDados tipoDados =  new TipoDados("varchar", 1);

        Assertions.assertEquals(null, tipoDados.getNumeroElementosString());

    }

    @Test
    public void darExcessionQuandoTipoDadoSqlNãoForEncontrado(){

        Assertions.assertThrows(TipoDadoSqlNaoEncontradoException.class,
                (Executable) new TipoDados("String", 1));


    }
}
