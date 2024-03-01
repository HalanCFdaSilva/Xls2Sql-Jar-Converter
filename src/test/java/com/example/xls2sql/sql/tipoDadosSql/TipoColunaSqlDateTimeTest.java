package com.example.xls2sql.sql.tipoDadosSql;

import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**Classe que faz testes automatizados no enum TipoColunaSqlDateTime.
 * @author HalanSilva
 * @see TipoColunaSqlDateTime*/
public class TipoColunaSqlDateTimeTest {


    /**Método que verifica se todas as constantes do enum retornam false quando chamado o método aceitaNumeroElementos.*/
    @Test
    public void daFalsoSempreQueChamaAceitaNumeroElementos(){

        for (TipoColunaSqlDateTime dateTime: TipoColunaSqlDateTime.values()){
            Assertions.assertFalse(dateTime.aceitaNumeroElementos());
        }


    }

    /**Método que verifica se todas as constantes do enum retornam false quando chamado o método verificarCelula
     * para um arrayList com tamanho maior a 1.*/
    @Test
    public void retornaFalseSempreQueCelulaTemTamanhoMaiorQueUm(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("1");
        celula.add("0");
        for (TipoColunaSqlDateTime tipoDadosSql : TipoColunaSqlDateTime.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
        }

    }

    /**Método que verifica se todas as constantes do enum retornam true quando chamado o método verificarCelula
     * para um arrayList de tamanho 1 que guarda uma string com "null" ou um espaçamento simples;*/
    @Test
    public void retornaTrueSempreQueCelulaEstaSalvoStringNullOuEspacoSimples(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("null");
        for (TipoColunaSqlDateTime tipoDadosSql : TipoColunaSqlDateTime.values()){
            Assertions.assertEquals(1, celula.size());
            Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
        }

        celula.set(0," ");
        for (TipoColunaSqlDateTime tipoDadosSql : TipoColunaSqlDateTime.values()){
            Assertions.assertEquals(1, celula.size());
            Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
        }

    }

    /**Método que verifica se todas as constantes do enum retornam false quando chamado o método verificarCelula
     * para um arrayList de tamanho maior a 1 e que guarde uma string com "null" ou um espaçamento simples*/
    @Test
    public void retornaFalseSempreQueCelulaEstaSalvoStringNullOuEspacoSimplesMasTamanhoMaiorA1(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("null");
        celula.add(" ");
        for (TipoColunaSqlDateTime tipoDadosSql : TipoColunaSqlDateTime.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,0));
        }

        celula.clear();
        celula.add(" ");
        celula.add("null");
        for (TipoColunaSqlDateTime tipoDadosSql : TipoColunaSqlDateTime.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,0));
        }

    }


//  DATE
    /**Método que verifica se a constante date retorna true quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto dentro do formato permitido pela constânte. */
    @Test
    public void retornaTrueQuandoDateNoFormatoCerto(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991/12/30");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.DATE;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    /**Método que verifica se a constante date retorna false quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto fora do formato sql para esse tipo. */
    @Test
    public void retornaFalseQuandoDateNoFormatoCerto(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.DATE;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }

//  TIME
    /**Método que verifica se a constante Time retorna true quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto dentro do formato permitido pela constânte. */
    @Test
    public void retornaTrueQuandoTimeNoFormatoCerto(){
        ArrayList<String> data = new ArrayList<>();
        data.add("750:25:35");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIME;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    /**Método que verifica se a constante Time retorna false quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto fora do formato permitido pela constânte. */
    @Test
    public void retornaFalseQuandoTimeNoFormatoErrado(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIME;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }


//  DATETIME
    /**Método que verifica se a constante DateTime retorna true quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto dentro do formato permitido pela constânte. */
    @Test
    public void retornaTrueQuandoDateTimeNoFormatoCerto(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.DATETIME;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    /**Método que verifica se a constante DateTime retorna false quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto fora do formato permitido pela constânte. */
    @Test
    public void retornaFalseQuandoDateTimeNoFormatoErrado(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.DATETIME;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }


//    TIMESTAMP
    /**Método que verifica se a constante TimeStamp retorna true quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto dentro do formato permitido pela constânte. */
    @Test
    public void retornaTrueQuandoTimeStampNoFormatoCerto(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIMESTAMP;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    /**Método que verifica se a constante TimeStamp retorna false quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto fora do formato permitido pela constânte. */
    @Test
    public void retornaFalseQuandoTimeStampNoFormatoErrado(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIMESTAMP;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }

    /**Método que verifica se a constante TimeStamp retorna false quando chamado o método verificarCelula e pondo
     * como parâmetro um arraylist contendo uma única string com o texto dentro do formato permitido pela constânte,
     * mas sendo uma data anterior a 01/01/1970 UTC. */
    @Test
    public void retornaFalseQuandoTimeStampComDataMenorA1970(){
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIMESTAMP;
        ArrayList<String> data = new ArrayList<>();
        data.add("1969/12/30 23:59:59");

        Assertions.assertFalse( dateTime.verificarCelula(data,0));

        data.set(0,"1970/01/01 00:00:00");
        Assertions.assertTrue(dateTime.verificarCelula(data,0));

    }

    /**Método que verifica se a constante TimeStamp retorna false quando chamado o método verificarCelula e pondo
     * como parâmetro um arraylist contendo uma única string com o texto dentro do formato permitido pela constânte,
     * mas sendo uma data após a 08/01/2038 UTC. */
    @Test
    public void retornaFalseQuandoTimeStampComDataMaiorA2038(){
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIMESTAMP;
        ArrayList<String> data = new ArrayList<>();
        data.add("2038/01/09 03:14:08");

        Assertions.assertFalse( dateTime.verificarCelula(data,0));

        data.set(0,"2038/01/09 00:14:06 UTC");
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

//    YEAR
    /**Método que verifica se a constante Year retorna true quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto dentro do formato permitido pela constânte. */
@Test
    public void retornaTrueQuandoYearNoFormatoCerto(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.YEAR;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    /**Método que verifica se a constante Year retorna false quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto fora do formato permitido pela constânte. */
    @Test
    public void retornaFalseQuandoYearNoFormatoErrado(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.YEAR;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }



}
