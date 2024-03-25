package com.example.xls2sql.sql.tipoDadosSql.tipoDadosSqlService;

import com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService.TipoDadosSqlDateTimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TipoDadosSqlDateTimeServiceTest {

    /**Método que verifica se o método verificarCelulaYear retorna true quando recebe uma string com o texto dentro do formato permitido. */
    @Test
    public void retornaTrueQuandoYearNoFormatoCerto(){
        String data = "1991";

        TipoDadosSqlDateTimeService dateTimeService = new TipoDadosSqlDateTimeService();
        Assertions.assertTrue( dateTimeService.verificarCelulaYear(data));
    }

    /**Método que verifica se o método verificarCelulaYear retorna false quando recebe uma string com o texto fora do formato permitido. */
    @Test
    public void retornaFalseQuandoChecaYearRecebendoStringNoFormatoErrado(){
        String data = "1991/12/30 23:56:30";

        TipoDadosSqlDateTimeService dateTimeService = new TipoDadosSqlDateTimeService();
        Assertions.assertFalse( dateTimeService.verificarCelulaYear(data));
    }

}
