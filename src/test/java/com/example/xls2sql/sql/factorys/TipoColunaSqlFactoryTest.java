package com.example.xls2sql.sql.factorys;

import com.example.xls2sql.sql.exceptions.TipoColunaSqlNaoEncontradoException;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**Classe que faz testes automatizados da classe TipoDadosSqlFactory.
 * @author Halan Silva
 * @see TipoDadosSqlFactory*/
public class TipoColunaSqlFactoryTest {

    /**Variável referente a classe alvo dos testes.*/
    private TipoDadosSqlFactory factory;

    /**Método chamado antes de cada teste e que inicia a variável factory.*/
    @BeforeEach
    public void antesCada(){
        this.factory = new TipoDadosSqlFactory();
    }

    /**Método que verifica se o método generate da classe TipoDadosSqlFactory consegue criar o TipoColunaSql
     * corretamente mesmo com a String do parâmetro tendo espaçamento no meio do texto.*/
    @Test
    public void criaTipoColunaSqlComEspacamentoNaString(){
        Assertions.assertEquals(TipoColunaSqlNumeric.TINYINT,factory.generate("TinY iNt",1));
        Assertions.assertEquals(TipoColunaSQLString.LONGBLOB,factory.generate("LoNg BlOb",1));
        Assertions.assertEquals(TipoColunaSqlDateTime.TIME,factory.generate("Ti mE",1));
    }

    /**Método que verifica se o método generate da classe TipoDadosSqlFactory lança a exceção
     * TipoColunaSqlNaoEncontradoException quando a string está com o nome incorreto.
     * @see TipoColunaSqlNaoEncontradoException*/
    @Test
    public void lancaTipoColunaSqlNaoEncontradoExceptionQuandoNomeTipoDadosNaoEncontrado(){
        Assertions.assertDoesNotThrow(() -> factory.generate("bit",1));
        Assertions.assertThrows(TipoColunaSqlNaoEncontradoException.class, () -> factory.generate("bygInt",1) );
    }

    /**Método que verifica se o método generate da classe TipoDadosSqlFactory consegue criar o TipoColunaSql
     * corretamente mesmo com a String do parâmetro tendo espaçamento no fim do texto.*/
    @Test
    public void criaTipoColunaSqlComEspacamentoNoFim(){
        Assertions.assertEquals(TipoColunaSqlNumeric.DECIMAL,factory.generate("decimal ",1));
        Assertions.assertEquals(TipoColunaSQLString.CHAR,factory.generate("char ",1));
        Assertions.assertEquals(TipoColunaSqlDateTime.DATETIME,factory.generate("datetime ",1));
    }



//    NUMERIC
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlNumeric quando o
     * texto está com caractéres maiúsculos e minúsculos.
     * @see TipoColunaSqlNumeric*/
    @Test
    public void criaTipoColunaSqlNumericComParametroEmMaiusculoEMinusculo(){
        Assertions.assertEquals("TipoColunaSqlNumeric",factory.generate("BigInt",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlNumeric.BIGINT,factory.generate("BigInt",1));
    }
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlNumeric quando o
     * texto está com todos os caractéres em minúsculo.
     * @see TipoColunaSqlNumeric*/
    @Test
    public void criaTipoColunaSqlNumericComParametroEmLowerCase(){
        Assertions.assertEquals("TipoColunaSqlNumeric",factory.generate("bigint",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlNumeric.BIGINT,factory.generate("bigint",1));
    }
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlNumeric quando o
     * texto está com todos os caractéres em maiúsculo.
     * @see TipoColunaSqlNumeric*/
    @Test
    public void criaTipoColunaSqlNumericComParametroEmMaiusculo(){
        Assertions.assertEquals("TipoColunaSqlNumeric",factory.generate("BIGINT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlNumeric.BIGINT,factory.generate("BIGINT",1));
    }
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlNumeric para cada
     * um dos tipos do enum.
     * @see TipoColunaSqlNumeric*/
    @Test
    public void criaTodosOsTipoColunaSqlNumeric(){
        for(TipoColunaSqlNumeric numeric : TipoColunaSqlNumeric.values()){
            Assertions.assertEquals(numeric,factory.generate(numeric.name(),1));
        }
    }


//    STRING
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSQLString quando o
     * texto está com caractéres maiúsculos e minúsculos.
     * @see TipoColunaSQLString*/
    @Test
    public void criaTipoColunaSQLStringComParametroEmMaiusculoEMinusculo(){
        Assertions.assertEquals("TipoColunaSQLString",factory.generate("TexT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSQLString.TEXT,factory.generate("TeXt",1));
    }
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlString quando o
     * texto está com todos os caractéres em minúsculo.
     * @see TipoColunaSQLString*/
    @Test
    public void criaTipoColunaSQLStringComParametroEmMinusculo(){
        Assertions.assertEquals("TipoColunaSQLString",factory.generate("text",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSQLString.TEXT,factory.generate("text",1));
    }
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlString quando o
     * texto está com todos os caractéres em maiúsculo.
     * @see TipoColunaSQLString*/
    @Test
    public void criaTipoColunaSQLStringComParametroEmMaiusculo(){
        Assertions.assertEquals("TipoColunaSQLString",factory.generate("TEXT",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSQLString.TEXT,factory.generate("TEXT",1));
    }

    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSQLString para cada
     * um dos tipos do enum.
     * @see TipoColunaSQLString*/
    @Test
    public void criaTodosOsTipoColunaSQLString(){
        for(TipoColunaSQLString string : TipoColunaSQLString.values()){
            Assertions.assertEquals(string,factory.generate(string.name(),1));
        }
    }



//    DATETIME
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlDateTime quando o
     * texto está com caractéres maiúsculos e minúsculos.
     * @see TipoColunaSqlDateTime*/
    @Test
    public void criaTipoColunaSqlDateTimeComParametroEmMaiusculoEMinusculo(){
        Assertions.assertEquals("TipoColunaSqlDateTime",factory.generate("TimEStamp",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlDateTime.TIMESTAMP,factory.generate("TimeStaMp",1));
    }
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlDateTime quando o
     * texto está com todos os caractéres em minúsculo.
     * @see TipoColunaSqlDateTime*/
    @Test
    public void criaTipoColunaSqlDateTimeComParametroEmMinusculo(){
        Assertions.assertEquals("TipoColunaSqlDateTime",factory.generate("timestamp",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlDateTime.TIMESTAMP,factory.generate("timestamp",1));
    }
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlDateTime quando o
     * texto está com todos os caractéres em maiúsculo.
     * @see TipoColunaSqlDateTime*/
    @Test
    public void criaTipoColunaSqlDateTimeComParametroEmMaiusculo(){
        Assertions.assertEquals("TipoColunaSqlDateTime",factory.generate("TIMESTAMP",1).getClass().getSimpleName());
        Assertions.assertEquals(TipoColunaSqlDateTime.TIMESTAMP,factory.generate("TIMESTAMP",1));
    }
    /**Método que verifica se o método generate cria uma instância do enum TipoColunaSqlDateTime para cada
     * um dos tipos do enum.
     * @see TipoColunaSqlDateTime*/
    @Test
    public void criaTodosOsTipoColunaSqlDateTime(){
        for(TipoColunaSqlDateTime dateTime : TipoColunaSqlDateTime.values()){
            Assertions.assertEquals(dateTime,factory.generate(dateTime.name(),1));
        }
    }
}
