package com.example.xls2sql.domain.sql;

import java.util.ArrayList;

public class ElementosSql {


    private ArrayList<ElementoSql> elementosTabela;

    public ElementosSql() {

        this.elementosTabela = new ArrayList<>();
    }



    public void adicionar(ElementoSql elementoSql){
        this.elementosTabela.add(elementoSql);
    }

    public ArrayList<ElementoSql> getElementosTabela() {
        return elementosTabela;
    }
}
