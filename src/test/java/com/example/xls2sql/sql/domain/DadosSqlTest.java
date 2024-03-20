package com.example.xls2sql.sql.domain;

import com.example.xls2sql.sql.domain.CelulaLinhaSql;
import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.DadosSql;
import com.example.xls2sql.sql.domain.LinhaSql;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**Classe que faz testes automatizados na classe DadosSql.
 * @author Halan Silva
 * @see DadosSql*/
public class DadosSqlTest {

    /**Método que verifica se o método adicionar inclui uma Coluna na variável colunas.
     * @see Coluna*/
    @Test
    public void adicionarColunaSql(){
        Coluna coluna =new Coluna();
        coluna.adicionar("Pratos[int]",1);
        DadosSql dadosSql = new DadosSql();
        dadosSql.adicionar(coluna);
        Assertions.assertEquals(coluna,dadosSql.getColunas().get(0));

    }

    /**Método que verifica se o método adicionar inclui uma LinhaSql na variável agregadorLinhaSql.
     * @see LinhaSql*/
    @Test
    public void adicionarElementoSql() {
        LinhaSql linhaSql = new LinhaSql(1);
        CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(0,0);
        linhaSql.adicionar(celulaLinhaSql);
        DadosSql dadosSql = new DadosSql();
        dadosSql.adicionar(linhaSql);

        Assertions.assertEquals(linhaSql,dadosSql.getAgregadorLinhaSql().get(0));
    }


    /**Método que verifica se o método adicionar não inclui uma linhaSql na variável AgregadorLinhaSql se a
     * linha estiver vazia.
     * @see LinhaSql*/
    @Test
    public void dadosSqlNaoGuardaLinhaComNenhumaCelulaLinha() {

        DadosSql dadosSql = new DadosSql();

        LinhaSql linhaSql = new LinhaSql(1);
        Assertions.assertEquals(0, linhaSql.getCelulasLinha().size());
        Assertions.assertTrue(dadosSql.getAgregadorLinhaSql().isEmpty());

        dadosSql.adicionar(linhaSql);
        Assertions.assertTrue(dadosSql.getAgregadorLinhaSql().isEmpty());
    }

    /**Método que verifica se o método adicionar inclui CelulasLinhaSql na linhaSql nas mesmas posições de todas as
     * colunas salvas na variável colunas e caso não haja alguma célula em alguma das posições ele cria uma célula com o
     * texto null e o insere no linhaSql para após isso salvar o linhaSql na variável AgregadorLinhaSql.
     * @see Coluna
     * @see CelulaLinhaSql
     * @see LinhaSql*/
    @Test
    public void incluirCelulaNullAoAdicionarLinhaSqlComColunaSemCelulaNaLinha() {

        int i=0;
        DadosSql dadosSql = new DadosSql();
        ArrayList<String> textosColunas = new ArrayList<>();
        textosColunas.add("Pratos1[int]");
        textosColunas.add("Pratos2[int]");
        textosColunas.add("Pratos3[int]");
        for (String string : textosColunas){
            Coluna coluna = new Coluna();
            coluna.adicionar(string,i);
            dadosSql.adicionar(coluna);
            i++;
        }

        LinhaSql linhaSql = new LinhaSql(1);
        CelulaLinhaSql celula = new CelulaLinhaSql(1,0);
        ArrayList<String> t = new ArrayList<>();
        t.add("1");
        celula.adicionarCelula(t,dadosSql.getColunas().get(0).getTipoDados());
        linhaSql.adicionar(celula);
        Assertions.assertEquals(1,
                linhaSql.getCelulasLinha().size());

        dadosSql.adicionar(linhaSql);

        Assertions.assertEquals(3,
                linhaSql.getCelulasLinha().size());
        Assertions.assertEquals(linhaSql, dadosSql.getAgregadorLinhaSql().get(0));
    }
}
