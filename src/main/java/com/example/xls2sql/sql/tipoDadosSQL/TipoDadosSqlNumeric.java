package com.example.xls2sql.sql.tipoDadosSQL;

import com.example.xls2sql.domain.sql.ElementoSql;
import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;

public enum TipoDadosSqlNumeric implements TipoDadosSql {

    BIT,
    TINYINT,
    SMALLINT,
    MEDIUMINT,
    INT,
    BIGINT,
    DECIMAL;

    TipoDadosSqlNumeric() {
    }


    @Override
    public int aceitaNumeroElementos() {
        switch ( this){
            case BIT: return 1;

            case DECIMAL: return 2;

            default : return 0;


        }
    }

    @Override
    public boolean verificarCelula(ElementoSql elementoSql) {


        try {
            boolean elementoDentroDasRegras = false;

            double valorCelula = Double.parseDouble(elementoSql.getCelula());


            switch ((TipoDadosSqlNumeric)elementoSql.getTipoDados().getTipo()){
                case BIT :{

                    Integer.parseInt(elementoSql.getCelula(),2);


                    if (elementoSql.getCelula().length() <= elementoSql.getTipoDados().getNumeroElementos()){
                        elementoDentroDasRegras = true;
                    }

                }

                case TINYINT: {
                    if (valorCelula <= 127 && valorCelula >= -128){
                        elementoDentroDasRegras = true;
                    }

                }

                case SMALLINT:{
                    if (valorCelula <= 32767 && valorCelula >= -32767){
                        elementoDentroDasRegras = true;
                    }
                }

                case MEDIUMINT:{
                    if (valorCelula <= 8388608 && valorCelula >= -8388608){
                        elementoDentroDasRegras = true;
                    }


                }

                case BIGINT:{

                    elementoDentroDasRegras = true;

                }

                case INT:{
            long valorAVerificar = Long.valueOf("2147483648");
                    if (valorCelula <= valorAVerificar && valorCelula >= -2147483648){
                        elementoDentroDasRegras = true;
                    }


                }
                case DECIMAL: {
                    if (elementoSql.getCelula().length() <= elementoSql.getTipoDados().getNumeroElementos(1)){
                        String textoPosVirgula = null;
                        if (elementoSql.getCelula().contains(".")) {
                            int posicaoVirgula = elementoSql.getCelula().indexOf(".");
                            textoPosVirgula = elementoSql.getCelula().substring(posicaoVirgula + 1);

                        }
                        if (elementoSql.getTipoDados().getNumeroElementos(2) != 0){
                            if (textoPosVirgula.length() <= elementoSql.getTipoDados().getNumeroElementos(2)){
                                elementoDentroDasRegras = true;
                            }
                        }else{
                            elementoDentroDasRegras = true;
                        }
                    }
                }
            }

            return elementoDentroDasRegras;
        } catch (NumberFormatException e){
            throw new CelulaComElementosNaoConversiveisException(elementoSql.getLinha(),elementoSql.getTipoDados().getColuna());
        }
    }
}
