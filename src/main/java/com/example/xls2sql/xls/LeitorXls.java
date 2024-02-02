package com.example.xls2sql.xls;





import com.example.xls2sql.domain.sql.Coluna;
import com.example.xls2sql.domain.sql.DadosSql;
import com.example.xls2sql.domain.sql.ElementoSql;
import com.example.xls2sql.domain.sql.ElementosSql;
import com.example.xls2sql.sql.exceptions.CelulaExcelComTamanhoMaiorQueOPermitidoColunaException;
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

    public DadosSql ler(String enderecoXls) throws IOException, CelulaExcelComTamanhoMaiorQueOPermitidoColunaException {

        FileInputStream inputStream = new FileInputStream(new File(enderecoXls));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);



        DadosSql dadosSql = new DadosSql();
        Integer linhaDoExcel = 0;


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
                   this.elementoSql = new ElementoSql(linhaDoExcel);
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


    private void adicionarElemento(Cell cell, Coluna coluna) throws CelulaExcelComTamanhoMaiorQueOPermitidoColunaException {

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


