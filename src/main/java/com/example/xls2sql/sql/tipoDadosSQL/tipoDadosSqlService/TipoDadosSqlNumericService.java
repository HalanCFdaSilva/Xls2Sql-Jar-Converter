package com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService;

import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;

/**Classe que faz a checagem do conteúdo a ser inserido na linha sql para as colunas no qual os dados sql
 * é do tipo Numeric.
 * @author Halan Silva
 * @see TipoColunaSqlNumeric */
public class TipoDadosSqlNumericService {

    /**Tamanho limite da coluna aonde se localiza a célula a ser verificada.*/
    double numeroElementos;

    /**Numero elementos pós virgula permitidos pelo tipo decimal.
     * <p>A variável é retirada da variável numeroElementos onde se retira o número pós virgula dele.</p>*/
    int numeroElementosPosVirgula;

    /**Método que cria uma instância da classe e insere nas variáveis
     * numeroElementos e numeroElementosPosVirgula os seus respectivos dados.
     * @param numeroElementos  Parâmetro com o tamanho máximo de elementos permitido pelo usuário.*/
    public TipoDadosSqlNumericService(double numeroElementos) {
        this.numeroElementos = numeroElementos;

        String numeroElementosString = Double.toString(numeroElementos);

        int posicaoVirgulaElemento = numeroElementosString.indexOf(".");
        String textoPosVirgula = numeroElementosString.substring(posicaoVirgulaElemento + 1);
        numeroElementosPosVirgula = Integer.parseInt(textoPosVirgula);



    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql Bit.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlNumeric */
    public boolean verificarCelulaBit(String celula) {

        try{
            Integer.parseInt(celula,2);
            return celula.length() <= numeroElementos;
        }catch (Exception e){
            return false;

        }
    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql TinyInt.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlNumeric */
    public boolean verificarCelulaTinyInt(String celula) {
        int valorCelula = Integer.parseInt(celula);

        return valorCelula <= 127 && valorCelula >= -128;

    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql SmallInt.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlNumeric */
    public boolean verificarCelulaSmallInt(String celula) {
        int valorCelula = Integer.parseInt(celula);
        return valorCelula <= 32767 && valorCelula >= -32767;
    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql MediumInt.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlNumeric */
    public boolean verificarCelulaMediumInt(String celula) {

        int valorCelula = Integer.parseInt(celula);
        return valorCelula <= 8388608 && valorCelula >= -8388608;

    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql BigInt.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlNumeric */
    public boolean verificarCelulaBigInt(String celula) {
        try {

            long valorCelula = Long.parseLong(celula);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql Int.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlNumeric */
    public boolean verificarCelulaInt(String celula) {
        if (!celula.contains(".")){
            double valorCelula = Double.parseDouble(celula);
            long valorAVerificar = Long.parseLong("2147483648");
            return valorCelula <= valorAVerificar && valorCelula >= -2147483648;
        }
        return false;

    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql Decimal.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlNumeric */
    public boolean verificarCelulaDecimal (String celula) {
        if (celula.contains(".")){
            int posicaoVirgula = celula.indexOf(".");
            String stringNumeroPosVirgula = celula.substring(posicaoVirgula + 1, celula.length());
            int numeroPosVirgula = stringNumeroPosVirgula.length();





            celula = celula.replace(".","");
            if (celula.length() <= numeroElementos){
                return numeroElementosPosVirgula >= numeroPosVirgula;


            }
        } else return celula.length() <= numeroElementos;

        return false;

    }

}
