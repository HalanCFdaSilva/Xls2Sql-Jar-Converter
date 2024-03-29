package com.example.xls2sql.sql.escritor;

import com.example.xls2sql.sql.domain.CelulaLinhaSql;
import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.domain.LinhaSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;

import java.util.ArrayList;

/**Classe de suporte para a classe EscritorSql que cria um {@code Arraylist<String>} que será
 * usado na EscritorSql para escrever no arquivoSql o código que irá inserir uma linha do Sql.
 * @author Halan Silva
 * @see EscritorSql
 * @see LinhaSql*/
public class EscritorTextoLinha {

    /**Variável onde será armazenado a string com o código que será usado no {@link EscritorSql EscritorSql}*/
    ArrayList<String > textoAEscrever;

    /**Variável com todos os dados a serem inseridos na linha.
     * @see LinhaSql*/
    LinhaSql linhaSql;

    /**Método gerador da classe sql.*/
    public EscritorTextoLinha(LinhaSql linhaSql) {
        this.textoAEscrever = new ArrayList<>();
        this.linhaSql = linhaSql;
    }

    /**Método que faz a inclusão na variável textoAEscrever o código sql com os dados da tabela para inserir uma
     * linha no sql.
     * @param colunas  Parâmetro com todos os dados das colunas da tabela de onde será retirado o nome das colunas.
     * @param nomeTabela  Parâmetro com o nome da tabela do sql onde será inserido a linha*/
    public void textoColunaIncluirLinhas(ArrayList<Coluna> colunas, String nomeTabela){
        textoAEscrever.add("INSERT INTO " + nomeTabela + "(");

        StringBuilder textoColuna = new StringBuilder();
        boolean primeiraInteracaoLaco = true;
        for (Coluna coluna : colunas) {

            if (!this.linhaSql.getCelula(coluna.getTipoDados().posicaoColunaXls()).isNull()){
                if (!primeiraInteracaoLaco) {
                    textoColuna.append(",");
                }
                textoColuna.append(coluna.getNome());
                primeiraInteracaoLaco = false;
            }

        }
        this.textoAEscrever.add(textoColuna.toString());


    }

    /**Método que faz a inclusão na variável textoAEscrever do restante do código sql com as informações que serão
     * inseridas na linha.
     * <p>O método também faz a separação das células da linha entre os tipos de dados do sql:</p>
     * <p>(String, Numeric, DateTime)</p>
     * Chamando o método interno certo para os diferentes códigos para cada um dos tipos.*/
    public void textoElementosLinha(){


        this.textoAEscrever.add(")\nVALUES(");
        boolean primeiraInteracaoLaco = true;
        for (CelulaLinhaSql celulaLinhaSql : linhaSql.getCelulasLinha()) {
            if (!celulaLinhaSql.isNull()){
                if (!primeiraInteracaoLaco) {
                    this.textoAEscrever.add(",");
                }
                if (celulaLinhaSql.getTipoDados().tipoColunaSql().getClass().getSimpleName().equals("TipoColunaSqlNumeric")){
                    this.textoAEscrever.add(this.escreverElementoNumeric(celulaLinhaSql));
                }

                if (celulaLinhaSql.getTipoDados().tipoColunaSql().getClass().getSimpleName().equals("TipoColunaSQLString")){
                    ArrayList<String> textoCelula = this.escreverElementoString(celulaLinhaSql);
                    this.textoAEscrever.addAll(textoCelula);

                }

                if (celulaLinhaSql.getTipoDados().tipoColunaSql().getClass().getSimpleName().equals("TipoColunaSqlDateTime")){
                    this.textoAEscrever.add(this.escreverElementoDateTime(celulaLinhaSql));
                }
                primeiraInteracaoLaco = false;
            }
        }
        this.textoAEscrever.add(");");
    }

    /**Método interno que cria uma String com o código para inserir uma célula do tipo Numeric.
     * @param celulaLinhaSql Célula com os dados a serem inseridos no Sql.
     * @see TipoColunaSqlNumeric
     * @return Retorna uma string com os dados a serem inseridos no sql já formatados para ele.*/
    private String escreverElementoNumeric(CelulaLinhaSql celulaLinhaSql) {
        return celulaLinhaSql.getCelula().get(0);
    }

    /**Método interno que cria uma String com o código para inserir uma célula do tipo DateTime
     * conforme os muitos tipos de DateTime.
     * @param celulaLinhaSql Célula com os dados a serem inseridos no Sql.
     * @see TipoColunaSqlDateTime
     * @return Retorna uma string com os dados a serem inseridos no sql já formatados para ele.*/
    private String escreverElementoDateTime(CelulaLinhaSql celulaLinhaSql) {
        String textoAEscrever;
        switch ((TipoColunaSqlDateTime) celulaLinhaSql.getTipoDados().tipoColunaSql()){

            case DATETIME,TIMESTAMP: {
                String date;
                if (celulaLinhaSql.getCelula().get(0).contains("/")){
                    date = celulaLinhaSql.getCelula().get(0).replace("/","-");
                }else {
                    date = celulaLinhaSql.getCelula().get(0);
                }
                textoAEscrever = "'"+ date + "'";
                return textoAEscrever;
            }
            case DATE: {
                String date;
                if (celulaLinhaSql.getCelula().get(0).contains("/")){
                    date = celulaLinhaSql.getCelula().get(0).replace("/","-");
                }else {
                    date = celulaLinhaSql.getCelula().get(0);
                }
                textoAEscrever = "TO_DATE('"+ date + "', 'yyyy-mm-dd')";
                return textoAEscrever;
            }
            default: {
                textoAEscrever = "'" + celulaLinhaSql.getCelula().get(0) + "'";
                return textoAEscrever;
            }
        }
    }

    /**Método interno que cria um {@code Arraylist<String>} com o código para inserir uma célula do tipo String
     * conforme os muitos tipos de String.
     * @param  celulaLinhaSql Célula com os dados a serem inseridos no Sql.
     * @see TipoColunaSQLString
     * @return Retorna um arrayList com os dados a serem inseridos no sql já formatados para ele.*/
    private ArrayList<String> escreverElementoString(CelulaLinhaSql celulaLinhaSql){
        ArrayList<String> texto = new ArrayList<>();
        switch ((TipoColunaSQLString) celulaLinhaSql.getTipoDados().tipoColunaSql()){
            case LONGTEXT : {
                texto.add("'");

                texto.addAll(celulaLinhaSql.getCelula());

                texto.add("'");
                return texto;

            }

            default: {
                texto.add("'" + celulaLinhaSql.getCelula().get(0) + "'");
                return texto;
            }

        }
    }


    /**Método que retorna a variável textoAEscrever.
     * @return Retorna um {@code Arraylist<String>} onde se guarda o código sql para inserir uma linha. */
    public ArrayList<String> getTextoAEscrever() {
        return textoAEscrever;
    }
}
