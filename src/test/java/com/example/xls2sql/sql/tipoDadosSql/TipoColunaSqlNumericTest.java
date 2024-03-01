package com.example.xls2sql.sql.tipoDadosSql;


import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
/**Classe que faz testes automatizados no enum TipoColunaSqlNumeric.
 * @author HalanSilva
 * @see TipoColunaSqlNumeric */
public class TipoColunaSqlNumericTest {


    /**Método que verifica se somente as constantes Bit e Decimal do enum retornam true quando chamado o
     * método aceitaNumeroElementos.*/
    @Test
    public void aceitaDadosElementosRetornaTrueSomenteParaBitEDecimal(){
        boolean aceitaNumeroElementos;
        ArrayList<TipoColunaSqlNumeric> resultado = new ArrayList<>();

        for (TipoColunaSqlNumeric numeric: TipoColunaSqlNumeric.values()){
            aceitaNumeroElementos = numeric.aceitaNumeroElementos();
            if (aceitaNumeroElementos){
                resultado.add(numeric);
            }
        }
        Assertions.assertEquals("[DECIMAL, BIT]",resultado.toString());
    }

    /**Método que verifica se todas as constantes do enum retornam false quando chamado o método verificarCelula
     * para um arrayList com tamanho maior a 1.*/
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

    /**Método que verifica se todas as constantes do enum retornam true quando chamado o método verificarCelula
     * para um arrayList de tamanho 1 que guarda uma string com "null" ou um espaçamento simples;*/
    @Test
    public void retornaTrueSempreQueCelulaEstaSalvoStringNullOuEspacoSimples(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("null");
        for (TipoColunaSqlNumeric tipoDadosSql : TipoColunaSqlNumeric.values()){
            Assertions.assertEquals(1, celula.size());
            Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
        }

        celula.set(0," ");
        for (TipoColunaSqlNumeric tipoDadosSql : TipoColunaSqlNumeric.values()){
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
        for (TipoColunaSqlNumeric tipoDadosSql : TipoColunaSqlNumeric.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,0));
        }

        celula.clear();
        celula.add(" ");
        celula.add("null");
        for (TipoColunaSqlNumeric tipoDadosSql : TipoColunaSqlNumeric.values()){
            Assertions.assertEquals(2, celula.size());
            Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,0));
        }

    }

//  DECIMAL
    /**Método que verifica se a constante Decimal retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número menor que o permitido pelos parâmetros
     * da coluna. */
    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroComTamanhoMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("223");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    /**Método que verifica se a constante Decimal retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual o máximo permitido pelos parâmetros
     * da coluna. */
    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroComTamanhoIgualPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("12345");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    /**Método que verifica se a constante Decimal retorna false quando chamado o método verificarCelula que tem
     * como parâmetro um arraylist contendo uma única string com um número maior que o permitido pelos
     * parâmetros da coluna. */
    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroComTamanhoMaiorPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("123456");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5));
    }

    /**Método que verifica se a constante Decimal retorna false quando chamado o método verificarCelula que tem
     * como parâmetro um arraylist contendo uma única string com um número tendo números após a vírgula
     * quando não permitido pelos parâmetros da coluna. */
    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroComVirgulaQuandoNumeroElementoNaoPermite(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("22.252");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5));
    }

    /**Método que verifica se a constante Decimal retorna true quando chamado o método verificarCelula que tem
     * como parâmetro um arraylist contendo uma única string com um número tendo números após a vírgula igual o
     * limite permitido pelos parâmetros da coluna. */
    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoIgualOPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("223.25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5.2));
    }

    /**Método que verifica se a constante Decimal retorna true quando chamado o método verificarCelula que tem
     * como parâmetro um arraylist contendo uma única string com um número tendo números após a vírgula menor que o
     * permitido pelos parâmetros da coluna. */
    @Test
    public void retornaTrueAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoMenorQuePermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("4223.5");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5.2));
    }

    /**Método que verifica se a constante Decimal retorna false quando chamado o método verificarCelula que tem
     * como parâmetro um arraylist contendo uma única string com um número tendo números após a vírgula igual o
     * limite permitido pelos parâmetros da coluna. */
    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroPosVirgulaComTamanhoMaiorQuePermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("223.252");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,6.2));
    }

    /**Método que verifica se a constante Decimal retorna false quando chamado o método verificarCelula que tem
     * como parâmetro um arraylist contendo uma única string com um número tendo mais números após a vírgula
     * do que o permitido pelos parâmetros da coluna. */
    @Test
    public void retornaFalseAoDecimalVerificarCelulaComNumeroTotalComTamanhoMaiorQuePermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("223.252");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.DECIMAL;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5.3));
    }

//    BIT

    /**Método que verifica se a constante Bit retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número binário de tamanho um quando o parâmetro
     * numeroElementos igual a zero. */
@Test
public void retornaTrueAoBitVerificarCelulaComNumeroComTamanhoIgualUmQuandoumeroElementosIgualZero(){
    ArrayList<String>  celula = new ArrayList<>();
    celula.add("1");
    TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
    Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
}

    /**Método que verifica se a constante Bit retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número binário de tamanho maior que um quando o parâmetro
     * numeroElementos igual a zero. */
    @Test
    public void retornaTrueAoBitVerificarCelulaComNumeroComTamanhoMaiorUmQuandoumeroElementosIgualZero(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("01");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante Bit retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número binário de tamanho menor que o permitido
     * pelos parâmetros da coluna. */
    @Test
    public void retornaTrueAoBitVerificarCelulaComNumeroComTamanhoMenorPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("010101");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante Bit retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número binário de tamanho igual o permitido
     * pelos parâmetros da coluna. */
    @Test
    public void retornaTrueAoBitVerificarCelulaComNumeroComTamanhoIgualPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("01010101");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante Bit retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número binário de tamanho maior que o permitido
     * pelos parâmetros da coluna. */
    @Test
    public void retornaFalseAoBitVerificarCelulaComNumeroComTamanhoMaiorPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("010101001");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante Bit retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número não binário. */
    @Test
    public void retornaFalseAoBitVerificarCelulaComNumeroComFormatoIncorreto(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("010235");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));

        celula.clear();
        celula.add("2");
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }


    //    TINYINT

    /**Método que verifica se a constante Tinyint retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número entre -128 e 127. */
    @Test
    public void retornaTrueAoTinyintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante Tinyint retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a 127. */
    @Test
    public void retornaTrueAoTinyintVerificarCelulaComMaiorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("127");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante Tinyint retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número maior que 127. */
    @Test
    public void retornaFalseAoTinyintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("128");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }
    /**Método que verifica se a constante Tinyint retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a -128. */
    @Test
    public void retornaTrueAoTinyintVerificarCelulaComMenorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-128");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante Tinyint retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número menor que -128. */
    @Test
    public void retornaFalseAoTinyintVerificarCelulaComNumeroMenorQuePermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-129");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.TINYINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    SMALLINT

    /**Método que verifica se a constante SmallInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número entre -32.768 e 32.767. */
    @Test
    public void retornaTrueAoSmallintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante SmallInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a 32.767. */
    @Test
    public void retornaTrueAoSmallintVerificarCelulaComMaiorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("32767");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante SmallInt retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número maior que 32.767. */
    @Test
    public void retornaFalseAoSmallintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("32768");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante SmallInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a -32.768. */
    @Test
    public void retornaTrueAoSmallintVerificarCelulaComNumeroMenorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-32768");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante SmallInt retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número menor que -32.768. */
    @Test
    public void retornaFalseAoSmallintVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-32769");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.SMALLINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    MEDIUMINT

    /**Método que verifica se a constante MediumInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número entre -8.388.608 e 8.388.607. */
    @Test
    public void retornaTrueAoMediumintVerificarCelulaComNumeroDentroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante MediumInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a 8.388.607. */
    @Test
    public void retornaTrueAoMediumintVerificarCelulaComMaiorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("8388607");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante MediumInt retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número maior que 8.388.607. */
    @Test
    public void retornaFalseAoMediumintVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("8388608");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante MediumInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a -8.388.608. */
    @Test
    public void retornaTrueAoMediumintVerificarCelulaComMenorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-8388608");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante MediumInt retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número menor que -8.388.608. */
    @Test
    public void retornaFalseAoMediumintVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-8388609");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.MEDIUMINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    INT

    /**Método que verifica se a constante Int retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número entre -2.147.483.648 e 2.147.483.647. */
    @Test
    public void retornaTrueAoIntVerificarCelulaComNumeroDentroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante Int retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a 2.147.483.647. */
    @Test
    public void retornaTrueAoIntVerificarCelulaComMaiorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("2147483647");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante Int retorna false quando chamado o método verificarCelula somente se a
     * arraylist de parametro conter uma única string com um número maior que 2.147.483.647. */
    @Test
    public void retornaFalseAoIntVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("2147483648");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante Int retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a -2.147.483.648. */
    @Test
    public void retornaTrueAoIntVerificarCelulaComMenorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-2147483648");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante Int retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número menor que -2.147.483.648. */
    @Test
    public void retornaFalseAoIntVerificarCelulaComNumeroAbaixoPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-2147483649");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.INT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }


    //    BIGINT

    /**Método que verifica se a constante BigInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número entre -9.223.372.036.854.775.808 e
     * 9.223.372.036.854.775.807. */
    @Test
    public void retornaTrueAoBigIntVerificarCelulaComNumeroDentroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("25");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante BigInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a 9.223.372.036.854.775.807. */
    @Test
    public void retornaTrueAoBigIntVerificarCelulaComMaiorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add(Long.toString(Long.MAX_VALUE));
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante BigInt retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número maior que 9.223.372.036.854.775.807. */
    @Test
    public void retornaFalseAoBigIntVerificarCelulaComNumeroAcimaPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("9223372036854775808");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante BigInt retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número igual a -9.223.372.036.854.775.808. */
    @Test
    public void retornaTrueAoBigIntVerificarCelulaComMenorNumeroPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add(Long.toString(Long.MIN_VALUE));
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,8));
    }

    /**Método que verifica se a constante BigInt retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um número menor que -9.223.372.036.854.775.808. */
    @Test
    public void retornaFalseAoBigIntVerificarCelulaComNumeroNegativoAbaixoPermitido(){
        ArrayList<String>  celula = new ArrayList<>();
        celula.add("-9223372036854775809");
        TipoColunaSql tipoColunaSql = TipoColunaSqlNumeric.BIGINT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,8));
    }









}
