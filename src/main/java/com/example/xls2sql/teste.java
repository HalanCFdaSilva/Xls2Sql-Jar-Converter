package com.example.xls2sql;


import com.example.xls2sql.coletor.domain.TipoDados;
import com.example.xls2sql.sql.CelulaElementoSqlService;
import com.example.xls2sql.sql.domain.ElementoSql;
import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;
import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;

import java.io.FileNotFoundException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Set;

public class teste {


    public static void main(String[] args) throws FileNotFoundException {
        ElementoSql elementoSql = new ElementoSql(1,1);
        ArrayList<String> celula = new ArrayList();
        celula.add("arroz");
        elementoSql.setCelula(celula);




    }








}
