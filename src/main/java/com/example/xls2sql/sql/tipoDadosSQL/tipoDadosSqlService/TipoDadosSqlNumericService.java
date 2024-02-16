package com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService;

public class TipoDadosSqlNumericService {

    double numeroElementos;
    int numeroElementosPosVirgula;

    public TipoDadosSqlNumericService(double numeroElementos) {
        this.numeroElementos = numeroElementos;

        String numeroElementosString = Double.toString(numeroElementos);

        int posicaoVirgulaElemento = numeroElementosString.indexOf(".");
        String textoPosVirgula = numeroElementosString.substring(posicaoVirgulaElemento + 1);
        numeroElementosPosVirgula = Integer.parseInt(textoPosVirgula);



    }

    public boolean verificarCelulaBit(String celula) {

        try{
            Integer.parseInt(celula,2);
            if (celula.length() <= numeroElementos){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;

        }
    }

    public boolean verificarCelulaTinyInt(String celula) {
        int valorCelula = Integer.parseInt(celula);

        if (valorCelula <= 127 && valorCelula >= -128){
            return true;
        }
        return false;

    }

    public boolean verificarCelulaSmallInt(String celula) {
        int valorCelula = Integer.parseInt(celula);
        if (valorCelula <= 32767 && valorCelula >= -32767){
            return true;
        }
        return false;
    }
    public boolean verificarCelulaMediumInt(String celula) {

        int valorCelula = Integer.parseInt(celula);
        if (valorCelula <= 8388608 && valorCelula >= -8388608){
            return true;
        }

        return false;

    }
    public boolean verificarCelulaBigInt(String celula) {
        try {

            long valorCelula = Long.parseLong(celula);
            if (valorCelula <= Long.MAX_VALUE && valorCelula >= Long.MIN_VALUE){
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }
    public boolean verificarCelulaInt(String celula) {
        if (!celula.contains(".")){
            double valorCelula = Double.parseDouble(celula);
            long valorAVerificar = Long.valueOf("2147483648");
            if (valorCelula <= valorAVerificar && valorCelula >= -2147483648){
                 return true;
            }
        }
        return false;

    }

    public boolean verificarCelulaDecimal (String celula) {
        if (celula.contains(".")){
            int posicaoVirgula = celula.indexOf(".");
            String stringNumeroPosVirgula = celula.substring(posicaoVirgula + 1, celula.length());
            int numeroPosVirgula = stringNumeroPosVirgula.length();





            celula = celula.replace(".","");
            if (celula.length() <= numeroElementos){
                if (numeroElementosPosVirgula >= numeroPosVirgula){
                    return true;
                }


            }
        } else if (celula.length() <= numeroElementos ) {
            return true;
        }

        return false;

    }

}
