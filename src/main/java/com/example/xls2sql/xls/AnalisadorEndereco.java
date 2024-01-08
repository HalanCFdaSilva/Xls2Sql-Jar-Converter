package com.example.xls2sql.xls;

import java.io.File;

public class AnalisadorEndereco {

    private static boolean verificarEnderecoExiste(String endereco){
        File file = new File(endereco);
        if (file.exists()){
            return true;
        }
        return false;
    }

    private static  boolean verificarExtensaoXls(String enderecoXls){

        if (enderecoXls.endsWith(".xls")||enderecoXls.endsWith(".xlsx")){
            return true;
        }
        return false;

    }

    public  static boolean verificarArquivoXls(String enderecoXls){
        if (verificarEnderecoExiste(enderecoXls) && verificarExtensaoXls(enderecoXls)){
            return true;
        }
        return false;
    }

}
