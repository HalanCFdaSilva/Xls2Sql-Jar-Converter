package com.example.xls2sql.sql.celulaLinhaSql;

import com.example.xls2sql.sql.domain.TipoDados;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**Classe de apoio da classe {@link com.example.xls2sql.sql.domain.CelulaLinhaSql CelulaLinhaSql} que faz algumas
 * operações necessárias, mas que estão fora do escopo da classe CelulaLinhaSql.
 * @author Halan Silva
 * @see com.example.xls2sql.sql.domain.CelulaLinhaSql*/
public abstract class CelulaLinhaSqlService {



    /** Verifica se a classe do TipoDadosSql é do tipo {@link TipoColunaSqlDateTime TipoColunaSqlDateTime}
     * , aonde sendo é feito uma verificação se a data está no formato YYYY/MM/DD e caso não for modifica a String para o
     * formato aceito pelo Sql.
     * @param texto Arraylist com o texto a ser modificado.
     * @param tipoColunaSql tipoDados da celula vinculada a String
     * @return retorna a String após passar pelos testes de verificação podendo ser modificada ou não conforme o necessário.*/
    public static String formatarCelulaDateTime(String texto, TipoColunaSql tipoColunaSql){

        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy/MM/dd";

        if (tipoColunaSql.getClass().getSimpleName().equals("TipoColunaSqlDateTime")){
            final String REGEX_FORMATO_ERRADO = "^(3[01]|[12][0-9]|0?[1-9])(/|-)(1[0-2]|0?[1-9])\\2([1-9])[0-9]{3}";
            if (texto.matches(REGEX_FORMATO_ERRADO)){


                String newDateString;

                SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                Date d;
                try {
                    d = sdf.parse(texto);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                sdf.applyPattern(NEW_FORMAT);
                newDateString = sdf.format(d);
                texto = newDateString;
            }
        }


        return texto;
    }


    /**Método que checa se os dados da célula estão dentro das regras da coluna e devolve o resultado.
     * @param listaCelulaAdicionar lista com toda a informação da célula do arquivo xls.
     * @param tipoDados classe com todas as regras da coluna.
     * @return retorna true se o Arraylist estiver dentro das regras e false se estiver fora das regras.*/
    public static boolean checarCelulaAIncluir(ArrayList<String> listaCelulaAdicionar, TipoDados tipoDados) {
        return tipoDados.tipoColunaSql().verificarCelula(listaCelulaAdicionar,tipoDados.numeroElementos());
    }
}