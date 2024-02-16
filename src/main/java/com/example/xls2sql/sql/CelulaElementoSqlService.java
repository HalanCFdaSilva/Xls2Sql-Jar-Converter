package com.example.xls2sql.sql;

import com.example.xls2sql.coletor.domain.TipoDados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CelulaElementoSqlService {

    TipoDados tipoDados;

    public CelulaElementoSqlService(TipoDados tipoDados) {
        this.tipoDados = tipoDados;

    }


    public String formatarCelulaDateTime(ArrayList<String> lista){

        String texto = lista.get(0);

        if (this.tipoDados.getTipo().getClass().getSimpleName().equals("TipoDadosSqlDateTime")){
            final String REGEX_FORMATO_ERRADO = "^(3[01]|[12][0-9]|0?[1-9])(/|-)(1[0-2]|0?[1-9])\\2([1-9])[0-9]{3}";
            if (texto.matches(REGEX_FORMATO_ERRADO)){
                final String OLD_FORMAT = "dd/MM/yyyy";
                final String NEW_FORMAT = "yyyy/MM/dd";

                String newDateString;

                SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                Date d = null;
                try {
                    d = sdf.parse(texto);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                sdf.applyPattern(NEW_FORMAT);
                newDateString = sdf.format(d);
                texto = newDateString;
            }
        }


        return texto;
    }


    public boolean checarCelulaAIncluir(ArrayList<String> listaCelulaAdicionar) {
        return this.tipoDados.getTipo().verificarCelula(listaCelulaAdicionar,this.tipoDados.getNumeroElementos());
    }
}