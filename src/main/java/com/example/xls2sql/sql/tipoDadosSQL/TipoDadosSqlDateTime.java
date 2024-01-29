package com.example.xls2sql.sql.tipoDadosSQL;

import com.example.xls2sql.domain.sql.ElementoSql;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public enum TipoDadosSqlDateTime implements TipoDadosSql{
    DATE,
    DATETIME,
    TIME,
    YEAR,
    TIMESTAMP
    ;

    @Override
    public int aceitaNumeroElementos() {
        return 0;
    }

    @Override
    public boolean verificarCelula(ElementoSql elementoSql) {
        final String REGEX_FORMATO_ERRADO = "^(3[01]|[12][0-9]|0?[1-9])(/|-)(1[0-2]|0?[1-9])\\2([1-9])[0-9]{3}";
        if (elementoSql.getCelula().matches(REGEX_FORMATO_ERRADO)){
            final String OLD_FORMAT = "dd/MM/yyyy";
            final String NEW_FORMAT = "yyyy/MM/dd";

            String newDateString;

            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = null;
            try {
                d = sdf.parse(elementoSql.getCelula());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
            elementoSql.setCelula(newDateString);
        }

        boolean elementoDentroDasRegras = false;
        String REGEX_TIME = "(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$";
        final String REGEX_DATE = "^[1-9][0-9]{3}\\/(((0[13578]|(10|12))\\/(0[1-9]|[1-2][0-9]|3[0-1]))|(02\\/(0[1-9]|[1-2][0-9]))|((0[469]|11)\\/(0[1-9]|[1-2][0-9]|30)))$";

        switch ((TipoDadosSqlDateTime)elementoSql.getTipoDados().getTipo()){

            case DATE :{
                elementoDentroDasRegras = elementoSql.getCelula().matches(REGEX_DATE);

            }

            case DATETIME,TIMESTAMP: {
                String  regexDateTime= REGEX_DATE + REGEX_TIME;
                elementoDentroDasRegras = elementoSql.getCelula().matches(regexDateTime);
            }

            case YEAR:{
                if (elementoSql.getCelula().matches("^[0-9]{4}$")){
                    int ano = Integer.parseInt(elementoSql.getCelula());
                    if (ano == 0000|| (ano >= 1901 && ano <= 2155)){
                        elementoDentroDasRegras = true;
                    }
                }
            }

            case TIME:{
                String regexCaseTime = "^-?(?:[0-7]\\d\\d|8[0-2]\\d|83[0-8]):[0-5]\\d:[0-5]\\d$";
                elementoDentroDasRegras = elementoSql.getCelula().matches(regexCaseTime);

            }



        }


        return elementoDentroDasRegras;
    }
}
