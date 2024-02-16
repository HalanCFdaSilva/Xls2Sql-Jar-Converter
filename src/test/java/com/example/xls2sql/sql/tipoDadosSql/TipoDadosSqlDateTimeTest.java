package com.example.xls2sql.sql.tipoDadosSql;

import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlDateTime;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class TipoDadosSqlDateTimeTest {


    @Test
    public void daFalsoSempreQueChamaAceitaNumeroElementos(){


        boolean resultado = TipoDadosSqlDateTime.TIMESTAMP.aceitaNumeroElementos();

        for (TipoDadosSqlDateTime dateTime: TipoDadosSqlDateTime.values()){
            if (!resultado){
                resultado = dateTime.aceitaNumeroElementos();
            }
        }
        Assertions.assertFalse(resultado);

    }

    @Test
    public void retornaFalseSempreQueCelulaTemTamanhoMaiorQueUm(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("1");
        celula.add("0");
        for (TipoDadosSqlDateTime tipoDadosSql :TipoDadosSqlDateTime.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
        }

    }


//  DATE
    @Test
    public void retornaTrueQuandoDateNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991/12/30");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.DATE;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoDateNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.DATE;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }

//  TIME
    @Test
    public void retornaTrueQuandoTimeNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("750:25:35");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.TIME;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoTimeNoFormatoErrado(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.TIME;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }


//  DATETIME
    @Test
    public void retornaTrueQuandoDateTimeNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.DATETIME;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoDateTimeNoFormatoErrado(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.DATETIME;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }


//    TIMESTAMP
    @Test
    public void retornaTrueQuandOTimeStampNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.TIMESTAMP;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoTimeStampNoFormatoErrado(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.TIMESTAMP;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoTimeStampComDataMenorA1970(){
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.TIMESTAMP;
        ArrayList data = new ArrayList<>();
        data.add("1969/12/30 23:59:59");

        Assertions.assertFalse( dateTime.verificarCelula(data,0));

        data.set(0,"1970/01/01 00:00:00");
        Assertions.assertTrue(dateTime.verificarCelula(data,0));

    }

    @Test
    public void retornaFalseQuandoTimeStampComDataMaiorA2038(){
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.TIMESTAMP;
        ArrayList data = new ArrayList<>();
        data.add("2038/01/09 03:14:08");

        Assertions.assertFalse( dateTime.verificarCelula(data,0));

        data.set(0,"2038/01/09 00:14:06 UTC");
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

//    YEAR

@Test
    public void retornaTrueQuandoYearNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.YEAR;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoYearNoFormatoErrado(){
        ArrayList data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoDadosSqlDateTime dateTime = TipoDadosSqlDateTime.YEAR;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }



}
