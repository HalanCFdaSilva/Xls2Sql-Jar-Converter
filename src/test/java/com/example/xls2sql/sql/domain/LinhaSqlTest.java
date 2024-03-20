package com.example.xls2sql.sql.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**Classe que faz testes automatizados na classe LinhaSql.
 * @author Halan Silva
 * @see LinhaSql*/
public class LinhaSqlTest {

    /**Variável da classe alvo dos testes.*/
    private LinhaSql linhaSql;

    /**Método chamado antes de cada teste pelo Junit e que serve para iniciar a variável.*/
    @BeforeEach
    public void antesCadaTest(){
        this.linhaSql = new LinhaSql(1);
    }

    /**Método que verifica se a classe LinhaSql está salvando uma celulaLinhaSql corretamente*/
    @Test
    public void adicionaCelulaLinhaSqlCorretamente(){
        CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(1,1);
        Assertions.assertEquals(0,this.linhaSql.getCelulasLinha().size());
        this.linhaSql.adicionar(celulaLinhaSql);
        Assertions.assertEquals(1,this.linhaSql.getCelulasLinha().size());
        Assertions.assertEquals(celulaLinhaSql,this.linhaSql.getCelulasLinha().get(0));

    }
    /**Método que verifica se a classe LinhaSql está adicionando as celulaLinhaSql na variável celulasLinha e não
     * trocando uma já existente.*/
    @Test
    public void adicionaVariasCelulaLinhaSqlCorretamente(){
        CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(1,1);
        CelulaLinhaSql celulaLinhaSql2 = new CelulaLinhaSql(1,2);
        Assertions.assertEquals(0,this.linhaSql.getCelulasLinha().size());
        this.linhaSql.adicionar(celulaLinhaSql);
        this.linhaSql.adicionar(celulaLinhaSql2);
        Assertions.assertEquals(2,this.linhaSql.getCelulasLinha().size());
        Assertions.assertEquals(celulaLinhaSql,this.linhaSql.getCelulasLinha().get(0));
        Assertions.assertNotEquals(celulaLinhaSql,this.linhaSql.getCelulasLinha().get(1));
        Assertions.assertEquals(celulaLinhaSql2,this.linhaSql.getCelulasLinha().get(1));
        Assertions.assertNotEquals(celulaLinhaSql2,this.linhaSql.getCelulasLinha().get(0));

    }

    /**Método que verifica se ao usar o método adicionar ele não adiciona um novo CelulaLinhaSql na variável celulasLinha
     * se já houver uma variável guardando uma CelulaLinhaSql referenciando para a mesma coluna.
     * @see CelulaLinhaSql*/
    @Test
    public void naoAdicionaOutraCelulaLinhaSqlCasoCelulasLinhaComCelulaLinhaSqlDeMesmaColuna(){
        CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(1,1);
        CelulaLinhaSql celulaLinhaSql2 = new CelulaLinhaSql(1,1);
        Assertions.assertEquals(0,this.linhaSql.getCelulasLinha().size());
        this.linhaSql.adicionar(celulaLinhaSql);
        Assertions.assertEquals(1,this.linhaSql.getCelulasLinha().size());
        Assertions.assertTrue(this.linhaSql.getCelulasLinha().contains(celulaLinhaSql));
        this.linhaSql.adicionar(celulaLinhaSql2);
        Assertions.assertEquals(1,this.linhaSql.getCelulasLinha().size());
        Assertions.assertTrue(this.linhaSql.getCelulasLinha().contains(celulaLinhaSql));
        Assertions.assertFalse(this.linhaSql.getCelulasLinha().contains(celulaLinhaSql2));

    }

    @Test
    public void criarEInserirCelulaLinhaSqlComCelulaGuardandoNullNasColunasNaoRefenciadasAoChamarMetodoIncluirNull(){
        ArrayList<Coluna> colunas = new ArrayList<>();
        for (int i = 0;i<=4;i++){
            
            Coluna coluna = new Coluna();
            coluna.adicionar("teste[Int]",i);
            colunas.add(coluna);
        }
        CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(1,0);
        CelulaLinhaSql celulaLinhaSql2 = new CelulaLinhaSql(1,2);
        this.linhaSql.adicionar(celulaLinhaSql);
        this.linhaSql.adicionar(celulaLinhaSql2);
        Assertions.assertNotEquals(colunas.size(),this.linhaSql.getCelulasLinha().size());
        this.linhaSql.incluirNull(colunas);
        Assertions.assertEquals(colunas.size(),this.linhaSql.getCelulasLinha().size());
        Assertions.assertEquals(3,this.linhaSql.getCelula(3).getColuna());
        Assertions.assertEquals("[null]",this.linhaSql.getCelula(3).getCelula().toString());
        Assertions.assertEquals(colunas.size(),this.linhaSql.getCelulasLinha().size());

    }

}
