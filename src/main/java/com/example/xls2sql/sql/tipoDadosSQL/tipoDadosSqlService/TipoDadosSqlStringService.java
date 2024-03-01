package com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService;

import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;

import java.io.File;
import java.util.ArrayList;


/**Classe que faz a checagem do conteúdo a ser inserido na linha sql para as colunas no qual os dados sql
 * é do tipo String.
 * @author Halan Silva
 * @see TipoColunaSQLString */
public class TipoDadosSqlStringService {


    /**Método que checa se o parâmetro está dentro das regras do tipo sql Char.
     * @param celula celula com a String a ser verificada.
     * @param numeroElementos Numero máximo de elementos que o usuário permite na coluna. Caso ele não ponha nada vem como 0,
     *                        e assim o método utilizara o tamanho padrão do sql.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaChar(String celula,double numeroElementos) {
        if (numeroElementos == 0){
            return celula.length() <= 1;

        }else {
            return celula.length() <= numeroElementos;
        }

    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql VarChar.
     * @param celula celula com a String a ser verificada.
     * @param numeroElementos Numero máximo de elementos que o usuário permite na coluna. Caso ele não ponha nada vem como 0,
     *                        e assim o método utilizara o tamanho padrão do sql.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaVarChar(String celula,double numeroElementos) {
        if (numeroElementos == 0){

            return celula.length() <= 65535;
        }else {
            return celula.length() <= numeroElementos;
        }

    }


    /**Método que checa se o parâmetro está dentro das regras do tipo sql TinyText.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaTinyText(String celula) {

        return celula.length() <= 255;
    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql Text.
     * @param celula celula com a String a ser verificada.
     * @param numeroElementos Tamanho máximo em bytes que o usuário permite na coluna. Caso ele não ponha nada vem como 0,
     *                        e assim o método utilizara o tamanho padrão do sql.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaText(String celula,double numeroElementos) {
        final byte[] bytesString = celula.getBytes();
        if (numeroElementos == 0){
            return bytesString.length <= 65535;
        }else {
            return bytesString.length <= numeroElementos;
        }
    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql MediumText.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaMediumText(String celula) {

        return celula.length() <= 16777215;

    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql LongText.
     * @param listaCelula lista de Strings contendo o texto a ser checado.
     * @return Retorna true se a listaCelula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaLongText(ArrayList<String> listaCelula) {

        long tamanhoString = 0;
        for (String text :listaCelula){
            tamanhoString += text.length();
        }

        return tamanhoString <= 4294967295L;


    }



    /**Método que checa se o arquivo endereçado no parâmetro está dentro das regras do tipo sql blob.
     * @param celula String com o endereço do arquivo a ser verificado.
     * @param numeroElementos Tamanho máximo em bytes que o usuário permite na coluna. Caso ele não ponha nada vem como 0,
     *                        e assim o método utilizara o tamanho padrão do sql.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaBlob(String celula, double numeroElementos) {
        File fileString = new File(celula);
        if (numeroElementos == 0){
            return fileString.isFile() && fileString.length() <= 65535;
        }else {
            return fileString.isFile() && fileString.length() <= numeroElementos;
        }
    }

    /**Método que checa se o arquivo endereçado no parâmetro está dentro das regras do tipo sql blob.
     * @param celula String com o endereço do arquivo a ser verificado.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaMediumBlob(String celula) {

        File fileString = new File(celula);
        return fileString.isFile() && fileString.length() <= 16777215;
    }

    /**Método que checa se o arquivo endereçado no parâmetro está dentro das regras do tipo sql blob.
     * @param celula String com o endereço do arquivo a ser verificado.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSQLString */
    public boolean verificarCelulaLongBlob(String celula) {

        File fileString = new File(celula);
        return fileString.isFile() && fileString.length() <= 4294967295L;
    }


}
