package com.example.xls2sql.sql.tipoDadosSql;


import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TipoColunaSqlNumericTest {



    @Test
    public void metodoAceitaDadosElementosRetornaTrueSomenteParaBitEDecimal(){
        boolean aceitaNumeroElementos = TipoColunaSqlNumeric.INT.aceitaNumeroElementos();
        ArrayList<TipoColunaSqlNumeric> resultado = new ArrayList<>();

        for (TipoColunaSqlNumeric numeric: TipoColunaSqlNumeric.values()){
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
        for (TipoColunaSqlNumeric tipoDadosSql : TipoColunaSqlNumeric.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
        }

    }

//  DECIMAL
    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroComTamanhoMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("223");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroComTamanhoIgualPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("12345");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroComTamanhoMaiorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("123456");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroComVirgulaQuandoNumeroElementoNaoPermite(){
        ArrayList celula = new ArrayList<>();
        celula.add("22.252");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoIqualPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("223.25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5.2));
    }

    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoMenorQuePermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("4223.5");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5.2));
    }

    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoMaiorQuePermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("223.252");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,6.2));
    }
    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroTotalComTamanhoMaiorQuePermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("223.252");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5.3));
    }

//    BIT

    @Test
    public void retornaTrueAoBitVerificarCelulaComNumeroComTamanhoMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("010101");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaTrueAoBitVerificarCelulaComNumeroComTamanhoIgualPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("01010101");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoBitVerificarCelulaComNumeroComTamanhoMaiorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("010101001");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoBitVerificarCelulaComNumeroComFormatoIncorreto(){
        ArrayList celula = new ArrayList<>();
        celula.add("010235");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    TINYINT

    @Test
    public void retornaTrueAoTinyintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoTinyintVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("127");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoTinyintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("128");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoTinyintVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-128");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoTinyintVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-129");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    SMALLINT

    @Test
    public void retornaTrueAoSmallintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoSmallintVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("32767");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoSmallintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("32768");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoSmallintVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-32767");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoSmallintVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-32768");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    MEDIUMINT

    @Test
    public void retornaTrueAoMediumintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoMediumintVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("8388608");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoMediumintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("8388609");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoMediumintVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-8388608");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoMediumintVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-8388609");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    INT

    @Test
    public void retornaTrueAoIntVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoIntVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("2147483648");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoIntVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("2147483649");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoIntVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-2147483648");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoIntVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-2147483649");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    BIGINT

    @Test
    public void retornaTrueAoBigIntVerificarCelulaComNumeroDentroPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaTrueAoBigIntVerificarCelulaComNumeroMaximoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add(Long.toString(Long.MAX_VALUE));
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornaFalseAoBigIntVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("9223372036854775808");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }
    @Test
    public void retornaTrueAoBigIntVerificarCelulaComNumeroNegativoMinimoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add(Long.toString(Long.MIN_VALUE));
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    @Test
    public void retornaFalseAoBigIntVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("-9223372036854775809");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }









}
