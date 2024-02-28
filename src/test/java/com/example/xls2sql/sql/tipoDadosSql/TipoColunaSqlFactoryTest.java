package com.example.xls2sql.sql.tipoDadosSql;

import com.example.xls2sql.sql.exceptions.TipoColunaSqlNaoEncontradoException;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;
import com.example.xls2sql.sql.factorys.TipoDadosSqlFactory;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TipoColunaSqlFactoryTest {

    private TipoDadosSqlFactory factory;

    @BeforeEach
    public void antesCada(){
        this.factory = new TipoDadosSqlFactory();
    }


    @Test
    public void criaTipoDadosComEspacamentoNaString(){
        Assertions.assertEquals(TipoColunaSqlNumeric.TINYINT,factory.generate("TinY iNt",1));
        Assertions.assertEquals(TipoColunaSQLString.LONGBLOB,factory.generate("LoNg BlOb",1));
        Assertions.assertEquals(TipoColunaSqlDateTime.TIME,factory.generate("Ti mE",1));
    }

    @Test
    public void lancaTipoDadoSqlNaoEncontradoExceptionQuandoNomeTipoDadosNaoEncontrado(){
        Assertions.assertDoesNotThrow(() -> factory.generate("bit",1));
        Assertions.assertThrows(TipoColunaSqlNaoEncontradoException.class, () -> factory.generate("bygInt",1) );
    }

    @Test
    public void criaTipoDadosComEspacamentoNoFim(){
        Assertions.assertEquals(TipoColunaSqlNumeric.DECIMAL,factory.generate("decimal ",1));
        Assertions.assertEquals(TipoColunaSQLString.CHAR,factory.generate("char ",1));
        Assertions.assertEquals(TipoColunaSqlDateTime.DATETIME,factory.generate("datetime ",1));
    }



//    NUMERIC
    @Test
    public void criaTipoDadosNumericComParametroEmUperELowerCase(){
        Assertions.assertEquals("TipoColunaSqlNumeric",factory.generate("BigInt",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlNumeric.BIGINT,factory.generate("BigInt",1));
    }
    @Test
    public void criaTipoDadosNumericComParametroEmLowerCase(){
        Assertions.assertEquals("TipoColunaSqlNumeric",factory.generate("bigint",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlNumeric.BIGINT,factory.generate("bigint",1));
    }
    @Test
    public void criaTipoDadosNumericComParametroEmUperCase(){
        Assertions.assertEquals("TipoColunaSqlNumeric",factory.generate("BIGINT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlNumeric.BIGINT,factory.generate("BIGINT",1));
    }


//    STRING
    @Test
    public void criaTipoDadosStringComParametroEmUperELowerCase(){
        Assertions.assertEquals("TipoColunaSQLString",factory.generate("TexT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSQLString.TEXT,factory.generate("TeXt",1));
    }
    @Test
    public void criaTipoDadosStringComParametroEmLowerCase(){
        Assertions.assertEquals("TipoColunaSQLString",factory.generate("text",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSQLString.TEXT,factory.generate("text",1));
    }
    @Test
    public void criaTipoDadosStringComParametroEmUperCase(){
        Assertions.assertEquals("TipoColunaSQLString",factory.generate("TEXT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSQLString.TEXT,factory.generate("TEXT",1));
    }



//    DATETIME
    @Test
    public void criaTipoDadosDateTimeomParametroEmUperELowerCase(){
        Assertions.assertEquals("TipoColunaSqlDateTime",factory.generate("TimEStamp",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlDateTime.TIMESTAMP,factory.generate("TimeStaMp",1));
    }
    @Test
    public void criaTipoDadosDateTimeComParametroEmLowerCase(){
        Assertions.assertEquals("TipoColunaSqlDateTime",factory.generate("timestamp",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlDateTime.TIMESTAMP,factory.generate("timestamp",1));
    }
    @Test
    public void criaTipoDadosDateTimeComParametroEmUperCase(){
        Assertions.assertEquals("TipoColunaSqlDateTime",factory.generate("TIMESTAMP",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlDateTime.TIMESTAMP,factory.generate("TIMESTAMP",1));
    }
}
