package com.example.xls2sql.sql.tipoDadosSql;

import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSQLString;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlDateTime;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlFactory;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TipoDadosSqlFactoryTest {

    private TipoDadosSqlFactory factory;

    @BeforeEach
    public void antesCada(){
        this.factory = new TipoDadosSqlFactory();
    }


    @Test
    public void criaTipoDadosComEspacamentoNaString(){
        Assertions.assertEquals(TipoDadosSqlNumeric.TINYINT,factory.generate("TinY iNt",1));
        Assertions.assertEquals(TipoDadosSQLString.LONGBLOB,factory.generate("LoNg BlOb",1));
        Assertions.assertEquals(TipoDadosSqlDateTime.TIME,factory.generate("Ti mE",1));
    }

    @Test
    public void lancaTipoDadoSqlNaoEncontradoExceptionQuandoNomeTipoDadosNaoEncontrado(){
        Assertions.assertDoesNotThrow(() -> factory.generate("bit",1));
        Assertions.assertThrows(TipoDadoSqlNaoEncontradoException.class, () -> factory.generate("bygInt",1) );
    }

    @Test
    public void criaTipoDadosComEspacamentoNoFim(){
        Assertions.assertEquals(TipoDadosSqlNumeric.DECIMAL,factory.generate("decimal ",1));
        Assertions.assertEquals(TipoDadosSQLString.CHAR,factory.generate("char ",1));
        Assertions.assertEquals(TipoDadosSqlDateTime.DATETIME,factory.generate("datetime ",1));
    }



//    NUMERIC
    @Test
    public void criaTipoDadosNumericComParametroEmUperELowerCase(){
        Assertions.assertEquals("TipoDadosSqlNumeric",factory.generate("BigInt",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSqlNumeric.BIGINT,factory.generate("BigInt",1));
    }
    @Test
    public void criaTipoDadosNumericComParametroEmLowerCase(){
        Assertions.assertEquals("TipoDadosSqlNumeric",factory.generate("bigint",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSqlNumeric.BIGINT,factory.generate("bigint",1));
    }
    @Test
    public void criaTipoDadosNumericComParametroEmUperCase(){
        Assertions.assertEquals("TipoDadosSqlNumeric",factory.generate("BIGINT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSqlNumeric.BIGINT,factory.generate("BIGINT",1));
    }


//    STRING
    @Test
    public void criaTipoDadosStringComParametroEmUperELowerCase(){
        Assertions.assertEquals("TipoDadosSQLString",factory.generate("TexT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSQLString.TEXT,factory.generate("TeXt",1));
    }
    @Test
    public void criaTipoDadosStringComParametroEmLowerCase(){
        Assertions.assertEquals("TipoDadosSQLString",factory.generate("text",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSQLString.TEXT,factory.generate("text",1));
    }
    @Test
    public void criaTipoDadosStringComParametroEmUperCase(){
        Assertions.assertEquals("TipoDadosSQLString",factory.generate("TEXT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSQLString.TEXT,factory.generate("TEXT",1));
    }



//    DATETIME
    @Test
    public void criaTipoDadosDateTimeomParametroEmUperELowerCase(){
        Assertions.assertEquals("TipoDadosSqlDateTime",factory.generate("TimEStamp",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSqlDateTime.TIMESTAMP,factory.generate("TimeStaMp",1));
    }
    @Test
    public void criaTipoDadosDateTimeComParametroEmLowerCase(){
        Assertions.assertEquals("TipoDadosSqlDateTime",factory.generate("timestamp",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSqlDateTime.TIMESTAMP,factory.generate("timestamp",1));
    }
    @Test
    public void criaTipoDadosDateTimeComParametroEmUperCase(){
        Assertions.assertEquals("TipoDadosSqlDateTime",factory.generate("TIMESTAMP",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoDadosSqlDateTime.TIMESTAMP,factory.generate("TIMESTAMP",1));
    }
}
