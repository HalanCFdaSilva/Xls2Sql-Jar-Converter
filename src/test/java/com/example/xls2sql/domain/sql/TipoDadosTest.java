package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.factorys.TipoDadosFactory;
import com.example.xls2sql.sql.domain.TipoDados;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TipoDadosTest {

    @Test
    public void conseguirAcharOtipoDeDadoSqlComTextoMinusculo(){

        TipoDados tipoDados =  TipoDadosFactory.generate("varchar",1);
        Assertions.assertEquals(TipoColunaSQLString.VARCHAR, tipoDados.tipoColunaSql());

    }
    @Test
    public void conseguirAcharOtipoDeDadoSqlComTextoTodoMaiusculo(){

        TipoDados tipoDados =  TipoDadosFactory.generate("VARCHAR",1);
        Assertions.assertEquals(TipoColunaSQLString.VARCHAR, tipoDados.tipoColunaSql());

    }

    @Test
    public void conseguirAcharOtipoDeDadoSqlENumeroDeElementos(){

        TipoDados tipoDados =  TipoDadosFactory.generate("varchar(10)",1);
        Assertions.assertEquals(TipoColunaSQLString.VARCHAR, tipoDados.tipoColunaSql());
        Assertions.assertEquals(10, tipoDados.numeroElementos());

    }

    @Test
    public void PorZeroQuandoNaoHaNumeroDeElementos(){

        TipoDados tipoDados =  TipoDadosFactory.generate("varchar",1);

        Assertions.assertEquals(0, tipoDados.numeroElementos());

    }


}
