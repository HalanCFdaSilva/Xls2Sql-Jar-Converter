package com.example.xls2sql.domain.sql;

import java.util.ArrayList;

public class DadosSql {

    private ArrayList<Coluna> colunas ;

    private ArrayList<ElementosSql> elementos;


    public DadosSql() {
        this.colunas = new ArrayList<>();
        this.elementos = new ArrayList<>();
    }

    public void adicionar(Coluna coluna){
        colunas.add(coluna);
    }

    public  void adicionar(ElementosSql elementosSql){
        elementos.add(elementosSql);
    }


    public ArrayList<Coluna> getColunas() {
        return colunas;
    }

    public ArrayList<ElementosSql> getElementos() {
        return elementos;
    }
}
