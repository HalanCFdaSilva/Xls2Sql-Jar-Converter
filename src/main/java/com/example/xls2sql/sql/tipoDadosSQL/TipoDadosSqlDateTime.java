package com.example.xls2sql.sql.tipoDadosSQL;


import com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService.TipoDadosSqlDateTimeService;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

public enum TipoDadosSqlDateTime implements TipoDadosSql{
    DATE,
    DATETIME,
    TIME,
    YEAR,
    TIMESTAMP
    ;

    @Override
    public boolean aceitaNumeroElementos() {
        return false;
    }

    @Override
    public boolean verificarCelula(ArrayList<String> listaCelula, double numeroElementos) {

        if (listaCelula.size() == 1 ){
            String celula = listaCelula.get(0);
            TipoDadosSqlDateTimeService service = new TipoDadosSqlDateTimeService();


            switch (this){

                case DATE : return service.verificarCelulaDate(celula);


                case DATETIME: return service.verificarCelulaDateTime(celula);

                case TIMESTAMP: return service.verificarCelulaTimeStamp(celula);

                case YEAR: return service.verificarCelulaYear(celula);

                case TIME: return service.verificarCelulaTime(celula);




            }
        }


        return false;
    }
}
