package com.example.xls2sql.sql.tipoDadosSql;


import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TipoDadosSqlNumericTest {



    @Test
    public void metodoAceitaDadosElementosRetornaTrueSomenteParaBitEDecimal(){
        boolean aceitaNumeroElementos = TipoDadosSqlNumeric.INT.aceitaNumeroElementos();
        ArrayList<TipoDadosSqlNumeric> resultado = new ArrayList<>();

        for (TipoDadosSqlNumeric numeric: TipoDadosSqlNumeric.values()){
            aceitaNumeroElementos = numeric.aceitaNumeroElementos();
            if (aceitaNumeroElementos){
                resultado.add(numeric);
            }
        }
        Assertions.assertEquals("[DECIMAL, BIT]",resultado.toString());
    }

    @Test
    public void retornaFalseSempreQueCelulaTemTamanhoMaiorQueUm(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("1");
        celula.add("0");
        for (TipoDadosSqlNumeric tipoDadosSql :TipoDadosSqlNumeric.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
        }

    }

//  DECIMAL
    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroComTamanhoMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("223");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroComTamanhoIgualPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("12345");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroComTamanhoMaiorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("123456");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroComVirgulaQuandoNumeroElementoNaoPermite(){
        ArrayList celula = new ArrayList<>();
        celula.add("22.252");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoIqualPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("223.25");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,5.2));
    }

    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoMenorQuePermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("4223.5");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,5.2));
    }

    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoMaiorQuePermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("223.252");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,6.2));
    }
    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroTotalComTamanhoMaiorQuePermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("223.252");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,5.3));
    }

//    BIT

    @Test
    public void retornaTrueAoBitVerificarCelulaComNumeroComTamanhoMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("010101");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaTrueAoBitVerificarCelulaComNumeroComTamanhoIgualPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("01010101");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoBitVerificarCelulaComNumeroComTamanhoMaiorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("010101001");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoBitVerificarCelulaComNumeroComFormatoIncorreto(){
        ArrayList celula = new ArrayList<>();
        celula.add("010235");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }


    //    TINYINT

    @Test
    public void retornaTrueAoTinyintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoTinyintVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("127");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoTinyintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("128");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.TINYINT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoTinyintVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-128");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoTinyintVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-129");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.TINYINT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }


    //    SMALLINT

    @Test
    public void retornaTrueAoSmallintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoSmallintVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("32767");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoSmallintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("32768");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.SMALLINT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoSmallintVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-32767");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoSmallintVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-32768");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.SMALLINT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }


    //    MEDIUMINT

    @Test
    public void retornaTrueAoMediumintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoMediumintVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("8388608");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoMediumintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("8388609");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.MEDIUMINT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoMediumintVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-8388608");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoMediumintVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-8388609");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.MEDIUMINT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }


    //    INT

    @Test
    public void retornaTrueAoIntVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.INT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoIntVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("2147483648");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.INT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoIntVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("2147483649");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.INT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoIntVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-2147483648");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.INT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoIntVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-2147483649");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.INT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }


    //    BIGINT

    @Test
    public void retornaTrueAoBigIntVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoBigIntVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add(Long.toString(Long.MAX_VALUE));
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoBigIntVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("9223372036854775808");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIGINT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoBigIntVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add(Long.toString(Long.MIN_VALUE));
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoBigIntVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-9223372036854775809");
        TipoDadosSql tipoDadosSql = TipoDadosSqlNumeric.BIGINT;
        Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
    }









}
