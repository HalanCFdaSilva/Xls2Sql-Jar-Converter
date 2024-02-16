package com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

public class TipoDadosSqlDateTimeService {

    final String REGEX_TIME;
    final String REGEX_DATE;
    boolean possuiZoneId;

    public TipoDadosSqlDateTimeService() {
        this.REGEX_TIME = "(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d";
        this.REGEX_DATE = "^[1-9][0-9]{3}\\/(((0[13578]|(10|12))\\/(0[1-9]|[1-2][0-9]|3[0-1]))|(02\\/(0[1-9]|[1-2][0-9]))|((0[469]|11)\\/(0[1-9]|[1-2][0-9]|30)))";

    }

    public boolean verificarCelulaDate(String celula) {
        return celula.matches(REGEX_DATE);
    }

    public boolean verificarCelulaTime(String celula) {
        String regexCaseTime = "^-?(?:[0-7]\\d\\d|8[0-2]\\d|83[0-8]):[0-5]\\d:[0-5]\\d$";
        return celula.matches(regexCaseTime);

    }

    public boolean verificarCelulaYear(String celula) {

        if (celula.matches("^[0-9]{4}$")){
            int ano = Integer.parseInt(celula);
            if (ano == 0000|| (ano >= 1901 && ano <= 2155)){
                return true;
            }
        }
        return false;

    }

    public boolean verificarCelulaDateTime(String celula) {
        String  regexDateTime= REGEX_DATE +" ?" + REGEX_TIME + "$";
        return celula.matches(regexDateTime);
    }

    public boolean verificarCelulaTimeStamp(String celula) {
        String  regexDateTime= REGEX_DATE +" ?" + REGEX_TIME;

        String elementoString = this.ChecarZoneId(celula);

        if (elementoString.matches(regexDateTime)||celula.matches(regexDateTime)){

            return this.verificarCelulaTimeStampFormatoDataHora(celula);

        }else{

            return verificarCelulaTimeStampFormatoNaoDataHora(celula);

        }


    }

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

    private boolean verificarCelulaTimeStampFormatoDataHora(String celula) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss[ VV]");

        ZonedDateTime dateTimeElementoSql;
        if (this.possuiZoneId){
            dateTimeElementoSql = ZonedDateTime.parse(celula,formatter);
        }else {
            dateTimeElementoSql = ZonedDateTime.parse(celula + " " + ZoneId.systemDefault(),formatter);
        }

        ZonedDateTime zonedDateTimeLimit = ZonedDateTime.parse("2038/01/09 03:14:07 UTC",formatter);
        if (dateTimeElementoSql.toEpochSecond() >= 0 && zonedDateTimeLimit.isAfter(dateTimeElementoSql)){
            return true;
        }
        return false;
    }

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
