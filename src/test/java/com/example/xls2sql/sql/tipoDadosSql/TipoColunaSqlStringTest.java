package com.example.xls2sql.sql.tipoDadosSql;

import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TipoColunaSqlStringTest {
//.substring(0,16777217)
//

    @Test
    public void metodoAceitaDadosElementosRetornaTrueSomenteParaEnumQuePermiteNumeroElementos(){
        boolean aceitaNumeroElementos = TipoColunaSQLString.LONGBLOB.aceitaNumeroElementos();
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

    @Test
    public void retornaFalseSempreQueCelulaTemTamanhoMaiorQueUmComExcessaoDeLongText(){
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


//  CHAR
    @Test
    public void retornaTrueAoCharVerificarCelulaComTamanhoDaStringIgualPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("a");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

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

    @Test
    public void retornaTrueAoCharVerificarCelulaComTamanhoDaStringMenorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaTrueAoCharVerificarCelulaComTamanhoDaStringIgualPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asada");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaFalseAoCharVerificarCelulaComTamanhoDaStringMaiorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asadab");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.CHAR;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5));
    }



    //  VARCHAR
    @Test
    public void retornaTrueAoVarcharVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

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

    @Test
    public void retornaTrueAoVarcharVerificarCelulaComTamanhoDaStringMenorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaTrueAoVarcharVerificarCelulaComTamanhoDaStringIgualPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asada");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,5));
    }

    @Test
    public void retornaFalseAoVarcharVerificarCelulaComTamanhoDaStringMaiorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asadab");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.VARCHAR;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,5));
    }



    //  TINYTEXT
    @Test
    public void retornaTrueAoTinytextVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TINYTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

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
    @Test
    public void retornaTrueAoTextVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

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

    @Test
    public void retornaTrueAoTextVerificarCelulaComTamanhoDaStringMenorPermitidoNumeroElementos(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.TEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,3855));
    }

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
    @Test
    public void retornaTrueAoMediumtextVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList<String> celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

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
    @Test
    public void retornaTrueAoLongtextVerificarCelulaComTamanhoDaStringMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("asa");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.LONGTEXT;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }

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

    @Test
    public void retornaTrueAoBlobVerificarCelulaComTamanhoMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto pequeno.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }


    @Test
    public void retornaFalseAoBlobVerificarCelulaComTamanhoMaiorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,0));
    }

    @Test
    public void retornafalseAoBlobNaoEncontrarArquivo(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.jpeg");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }

    @Test
    public void retornaTrueAoBlobVerificarCelulaComTamanhoMenorPermitidoNumeroElementos(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto pequeno.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,3855));
    }

    @Test
    public void retornaFalseAoBlobVerificarCelulaComTamanhoMaiorPermitidoNumeroElementos(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.BLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }

    //  MEDIUMBLOB

    @Test
    public void retornaTrueAoMediumBlobVerificarCelulaComTamanhoMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMBLOB;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }


    @Test
    public void retornafalseAoMediumBlobNaoEncontrarArquivo(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.jpeg");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMBLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }

    //  LONGBLOB

    @Test
    public void retornaTrueAoLongBlobVerificarCelulaComTamanhoMenorPermitido(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.txt");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.LONGBLOB;
        Assertions.assertTrue(tipoColunaSql.verificarCelula(celula,0));
    }



    @Test
    public void retornafalseAoLongBlobNaoEncontrarArquivo(){
        ArrayList celula = new ArrayList<>();
        celula.add("src/main/resources/texto grande.jpeg");
        TipoColunaSql tipoColunaSql = TipoColunaSQLString.MEDIUMBLOB;
        Assertions.assertFalse(tipoColunaSql.verificarCelula(celula,3855));
    }





}


