package com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService;

import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

/**Classe que faz a checagem do conteúdo a ser inserido na linha sql para as colunas no qual os dados sql
 * é do tipo DateTime.
 * @author Halan Silva
 * @see TipoColunaSqlDateTime */
public class TipoDadosSqlDateTimeService {

    /**Variável que guarda o Regex para String tem o formato de hora em 24 horas.*/
    final String REGEX_TIME;

    /**Variável que guarda o Regex que verifica se a String está no formato de ano bissexto.*/
    final String REGEX_DATE;

    /**Variável utilizada pelos métodos do TimeStamp que guarda se o conteúdo a ser inserido tem uma
     * zone id dentro dele.*/
    boolean possuiZoneId;

    /**Método que cria uma instância da classe e insere nas variáveis
     * REGEX_TIME e REGEX_DATE as suas respectivas String de Regex.*/
    public TipoDadosSqlDateTimeService() {
        this.REGEX_TIME = "(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d";
        this.REGEX_DATE = "^[1-9][0-9]{3}\\/(((0[13578]|(10|12))\\/(0[1-9]|[1-2][0-9]|3[0-1]))|(02\\/(0[1-9]|[1-2][0-9]))|((0[469]|11)\\/(0[1-9]|[1-2][0-9]|30)))";

    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql Date.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlDateTime */
    public boolean verificarCelulaDate(String celula) {
        return celula.matches(REGEX_DATE);
    }

    /**Método que checa se o parâmetro está dentro das regras do enum do tipo sql Time.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlDateTime */
    public boolean verificarCelulaTime(String celula) {
        String regexCaseTime = "^-?(?:[0-7]\\d\\d|8[0-2]\\d|83[0-8]):[0-5]\\d:[0-5]\\d$";
        return celula.matches(regexCaseTime);

    }
    /**Método que checa se o parâmetro está dentro das regras do tipo sql Year.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlDateTime */
    public boolean verificarCelulaYear(String celula) {

        if (celula.matches("^[0-9]{4}$")){
            int ano = Integer.parseInt(celula);
            return celula.equals("0000") || (ano >= 1901 && ano <= 2155);
        }
        return false;

    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql DateTime.
     * @param celula celula com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlDateTime */
    public boolean verificarCelulaDateTime(String celula) {
        String  regexDateTime= REGEX_DATE +" ?" + REGEX_TIME + "$";
        return celula.matches(regexDateTime);
    }

    /**Método que checa se o parâmetro está dentro das regras do tipo sql TimeStamp.
     * @param celula Parâmetro com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.
     * @see TipoColunaSqlDateTime */
    public boolean verificarCelulaTimeStamp(String celula) {
        String  regexDateTime= REGEX_DATE +" ?" + REGEX_TIME;

        String elementoString = this.ChecarZoneId(celula);

        if (elementoString.matches(regexDateTime)||celula.matches(regexDateTime)){

            return this.verificarCelulaTimeStampFormatoDataHora(celula);

        }else{

            return verificarCelulaTimeStampFormatoNaoDataHora(celula);

        }


    }

    /**Método interno utilizado pelo método verificarCelulaTimeStamp que checa se o parâmetro
     * não está no formato com data e hora, mas com algum código do sql que gera a data e a hora.
     * @param celula Parâmetro com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.*/
    private boolean verificarCelulaTimeStampFormatoNaoDataHora(String celula) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("CURRENT_TIMESTAMP");

        for (String textoSql: arrayList){

            if (celula.equalsIgnoreCase(textoSql)){
                return true;
            }
        }
        return false;
    }



    /**Método interno utilizado pelo método verificarCelulaTimeStamp que checa se o parâmetro que já está
     * no formato data e hora está dentro das regras do TimeStamp de data e hora.
     * @param celula Parâmetro com a String a ser verificada.
     * @return Retorna true se a celula estiver dentro das regras e false se estiver fora das regras.*/
    private boolean verificarCelulaTimeStampFormatoDataHora(String celula) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss[ VV]");

        ZonedDateTime dateTimeElementoSql;
        if (this.possuiZoneId){
            dateTimeElementoSql = ZonedDateTime.parse(celula,formatter);
        }else {
            dateTimeElementoSql = ZonedDateTime.parse(celula + " " + ZoneId.systemDefault(),formatter);
        }

        ZonedDateTime zonedDateTimeLimit = ZonedDateTime.parse("2038/01/09 03:14:07 UTC",formatter);
        return dateTimeElementoSql.toEpochSecond() >= 0 && zonedDateTimeLimit.isAfter(dateTimeElementoSql);
    }

    /**Método que checa se a String possui a zona do fuso horário e caso tenha retira o zona do fuso horário para outro método poder testar se o parâmetro
     * está no formato data e hora.
     * @param celula String com o texto0 a ser verificado se tem a zona de fuso horário.
     * @return retorna uma String sem a zona do fuso horário.*/
    private String ChecarZoneId(String celula){
        Set<String> zoneIds= ZoneId.getAvailableZoneIds();
        String elementoString = "none";
        this.possuiZoneId = false;
        for (String zone : zoneIds) {
            if (celula.contains(zone)){
                int numeroZoneId = celula.indexOf(zone);
                elementoString = celula.substring(0,numeroZoneId).strip();
                this.possuiZoneId = true;
                break;
            }
        }
        return elementoString;
    }
}
