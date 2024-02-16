package com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService;

import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSQLString;

import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

public class TipoDadosSqlStringService {



    public boolean verificarCelulaChar(String celula,double numeroElementos) {
        if (numeroElementos == 0){
            if (celula.length() <= 1){
                return true;
            }

        }else {
            if (celula.length() <= numeroElementos){
                return true;
            }
        }

        return false;

    }

    public boolean verificarCelulaVarChar(String celula,double numeroElementos) {
        if (numeroElementos == 0){

            if ( celula.length() <= 65535){
                return true;
            }
        }else {
            if (celula.length() <= numeroElementos){
                return true;
            }
        }

        return false;

    }


    public boolean verificarCelulaTinyText(String celula) {

        if (celula.length() <= 255){
            return true;
        }
        return false;
    }

    public boolean verificarCelulaText(String celula,double numeroElementos) {
        final byte[] bytesString = celula.getBytes();
        if (numeroElementos == 0){
            if (bytesString.length <= 65535){
                return true;
            }
        }else {
            if (bytesString.length <= numeroElementos){
                return true;
            }
        }

        return false;
    }

    public boolean verificarCelulaMediumText(String celula) {

        if (celula.length() <= 16777215){
            return true;
        }
        return false;

    }

    public boolean verificarCelulaLongText(ArrayList<String> listaCelula) {

        long tamanhoString = 0;
        for (String text :listaCelula){
            tamanhoString += text.length();
        }

        if (tamanhoString <= 4294967295L){
            return true;
        }
        return false;


    }



    public boolean verificarCelulaBlob(String celula, double numeroElementos) {
        File fileString = new File(celula);
        if (numeroElementos == 0){
            if (fileString.isFile() && fileString.length() <= 65535){
                return true;
            }
        }else {
            if (fileString.isFile() && fileString.length() <= numeroElementos){
                return true;
            }
        }
        return false;
    }
    public boolean verificarCelulaMediumBlob(String celula) {

        File fileString = new File(celula);
        if (fileString.isFile() && fileString.length() <= 16777215){
            return true;
        }
        return false;
    }
    public boolean verificarCelulaLongBlob(String celula) {

        File fileString = new File(celula);
        if (fileString.isFile() && fileString.length() <=  4294967295l){
            return true;
        }
        return false;
    }


}
