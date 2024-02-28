package com.example.xls2sql.sql;

import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.domain.LinhaSql;

import java.util.ArrayList;

/**Classe que armazena o conteúdo contido no arquivo xls/xlsx e que deve ser convertido para o arquivo sql.
 * @author Halan Silva
 * @see com.example.xls2sql.xls.LeitorXls
 * @see com.example.xls2sql.sql.escritor.EscritorSql*/
public class DadosSql {

    /**Variável que guarda todas as colunas do sql registradas no arquivo xls/xlsx.
     * @see Coluna*/
    private final ArrayList<Coluna> colunas ;

    /**Variável que guarda todas as linhas a serem inseridas no sql e que estão contidas no arquivo xls/xlsx.
     * @see LinhaSql*/
    private final ArrayList<LinhaSql> agregadorLinhaSql;


    /**Método que cria um objeto dessa classe e inicia as variáveis.*/
    public DadosSql() {
        this.colunas = new ArrayList<>();
        this.agregadorLinhaSql = new ArrayList<>();
    }

    /**Método que adiciona uma nova coluna na variável colunas.
     * @param coluna  coluna que vai ser incluída na variável colunas.*/
    public void adicionar(Coluna coluna){
        boolean colunaJaSalva = false;
        for (Coluna colunaSalva : this.getColunas()){
            if (colunaSalva.getNome() == coluna.getNome()){
                colunaJaSalva = true;
                break;
            }
        }
        if (!colunaJaSalva){
            this.colunas.add(coluna);
        }
    }

    /**Método que adiciona uma linhaSql na variável agregadorLinhaSql e preenche as células das colunas que não serão
     * inseridas nesta linhaSql.
     * @param linhaSql linhaSql a ser adicionada na variável e a
     *                 checar se tem alguma coluna que não terá dados inserido por ela.*/
    public  void adicionar(LinhaSql linhaSql){
        if (!linhaSql.getCelulasLinha().isEmpty()){
            linhaSql.incluirNull(this.getColunas());
            agregadorLinhaSql.add(linhaSql);
        }
    }

    /**Método que retorna a variável colunas.
     * @return retorna um Arraylist de {@link Coluna colunas}.*/
    public ArrayList<Coluna> getColunas() {
        return colunas;
    }

    /**Método que retorna a variável agregadorLinhaSql.
     * @return retorna um Arraylist de {@link LinhaSql linhasSql}.*/
    public ArrayList<LinhaSql> getAgregadorLinhaSql() {
        return agregadorLinhaSql;
    }
}
