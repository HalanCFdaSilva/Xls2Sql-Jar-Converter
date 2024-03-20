package com.example.xls2sql.sql.tipoDadosSql.tipoDadosSqlService;

import com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService.TipoDadosSqlDateTimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TipoDadosSqlDateTimeServiceTest {


    @Test
    public void retornaTrueQuandoYearNoFormatoCerto(){
        String data = "1991";

        TipoDadosSqlDateTimeService dateTimeService = new TipoDadosSqlDateTimeService();
        Assertions.assertTrue( dateTimeService.verificarCelulaYear(data));
    }

    /**Método que verifica se a constante Year retorna false quando chamado o método verificarCelula e pondo como
     * parâmetro um arraylist contendo uma única string com o texto fora do formato permitido pela constânte. */
    @Test
    public void retornaFalseQuandoChecaYearRecebendoStringNoFormatoErrado(){
        String data = "1991/12/30 23:56:30";

        TipoDadosSqlDateTimeService dateTimeService = new TipoDadosSqlDateTimeService();
        Assertions.assertFalse( dateTimeService.verificarCelulaYear(data));
    }

}
