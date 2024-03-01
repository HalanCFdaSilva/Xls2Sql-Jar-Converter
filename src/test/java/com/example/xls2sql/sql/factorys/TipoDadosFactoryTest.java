package com.example.xls2sql.sql.factorys;

import com.example.xls2sql.sql.exceptions.TipoColunaSqlNaoEncontradoException;
import com.example.xls2sql.sql.domain.TipoDados;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**Classe que faz testes automatizados da classe TipoDadosFactory.
 * @author Halan Silva
 * @see TipoDadosFactory*/
public class TipoDadosFactoryTest {

    /**Método que faz verificações se o TipoDadosFactory cria um tipoDados com um TipoColunaSql
     * apartir de uma string em minusculo.*/
    @Test
    public void conseguirAcharOTipoDeDadoSqlComTextoMinusculo(){

        TipoDados tipoDados =  TipoDadosFactory.generate("varchar",1);
        Assertions.assertEquals(TipoColunaSQLString.VARCHAR, tipoDados.tipoColunaSql());

    }
    /**Método que faz verificações se o TipoDadosFactory cria um tipoDados com um TipoColunaSql
     * apartir de uma string em Maiúsculo.*/
    @Test
    public void conseguirAcharOTipoDeDadoSqlComTextoTodoMaiusculo(){

        TipoDados tipoDados =  TipoDadosFactory.generate("VARCHAR",1);
        Assertions.assertEquals(TipoColunaSQLString.VARCHAR, tipoDados.tipoColunaSql());

    }

    /**Método que faz verificações se o TipoDadosFactory cria um tipoDados com um TipoColunaSql
     * e um NumeroElementos apartir de uma string que possui numero de Elementos especificado.*/
    @Test
    public void conseguirAcharOtipoDeDadoSqlComNumeroDeElementos(){

        TipoDados tipoDados =  TipoDadosFactory.generate("varchar(10)",1);
        Assertions.assertEquals(TipoColunaSQLString.VARCHAR, tipoDados.tipoColunaSql());
        Assertions.assertEquals(10, tipoDados.numeroElementos());

    }
    /**Método que faz verificações se o TipoDadosFactory cria um tipoDados com um TipoColunaSql
     * e a variável NumeroElementos com valor zero apartir de uma string sem numero de Elementos especificado.*/
    @Test
    public void criarTipoDadosComVariavelNumeroElementosIgualZeroQuandoNaoHaNumeroDeElementosNaStringDeParametro(){

        TipoDados tipoDados =  TipoDadosFactory.generate("varchar",1);

        Assertions.assertEquals(0, tipoDados.numeroElementos());

    }

    /**Método que faz verificações se o TipoDadosFactory cria um tipoDados com um TipoColunaSql
     * e a variável NumeroElementos com valor zero apartir de uma string com numero de Elementos especificado
     * na String quando o tipoColunaSql especificado na String não aceitar especificação de limite de dados
     * vindo do usuário.*/
    @Test
    public void criarTipoDadosComVariavelNumeroElementosIgualZeroQuandoTipoColunaNaoAceitaNumeroElementos(){

        TipoDados tipoDados =  TipoDadosFactory.generate("varchar(10)",1);

        Assertions.assertTrue(tipoDados.tipoColunaSql().aceitaNumeroElementos());
        Assertions.assertEquals(10, tipoDados.numeroElementos());

        tipoDados =  TipoDadosFactory.generate("tinYText(10)",1);

        Assertions.assertFalse(tipoDados.tipoColunaSql().aceitaNumeroElementos());
        Assertions.assertEquals(0, tipoDados.numeroElementos());



    }

    /**Método que verifica se o TipoDadosFactory solta a excessão TipoColunaSqlNaoEncontradoException
     * quando o factory não consegue encontrar o TipoColunaSql apartir da String
     * @see TipoColunaSqlNaoEncontradoException*/
    @Test
    public void darTipoColunaSqlNaoEncontradoExceptionQuandoTipoDadoSqlNãoForEncontrado(){

        Assertions.assertThrows(TipoColunaSqlNaoEncontradoException.class,
                () -> TipoDadosFactory.generate("String",1));


    }

    /**Método que verifica se o factory consegue criar um TipoColunaSqlString quando vêm ou não um número de elemêntos
     * junto na String.
     * @see TipoColunaSQLString*/
    @Test
    public void criarTipoColunaSqlStringApartirDeUmaStringComOuSemNumeroEleMentos(){

        TipoDados tipoDados = TipoDadosFactory.generate("TexT", 1);
        Assertions.assertEquals(TipoColunaSQLString.TEXT,tipoDados.tipoColunaSql());
        Assertions.assertEquals(0, tipoDados.numeroElementos());

        tipoDados= null;
        tipoDados = TipoDadosFactory.generate("TexT(10)", 1);
        Assertions.assertEquals(TipoColunaSQLString.TEXT,tipoDados.tipoColunaSql());
        Assertions.assertEquals(10, tipoDados.numeroElementos());

    }

    /**Método que verifica se o factory consegue criar um TipoColunaSqlNumeric quando vêm ou não um número de elemêntos
     * junto na String.
     * @see TipoColunaSqlNumeric*/
    @Test
    public void criarTipoColunaSqlNumericApartirDeUmaStringComOuSemNumeroEleMentos(){

        TipoDados tipoDados = TipoDadosFactory.generate("biT", 1);
        Assertions.assertEquals(TipoColunaSqlNumeric.BIT,tipoDados.tipoColunaSql());
        Assertions.assertEquals(0, tipoDados.numeroElementos());

        tipoDados= null;
        tipoDados = TipoDadosFactory.generate("biT(10)", 1);
        Assertions.assertEquals(TipoColunaSqlNumeric.BIT,tipoDados.tipoColunaSql());
        Assertions.assertEquals(10, tipoDados.numeroElementos());

    }

    /**Método que verifica se o factory consegue criar um TipoColunaSqlNumeric quando vêm ou não um número de elemêntos
     * junto na String.
     * @see TipoColunaSqlDateTime*/
    @Test
    public void criarTipoColunaSqlDateTimeApartirDeUmaStringComOuSemNumeroEleMentos(){

        TipoDados tipoDados = TipoDadosFactory.generate("DateTime", 1);
        Assertions.assertEquals(TipoColunaSqlDateTime.DATETIME,tipoDados.tipoColunaSql());
        Assertions.assertEquals(0, tipoDados.numeroElementos());

        tipoDados= null;
        tipoDados = TipoDadosFactory.generate("DateTime(10)", 1);
        Assertions.assertEquals(TipoColunaSqlDateTime.DATETIME,tipoDados.tipoColunaSql());
        Assertions.assertEquals(0, tipoDados.numeroElementos());

    }


}
