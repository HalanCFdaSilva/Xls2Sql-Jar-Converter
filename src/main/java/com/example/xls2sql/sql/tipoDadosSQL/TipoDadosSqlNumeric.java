package com.example.xls2sql.sql.tipoDadosSQL;

import com.example.xls2sql.domain.sql.ElementoSql;
import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;

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
    public boolean verificarCelula(ElementoSql elementoSql) {

            boolean elementoDentroDasRegras = false;

            switch ((TipoDadosSqlNumeric)elementoSql.getTipoDados().getTipo()){

                case BIT :{

                   try{
                       Integer.parseInt(elementoSql.getCelula(),2);
                       if (elementoSql.getCelula().length() <= elementoSql.getTipoDados().getNumeroElementos()){
                           elementoDentroDasRegras = true;
                       }
                   }catch (Exception e){

                   }
                    return elementoDentroDasRegras;

                }

                case TINYINT: {
                    int valorCelula = Integer.parseInt(elementoSql.getCelula());

                    if (valorCelula <= 127 && valorCelula >= -128){
                        elementoDentroDasRegras = true;
                    }
                    return elementoDentroDasRegras;

                }

                case SMALLINT:{
                    int valorCelula = Integer.parseInt(elementoSql.getCelula());
                    if (valorCelula <= 32767 && valorCelula >= -32767){
                        elementoDentroDasRegras = true;
                    }
                    return elementoDentroDasRegras;
                }

                case MEDIUMINT:{
                    int valorCelula = Integer.parseInt(elementoSql.getCelula());
                    if (valorCelula <= 8388608 && valorCelula >= -8388608){
                        elementoDentroDasRegras = true;
                    }

                    return elementoDentroDasRegras;

                }

                case BIGINT:{
                   try {
                       long valorCelula = Long.getLong(elementoSql.getCelula());

                       elementoDentroDasRegras = true;

                   } catch (Exception e) {

                   }
                    return elementoDentroDasRegras;
                }

                case INT:{
                    int valorCelula = Integer.parseInt(elementoSql.getCelula());
                    long valorAVerificar = Long.valueOf("2147483648");
                    if (valorCelula <= valorAVerificar && valorCelula >= -2147483648){
                        elementoDentroDasRegras = true;
                    }


                    return elementoDentroDasRegras;
                }

                case DECIMAL:{

                    if (elementoSql.getTipoDados().getNumeroElementosString().contains(",")){
                        int posicaoVirgula = elementoSql.getCelula().indexOf(",");
                        String stringNumeroAntesVirgula = elementoSql.getCelula().substring(1,posicaoVirgula);
                        String stringNumeroPosVirgula = elementoSql.getCelula().substring(posicaoVirgula, elementoSql.getCelula().length()-1);

                        int numeroAntesVirgula = Integer.parseInt(stringNumeroAntesVirgula);
                        int numeroPosVirgula = Integer.parseInt(stringNumeroPosVirgula);

                        if (elementoSql.getCelula().length() <= numeroAntesVirgula){
                            String textoPosVirgula = null;
                            if (elementoSql.getCelula().contains(".")) {
                                int posicaoVirgulaElemento = elementoSql.getCelula().indexOf(".");
                                textoPosVirgula = elementoSql.getCelula().substring(posicaoVirgulaElemento + 1);
                            }
                            if (textoPosVirgula.length() <= numeroPosVirgula){
                                elementoDentroDasRegras = true;
                            }


                        }
                    } else if (elementoSql.getCelula().length() <= elementoSql.getTipoDados().getNumeroElementos() && !elementoSql.getCelula().contains(",")) {
                        elementoDentroDasRegras = true;
                    }

                    return elementoDentroDasRegras;

                }

                default: return elementoDentroDasRegras;
            }

    }
}
