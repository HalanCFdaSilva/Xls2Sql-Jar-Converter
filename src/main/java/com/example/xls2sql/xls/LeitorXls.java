package com.example.xls2sql.xls;





import com.example.xls2sql.domain.sql.Coluna;
import com.example.xls2sql.domain.sql.DadosSql;
import com.example.xls2sql.domain.sql.ElementoSql;
import com.example.xls2sql.domain.sql.ElementosSql;
import com.example.xls2sql.sql.exceptions.CelulaExcelComTamanhoMaiorQueOPermitidoColuna;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


public class LeitorXls {

    private ElementoSql elementoSql;

    public DadosSql ler(String enderecoXls) throws IOException, CelulaExcelComTamanhoMaiorQueOPermitidoColuna {

        FileInputStream inputStream = new FileInputStream(new File(enderecoXls));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);



        DadosSql dadosSql = new DadosSql();
        Integer linhaXls = 0;


        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();


            ElementosSql elementosSql = new ElementosSql();
            Coluna coluna = new Coluna();

            int colunaXls = 0;


            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();



               if(linhaXls == 0){
                   coluna = this.adicionarColuna(cell);

               } else if (linhaXls == 1 && colunaXls <= dadosSql.getColunas().size() -1) {
                   coluna = this.incluirTipo(cell, dadosSql.getColunas().get(colunaXls));
                   dadosSql.getColunas().set(colunaXls,coluna);

               }else if (linhaXls > 1 && colunaXls <= dadosSql.getColunas().size() -1) {
                   this.elementoSql = new ElementoSql(linhaXls);
                   this.adicionarElemento(cell, dadosSql.getColunas().get(colunaXls));
                   elementosSql.adicionar(this.elementoSql);



            }
                colunaXls++;
            if (linhaXls == 0){
                dadosSql.adicionar(coluna);
            } else if (linhaXls > 1) {
                dadosSql.adicionar(elementosSql);
            }

            }
                linhaXls++;
        }
        inputStream.close();

        return dadosSql;
    }

    private Coluna incluirTipo(Cell cell, Coluna coluna) {
        switch (cell.getCellType()) {


            case NUMERIC:
                coluna.pegarTipo(Integer.toString((int) cell.getNumericCellValue()),1);
                break;

            case STRING:
                coluna.pegarTipo(cell.getStringCellValue(),1);
                break;

        }
        return coluna;
    }

    private void adicionarElemento(Cell cell, Coluna coluna) throws CelulaExcelComTamanhoMaiorQueOPermitidoColuna {

        switch (cell.getCellType()) {

            case NUMERIC:
                String celula = Integer.toString((int) cell.getNumericCellValue());
                this.elementoSql.adicionarCelula(celula, coluna);
                break;

            case STRING:
                this.elementoSql.adicionarCelula(cell.getStringCellValue(),coluna);
                break;
        }


    }

    private Coluna adicionarColuna(Cell cell) {

        Coluna coluna = new Coluna();
        switch (cell.getCellType()) {
            case NUMERIC:

                coluna.adicionar(Integer.toString((int) cell.getNumericCellValue()));

                break;
            case STRING:

                coluna.adicionar(cell.getStringCellValue());

                break;
        }
        return coluna;
    }


}


