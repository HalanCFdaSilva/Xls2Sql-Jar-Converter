package com.example.xls2sql.sql.factorys;

import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TipoDadosFactoryTest {

    @Test
    public void darExcessionQuandoTipoDadoSqlNãoForEncontrado(){

        Assertions.assertThrows(TipoDadoSqlNaoEncontradoException.class,
                () -> TipoDadosFactory.generate("String",1));


    }
}
