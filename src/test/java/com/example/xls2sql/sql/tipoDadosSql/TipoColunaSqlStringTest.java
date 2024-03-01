package com.example.xls2sql.sql.tipoDadosSql;

import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**Classe que faz testes automatizados no enum TipoColunaSqlString.
 * @author HalanSilva
 * @see TipoColunaSQLString */
public class TipoColunaSqlStringTest {


    /**Método que verifica se somente as constantes CHAR, VARCHAR, TEXT e BLOB do enum retornam true quando chamado o
     * método aceitaNumeroElementos.*/
    @Test
    public void aceitaDadosElementosRetornaTrueSomenteParaEnumQuePermiteNumeroElementos(){
        boolean aceitaNumeroElementos;
        ArrayList<TipoColunaSQLString> resultadoTrue = new ArrayList<>();
        ArrayList<TipoColunaSQLString> resultadoFalse = new ArrayList<>();

        for (TipoColunaSQLString string: TipoColunaSQLString.values()){
            aceitaNumeroElementos = string.aceitaNumeroElementos();
            if (aceitaNumeroElementos){
                resultadoTrue.add(string);
            }else{
                resultadoFalse.add(string);
            }
        }
        String resultadoEsperadoTrue = "[CHAR, VARCHAR, TEXT, BLOB]";
        String resultadoEsperadoFalse = "[TINYTEXT, MEDIUMTEXT, LONGTEXT, MEDIUMBLOB, LONGBLOB]";
        Assertions.assertEquals(resultadoEsperadoTrue,resultadoTrue.toString());
        Assertions.assertEquals(resultadoEsperadoFalse,resultadoFalse.toString());
    }

    /**Método que verifica se todas as constantes do enum retornam false, com exceção do LONGTEXT, quando chamado o método verificarCelula
     * para um arrayList com tamanho maior a 1.*/
    @Test
    public void retornaFalseSempreQueCelulaTemTamanhoMaiorQueUmComExcecaoDeLongText(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("a");
        celula.add("b");
        for (TipoColunaSQLString tipoDadosSql : TipoColunaSQLString.values()){
            if (tipoDadosSql != TipoColunaSQLString.LONGTEXT){
                Assertions.assertEquals(2, celula.size());
                Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
            }else {
                Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
            }
        }

    }

    /**Método que verifica se todas as constantes do enum retornam true quando chamado o método verificarCelula
     * para um arrayList de tamanho 1 que guarda uma string com "null" ou um espaçamento simples;*/
    @Test
    public void retornaTrueSempreQueCelulaEstaSalvoStringNullOuEspacoSimples(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("null");
        for (TipoColunaSQLString tipoDadosSql : TipoColunaSQLString.values()){
            Assertions.assertEquals(1, celula.size());
            Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
        }

        celula.set(0," ");
        for (TipoColunaSQLString tipoDadosSql : TipoColunaSQLString.values()){
            Assertions.assertEquals(1, celula.size());
            Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,0));
        }

    }

    /**Método que verifica se todas as constantes do enum, com exceção da LONGTEXT, retornam false quando chamado o método verificarCelula
     * para um arrayList de tamanho maior a 1 e que guarde uma string com "null" ou um espaçamento simples*/
    @Test
    public void retornaFalseSempreQueCelulaEstaSalvoStringNullOuEspacoSimplesMasTamanhoMaiorA1(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("null");
        celula.add(" ");
        for (TipoColunaSQLString tipoDadosSql : TipoColunaSQLString.values()){
            if (tipoDadosSql != TipoColunaSQLString.LONGTEXT){
                Assertions.assertEquals(2, celula.size());
                Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
            }else {
                Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
            }
        }

        celula.clear();
        celula.add(" ");
        celula.add("null");
        for (TipoColunaSQLString tipoDadosSql : TipoColunaSQLString.values()){
            if (tipoDadosSql != TipoColunaSQLString.LONGTEXT){
                Assertions.assertEquals(2, celula.size());
                Assertions.assertFalse(tipoDadosSql.verificarCelula(celula,8));
            }else {
                Assertions.assertTrue(tipoDadosSql.verificarCelula(celula,8));
            }
        }

    }


//  CHAR
    /**Método que verifica se a constante CHAR retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com tamanho um quando o segundo parâmetro é zero. */
    @Test
    public void retornaTrueAoCharVerificarCelulaComTamanhoDaStringIgualPermitidoPadrãoQuandoNumeroElementosIgualZero(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("a");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante CHAR retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho maior que um quando o segundo parâmetro é zero. */
    @Test
    public void retornaFalseAoCharVerificarCelulaComTamanhoDaStringMaiorPermitido(){
        ArrayList<String> celula = new ArrayList<>();

        String texto = "";
        for (int i = 1; i <= 256;i++){
            texto += "a";
        }
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante CHAR retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho menor que o parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaTrueAoCharVerificarCelulaComTamanhoDaStringMenorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    /**Método que verifica se a constante CHAR retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho igual o parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaTrueAoCharVerificarCelulaComTamanhoDaStringIgualPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asada");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    /**Método que verifica se a constante CHAR retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho maior que o parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaFalseAoCharVerificarCelulaComTamanhoDaStringMaiorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asadab");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5));
    }



    //  VARCHAR
    /**Método que verifica se a constante VARCHAR retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho menor que 65.535
     * quando o segundo parâmetro é zero. */
    @Test
    public void retornaTrueAoVarcharVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante VARCHAR retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho igual a 65.535
     * quando o segundo parâmetro é zero. */
    @Test
    public void retornaTrueAoVarcharVerificarCelulaComTamanhoDaStringIgualPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        String texto = "";
        for (int i = 1;i<=65535;i++){
            texto += "a";
        }
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante VARCHAR retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho maior que 65.535
     * quando o segundo parâmetro é zero. */
    @Test
    public void retornaFalseAoVarcharVerificarCelulaComTamanhoDaStringMaiorPermitido(){
        ArrayList<String> celula = new ArrayList<>();

        String texto = "";
        for (int i = 1; i <= 65536;i++){
            texto += "a";
        }
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante VARCHAR retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho menor que o parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaTrueAoVarcharVerificarCelulaComTamanhoDaStringMenorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    /**Método que verifica se a constante VARCHAR retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho igual ao parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaTrueAoVarcharVerificarCelulaComTamanhoDaStringIgualPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asada");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    /**Método que verifica se a constante VARCHAR retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho maior que o parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaFalseAoVarcharVerificarCelulaComTamanhoDaStringMaiorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asadab");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5));
    }



    //  TINYTEXT
    /**Método que verifica se a constante TINYTEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho menor que 255. */
    @Test
    public void retornaTrueAoTinytextVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TINYTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante TINYTEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho igual a 255. */
    @Test
    public void retornaTrueAoTinytextVerificarCelulaComTamanhoDaStringIgualPermitido(){
        ArrayList<String> celula = new ArrayList<>();

        String texto = "";
        for (int i = 1;i<=255;i++){
            texto += "a";
        }
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TINYTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante TINYTEXT retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho maior que 255. */
    @Test
    public void retornaFalseAoTinytextVerificarCelulaComTamanhoDaStringMaiorPermitido(){
        ArrayList<String> celula = new ArrayList<>();

        String texto = "";
        for (int i = 1; i <= 256;i++){
            texto += "a";
        }
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TINYTEXT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }

    //  TEXT
    /**Método que verifica se a constante TEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto onde o tamanho em bytes é menor que 65.535
     * quando o segundo parâmetro é zero. */
    @Test
    public void retornaTrueAoTextVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante TEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto onde o tamanho em bytes é igual a 65.535
     * quando o segundo parâmetro é zero. */
    @Test
    public void retornaTrueAoTextVerificarCelulaComTamanhoDaStringIgualPermitido(){
        ArrayList<String> celula = new ArrayList<>();

        String texto = "";
        for (int i = 1;i<=65535;i++){
            texto += "a";
        }
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante TEXT retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto onde o tamanho em bytes é maior que 65.535
     * quando o segundo parâmetro é zero. */
    @Test
    public void retornaFalseAoTextVerificarCelulaComTamanhoDaStringMaiorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        String texto = "";
        for (int i = 1; i <= 65536;i++){
            texto += "a";
        }
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TEXT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante TEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto onde o tamanho em bytes é menor ao parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaTrueAoTextVerificarCelulaComTamanhoDaStringMenorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,3855));
    }

    /**Método que verifica se a constante TEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto onde o tamanho em bytes é igual ao parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaTrueAoTextVerificarCelulaComTamanhoDaStringIgualPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        String texto = "";
        for (int i = 1; i <= 3855;i++){
            texto += "a";
        }
        celula.add(texto);

        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,3855));
    }

    /**Método que verifica se a constante TEXT retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto onde o tamanho em bytes é maior ao parâmetro
     * numeroElementos quando ele maior que zero. */
    @Test
    public void retornaFalseAoTextVerificarCelulaComTamanhoDaStringMaiorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        String texto = "asadab";
        for (int i = 1; i <= 3856;i++){
            texto += "a";
        }
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TEXT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }


    //  MEDIUMTEXT
    /**Método que verifica se a constante MEDIUMTEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho menor que 16.777.215. */
    @Test
    public void retornaTrueAoMediumtextVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante MEDIUMTEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho igual a 16.777.215. */
    @Test
    public void retornaTrueAoMediumtextVerificarCelulaComTamanhoDaStringIgualPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        String texto = "";
        for (long i = 1;i<=241;i++){
            texto += "a";
        }
        String celula2 =texto;
        for (int i = 2;i <= 221;i++){
            texto += celula2;
        }
        celula2 = texto;
        for (int i = 2;i <= 315;i++){
            texto += celula2;
        }
        System.out.println(texto.length());
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante MEDIUMTEXT retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho maior que 16.777.215. */
    @Test
    public void retornaFalseAoMediumtextVerificarCelulaComTamanhoDaStringMaiorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        String texto = "";
        for (long i = 1;i<=241;i++){
            texto += "a";
        }
        String celula2 =texto;
        for (int i = 2;i <= 221;i++){
            texto += celula2;
        }
        celula2 = texto;
        for (int i = 2;i <= 315;i++){
            texto += celula2;
        }
        texto += "a";
        celula.add(texto);
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMTEXT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }


    //  LONGTEXT
    /**Método que verifica se a constante LONGTEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho menor que 4.294.967.295. */
    @Test
    public void retornaTrueAoLongtextVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.LONGTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante LONGTEXT retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho igual a 4.294.967.295. */
    @Test
    public void retornaTrueAoLongtextVerificarCelulaComTamanhoDaStringIgualPermitido(){
        ArrayList<String> celula = new ArrayList<>();

        String texto = "";
        for (long i = 1;i<=241;i++){
            texto += "a";
        }
        String celula2 =texto;
        for (int i = 2;i <= 221;i++){
            texto += celula2;
        }
        celula2 = texto;
        for (int i = 2;i <= 315;i++){
            texto += celula2;
        }

        for (int i1 = 1; i1 <= 256; i1++ ) {
            celula.add(texto);

        }

        texto = "";
        for (long i = 1;i<=255;i++){
            texto += "a";
        }
        celula.add(texto);


        TipoColunaSql tipoColunaSql = TipoColunaSQLString.LONGTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante LONGTEXT retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com um texto com o tamanho maior que 4.294.967.295. */
    @Test
    public void retornaFalseAoLongtextVerificarCelulaComTamanhoDaStringMaiorPermitido(){
        ArrayList<String> celula = new ArrayList<>();

        String texto = "";
        for (long i = 1;i<=241;i++){
            texto += "a";
        }
        String celula2 =texto;
        for (int i = 2;i <= 221;i++){
            texto += celula2;
        }
        celula2 = texto;
        for (int i = 2;i <= 315;i++){
            texto += celula2;
        }

        for (int i1 = 1; i1 <= 256; i1++ ) {
            celula.add(texto);

        }
        texto = "";
        for (long i = 1;i<=256;i++){
            texto += "a";
        }
        celula.add(texto);


        TipoColunaSql tipoColunaSql = TipoColunaSQLString.LONGTEXT;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }

//  BLOB

    /**Método que verifica se a constante BLOB retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que existe e cujo arquivo tem
     * tamanho menor que 65.535 bytes quando o parâmetro numeroElementos é igual a zero. */
    @Test
    public void retornaTrueAoBlobVerificarCelulaComTamanhoMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto pequeno.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }


    /**Método que verifica se a constante BLOB retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que existe e cujo arquivo tem
     * tamanho maior que 65.535 bytes quando o parâmetro numeroElementos é igual a zero. */
    @Test
    public void retornaFalseAoBlobVerificarCelulaComTamanhoMaiorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }

    /**Método que verifica se a constante BLOB retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que não existe. */
    @Test
    public void retornaFalseAoBlobNaoEncontrarArquivo(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.jpeg");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }

    /**Método que verifica se a constante BLOB retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que existe e cujo arquivo tem
     * tamanho menor que o parâmetro numeroElementos. */
    @Test
    public void retornaTrueAoBlobVerificarCelulaComTamanhoMenorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto pequeno.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,3855));
    }

    /**Método que verifica se a constante BLOB retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que existe e cujo arquivo tem
     * tamanho maior que o parâmetro numeroElementos. */
    @Test
    public void retornaFalseAoBlobVerificarCelulaComTamanhoMaiorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }

    //  MEDIUMBLOB
    /**Método que verifica se a constante MEDIUMBLOB retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que existe e cujo arquivo tem
     * tamanho menor que 16.777.215 bytes. */
    @Test
    public void retornaTrueAoMediumBlobVerificarCelulaComTamanhoMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMBLOB;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }


    /**Método que verifica se a constante MEDIUMBLOB retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que não existe. */
    @Test
    public void retornaFalseAoMediumBlobNaoEncontrarArquivo(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.jpeg");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMBLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }

    //  LONGBLOB

    /**Método que verifica se a constante LONGBLOB retorna true quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que existe e cujo arquivo tem
     * tamanho menor que 4.294.967.295 bytes. */
    @Test
    public void retornaTrueAoLongBlobVerificarCelulaComTamanhoMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.LONGBLOB;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }



    /**Método que verifica se a constante LONGBLOB retorna false quando chamado o método verificarCelula que tem como
     * parâmetro um arraylist contendo uma única string com o endereço de um arquivo que não existe. */
    @Test
    public void retornaFalseAoLongBlobNaoEncontrarArquivo(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.jpeg");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMBLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }





}


