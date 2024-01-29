package com.example.xls2sql.sql.tipoDadosSQL;

import com.example.xls2sql.domain.sql.ElementoSql;

import java.io.File;
import java.nio.charset.CharsetEncoder;


public enum TipoDadosSQLString implements TipoDadosSql{

    CHAR,
    VARCHAR,
    TINYTEXT,
    TEXT,
    MEDIUMTEXT,
    LONGTEXT,
    BLOB,
    MEDIUMBLOB,
    LONGBLOB;




    @Override
    public int aceitaNumeroElementos() {
        switch (this){

            case TINYTEXT,MEDIUMTEXT,LONGTEXT,MEDIUMBLOB,LONGBLOB: return 0;

            default : return 1;

        }
    }

    @Override
    public boolean verificarCelula(ElementoSql elementoSql) {

        boolean elementoDentroDasRegras = false;

        switch ((TipoDadosSQLString)elementoSql.getTipoDados().getTipo()){

            case CHAR,VARCHAR: {
                if (elementoSql.getCelula().length() <= elementoSql.getTipoDados().getNumeroElementos()){
                    elementoDentroDasRegras = true;
                }
            }

            case TINYTEXT: {
                if (elementoSql.getCelula().length() <= 255){
                    elementoDentroDasRegras = true;
                }
            }

            case TEXT: {
                final byte[] bytesString = elementoSql.getCelula().getBytes();
                if (bytesString.length <= 65535){
                    elementoDentroDasRegras = true;
                }
            }

            case MEDIUMTEXT:{
                if (elementoSql.getCelula().length() <= 16777215){
                    elementoDentroDasRegras = true;
                }
            }

            case LONGTEXT:{
                if (elementoSql.getCelula().length() <= 4294967295L){
                    elementoDentroDasRegras = true;
                }
            }

            case BLOB: {
                File fileString = new File(elementoSql.getCelula());
                if (fileString.isFile() && fileString.length() <= 65535){
                    elementoDentroDasRegras = true;
                }
            }

            case MEDIUMBLOB: {

                File fileString = new File(elementoSql.getCelula());
                if (fileString.isFile() && fileString.length() <= 65535){
                    elementoDentroDasRegras = true;
                }

            }

            case LONGBLOB:{
                File fileString = new File(elementoSql.getCelula());
                if (fileString.isFile() && fileString.length() <=  4294967295L){
                    elementoDentroDasRegras = true;
                }
            }



        }

        return elementoDentroDasRegras;

    }
}
