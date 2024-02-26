package com.example.xls2sql.sql.tipoDadosSQL;


import com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService.TipoDadosSqlStringService;
import java.util.ArrayList;


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
    public boolean aceitaNumeroElementos() {
        switch (this){

            case TINYTEXT,MEDIUMTEXT,LONGTEXT,MEDIUMBLOB,LONGBLOB: return false;

            default : return true;

        }
    }

    @Override
    public boolean verificarCelula(ArrayList<String> listaCelula, double numeroElementos) {


        if ((this != LONGTEXT && listaCelula.size() == 1) || this == LONGTEXT){
            boolean elementoDentroDasRegras = false;
            String celula = listaCelula.get(0);
            if (celula == "null" || celula == " " ){
                return true;
            }

            TipoDadosSqlStringService  service = new TipoDadosSqlStringService();

            switch (this){

                case CHAR: return service.verificarCelulaChar(celula,numeroElementos);

                case VARCHAR: return service.verificarCelulaVarChar(celula,numeroElementos);

                case TINYTEXT: return service.verificarCelulaTinyText(celula);


                case TEXT: return service.verificarCelulaText(celula,numeroElementos);

                case MEDIUMTEXT: return service.verificarCelulaMediumText(celula);

                case LONGTEXT:return service.verificarCelulaLongText(listaCelula);

                case BLOB: return service.verificarCelulaBlob(celula,numeroElementos);

                case MEDIUMBLOB: return service.verificarCelulaMediumBlob(celula);

                case LONGBLOB: return service.verificarCelulaLongBlob(celula);

                default: return elementoDentroDasRegras;



            }
        }

        return false;

    }


}
