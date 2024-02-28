package com.example.xls2sql.sql.factorys;

import com.example.xls2sql.sql.exceptions.TipoColunaSqlNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TipoDadosFactoryTest {

    @Test
    public void darExcessionQuandoTipoDadoSqlNãoForEncontrado(){

        Assertions.assertThrows(TipoColunaSqlNaoEncontradoException.class,
                () -> TipoDadosFactory.generate("String",1));


    }
}
