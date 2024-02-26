package com.example.xls2sql.xls;





import com.example.xls2sql.sql.domain.CelulaLinhaSql;
import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.DadosSql;
import com.example.xls2sql.sql.domain.LinhaSql;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
/**
 Esta classe faz a leitura do arquivo xls e separa a linha com os dados da coluna sql das linhas com os dados
 dos elementos do sql
 @author Halan Silva**/

public class LeitorXls {


    private int colunaDoExcel;
    private int linhaDoExcel;
    private DadosSql dadosSql;
    private boolean linhaTemMaisUmaCelula;

    /**Este método com a utilização da api <a href= https://poi.apache.org/apidocs/dev/overview-summary.html>ApachePoi</a>
     * faz a leitura do arquivo xls/xlsx e envia para o método selecionarESalvarClasseSql que faz a separação da celula
     * em ElementoSql e Coluna além de salva las no dadosSql, ao fim da leitura o método retorna a classe DadosSql.
     * @param  enderecoXls Deve ser o endereço aonde está alocado o arquivo xls/xlsx na máquina
     * @see CelulaLinhaSql
     * @see Coluna
     * @see DadosSql*/
    public DadosSql ler(String enderecoXls) throws IOException {

        FileInputStream inputStream = new FileInputStream(new File(enderecoXls));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        this.dadosSql = new DadosSql();


        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            LinhaSql linhaSql = new LinhaSql(linhaDoExcel);

            this.linhaTemMaisUmaCelula = cellIterator.hasNext();
            while (this.linhaTemMaisUmaCelula) {
                Cell cell = cellIterator.next();
                this.colunaDoExcel= cell.getColumnIndex();
                this.linhaDoExcel = cell.getRowIndex();
                this.linhaTemMaisUmaCelula = cellIterator.hasNext();
                this.selecionarESalvarClasseSql(cell, linhaSql);



            }
        }
        inputStream.close();

        return dadosSql;
    }


    /**<p>Este método pega uma iteração da classe Cell e se a Cell for da primeira linha do arquivo xls/xlsx
     * ele chama o conversorXls e pede para criar uma Coluna, e no caso contrário ele pede ao Conversor Xls
     * para criar uma classe ElementoSql.</p>
     * No fim ele pega ou a classe coluna e a adiciona no campo dadosSql ou pega o elementoSql e o põe dentro do
     * ElementosSql recebido ao chama lo e caso essa seja o último ElementoSql da linha ele inclui o elementosSql
     * e a adiciona no campo dadossql.
     * @param linhaSql
     * @param cell uma celula do arquivo xls <a href= https://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/Cell.html>Cell</a>
     * @see ConversorXls
     * */
    private void selecionarESalvarClasseSql(Cell cell, LinhaSql linhaSql){

        if(this.linhaDoExcel == 0){

            ConversorXls conversorXls = new ConversorXls(colunaDoExcel,linhaDoExcel);
            Coluna coluna = conversorXls.adicionarColuna(cell);
            this.dadosSql.adicionar(coluna);



        }else{

            if (this.colunaDoExcel <= this.dadosSql.getColunas().size() -1) {

                ConversorXls conversorXls = new ConversorXls(this.colunaDoExcel,this.linhaDoExcel);
                Coluna colunaElemento = this.dadosSql.getColunas().get(this.colunaDoExcel);
                CelulaLinhaSql celulaLinhaSql = conversorXls.adicionarElemento(cell, colunaElemento.getTipoDados());
                linhaSql.adicionar(celulaLinhaSql);


            }
            if (!this.linhaTemMaisUmaCelula){
                this.dadosSql.adicionar(linhaSql);
            }

        }

    }





}


