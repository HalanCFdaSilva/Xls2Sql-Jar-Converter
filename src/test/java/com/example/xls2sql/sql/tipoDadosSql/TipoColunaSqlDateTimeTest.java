package com.example.xls2sql.sql.tipoDadosSql;

import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class TipoColunaSqlDateTimeTest {


    @Test
    public void daFalsoSempreQueChamaAceitaNumeroElementos(){


        boolean resultado = TipoColunaSqlDateTime.TIMESTAMP.aceitaNumeroElementos();

        for (TipoColunaSqlDateTime dateTime: TipoColunaSqlDateTime.values()){
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
        for (TipoColunaSqlDateTime tipoDadosSql : TipoColunaSqlDateTime.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
        }

    }


//  DATE
    @Test
    public void retornaTrueQuandoDateNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991/12/30");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.DATE;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoDateNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.DATE;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }

//  TIME
    @Test
    public void retornaTrueQuandoTimeNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("750:25:35");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIME;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoTimeNoFormatoErrado(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIME;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }


//  DATETIME
    @Test
    public void retornaTrueQuandoDateTimeNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.DATETIME;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoDateTimeNoFormatoErrado(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.DATETIME;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }


//    TIMESTAMP
    @Test
    public void retornaTrueQuandOTimeStampNoFormatoCerto(){
        ArrayList data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIMESTAMP;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoTimeStampNoFormatoErrado(){
        ArrayList data = new ArrayList<>();
        data.add("1991");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIMESTAMP;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoTimeStampComDataMenorA1970(){
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIMESTAMP;
        ArrayList data = new ArrayList<>();
        data.add("1969/12/30 23:59:59");

        Assertions.assertFalse( dateTime.verificarCelula(data,0));

        data.set(0,"1970/01/01 00:00:00");
        Assertions.assertTrue(dateTime.verificarCelula(data,0));

    }

    @Test
    public void retornaFalseQuandoTimeStampComDataMaiorA2038(){
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.TIMESTAMP;
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
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.YEAR;
        Assertions.assertTrue(dateTime.verificarCelula(data,0));
    }

    @Test
    public void retornaFalseQuandoYearNoFormatoErrado(){
        ArrayList data = new ArrayList<>();
        data.add("1991/12/30 23:56:30");
        TipoColunaSqlDateTime dateTime = TipoColunaSqlDateTime.YEAR;
        Assertions.assertFalse( dateTime.verificarCelula(data,0));
    }



}
