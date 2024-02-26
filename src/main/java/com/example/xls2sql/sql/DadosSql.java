package com.example.xls2sql.sql;

import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.domain.LinhaSql;

import java.util.ArrayList;

public class DadosSql {

    private ArrayList<Coluna> colunas ;

    private ArrayList<LinhaSql> agregadorLinhaSql;


    public DadosSql() {
        this.colunas = new ArrayList<>();
        this.agregadorLinhaSql = new ArrayList<>();
    }

    public void adicionar(Coluna coluna){
        colunas.add(coluna);
    }

    public  void adicionar(LinhaSql linhaSql){
        linhaSql.incluirNull(this.getColunas());
        agregadorLinhaSql.add(linhaSql);
    }


    public ArrayList<Coluna> getColunas() {
        return colunas;
    }

    public ArrayList<LinhaSql> getAgregadorElementosSql() {
        return agregadorLinhaSql;
    }
}
