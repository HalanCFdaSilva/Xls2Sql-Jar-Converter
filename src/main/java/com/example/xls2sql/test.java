package com.example.xls2sql;

import com.example.xls2sql.sql.DadosSql;
import com.example.xls2sql.sql.domain.CelulaLinhaSql;
import com.example.xls2sql.sql.domain.LinhaSql;
import com.example.xls2sql.xls.LeitorXls;
import java.io.IOException;


public class test {
    public static void main(String[] args) throws IOException {

        String endereco = "C:\\Users\\levi\\Desktop\\programar\\teste.xlsx";
        LeitorXls leitorXls = new LeitorXls();
        DadosSql dadosSql = leitorXls.ler(endereco);
        for (LinhaSql linha : dadosSql.getAgregadorElementosSql()){
            for (CelulaLinhaSql celulaLinhaSql : linha.getCelulasLinha()){
                System.out.println(celulaLinhaSql.getCelula().get(0));
            }
        }
    }
}
