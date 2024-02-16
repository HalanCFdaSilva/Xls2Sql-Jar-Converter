package com.example.xls2sql.sql.tipoDadosSQL;

import com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService.TipoDadosSqlNumericService;

import java.util.ArrayList;

public enum TipoDadosSqlNumeric implements TipoDadosSql {

    DECIMAL,
    BIT,
    TINYINT,
    SMALLINT,
    MEDIUMINT,
    INT,
    BIGINT;

    TipoDadosSqlNumeric() {
    }


    @Override
    public boolean aceitaNumeroElementos() {
        switch ( this){
            case BIT,DECIMAL: return true;

            default : return false;


        }
    }

    @Override
    public boolean verificarCelula(ArrayList<String> listaCelula, double numeroElementos) {
        if (listaCelula.size() == 1 ) {
            String celula = listaCelula.get(0);
            TipoDadosSqlNumericService service = new TipoDadosSqlNumericService(numeroElementos);

            switch (this) {

                case BIT:
                    return service.verificarCelulaBit(celula);

                case TINYINT:
                    return service.verificarCelulaTinyInt(celula);

                case SMALLINT:
                    return service.verificarCelulaSmallInt(celula);

                case MEDIUMINT:
                    return service.verificarCelulaMediumInt(celula);

                case BIGINT:
                    return service.verificarCelulaBigInt(celula);

                case INT:
                    return service.verificarCelulaInt(celula);

                case DECIMAL:
                    return service.verificarCelulaDecimal(celula);

                default:
                    return false;
            }

        }

        return false;
    }
}
