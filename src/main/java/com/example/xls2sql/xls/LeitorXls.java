package com.example.xls2sql.xls;





import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.domain.DadosSql;
import com.example.xls2sql.sql.domain.ElementoSql;
import com.example.xls2sql.sql.domain.ElementosSql;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class LeitorXls {

    private ElementoSql elementoSql;

    public DadosSql ler(String enderecoXls) throws IOException {

        FileInputStream inputStream = new FileInputStream(new File(enderecoXls));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);



        DadosSql dadosSql = new DadosSql();
        int linhaDoExcel = 0;


        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();


            ElementosSql elementosSql = new ElementosSql();
            Coluna coluna = new Coluna();

            int colunaDoExcel = 0;


            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();



               if(linhaDoExcel == 0){
                   coluna = this.adicionarColuna(cell, colunaDoExcel);


               }else if (colunaDoExcel <= dadosSql.getColunas().size() -1) {
                   this.elementoSql = new ElementoSql(linhaDoExcel, colunaDoExcel);
                   this.adicionarElemento(cell, dadosSql.getColunas().get(colunaDoExcel));
                   elementosSql.adicionar(this.elementoSql);
            }
                colunaDoExcel++;
            if (linhaDoExcel == 0){
                dadosSql.adicionar(coluna);
            } else  {
                dadosSql.adicionar(elementosSql);
            }

            }
                linhaDoExcel++;
        }
        inputStream.close();

        return dadosSql;
    }


    private void adicionarElemento(Cell cell, Coluna coluna) {

        ArrayList<String> celulaAAdicionar = new ArrayList<>();
        int i = 1;
        int numeroLimiteAtual = 16777217*i;
        int numeroLimiteAntigo = 0;

        switch (cell.getCellType()) {

            case NUMERIC:{
                while(Integer.toString((int) cell.getNumericCellValue()).length() >= numeroLimiteAtual){
                    String celula = Integer.toString((int) cell.getNumericCellValue()).substring(numeroLimiteAntigo
                            ,numeroLimiteAtual);

                    celulaAAdicionar.add(celula);
                    i++;
                    numeroLimiteAntigo = numeroLimiteAtual;
                    numeroLimiteAtual = 16777217*i;
                }
                int numerMaximo = Integer.toString((int) cell.getNumericCellValue()).length() - numeroLimiteAntigo;
                if (numerMaximo > 0){
                    String celula = Integer.toString((int) cell.getNumericCellValue()).substring(numeroLimiteAntigo);

                    celulaAAdicionar.add(celula);
                }



            }


            case STRING:{
                while(Integer.toString((int) cell.getNumericCellValue()).length() >= numeroLimiteAtual){
                    String celula = cell.getStringCellValue().substring(numeroLimiteAntigo,numeroLimiteAtual);
                    celulaAAdicionar.add(celula);
                    i++;
                    numeroLimiteAntigo = numeroLimiteAtual;
                    numeroLimiteAtual = 16777217*i;
                }

                int numerMaximo = cell.getStringCellValue().length() - numeroLimiteAntigo;
                if (numerMaximo > 0){
                    String celula = cell.getStringCellValue().substring(numeroLimiteAntigo);

                    celulaAAdicionar.add(celula);
                }
            }

        }
        this.elementoSql.adicionarCelula(celulaAAdicionar,coluna);


    }

    private Coluna adicionarColuna(Cell cell, int colunaExcel) {

        Coluna coluna = new Coluna();
        switch (cell.getCellType()) {
            case NUMERIC:

                coluna.adicionar(Integer.toString((int) cell.getNumericCellValue()), colunaExcel);

                break;
            case STRING:

                coluna.adicionar(cell.getStringCellValue(), colunaExcel);

                break;
        }
        return coluna;
    }


}


