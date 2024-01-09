package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.TipoDadosSql;
import com.example.xls2sql.sql.exceptions.TextoColunaVaziaException;
import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColunaTest {


    private Coluna coluna;

    @BeforeEach
    public void criarColuna(){
        this.coluna = new  Coluna();
    }

    @Test
    public void adicionarNomeNaColuna(){

        coluna.adicionar("Pratos[int]",1);
        Assertions.assertEquals("Pratos", coluna.getNome());
    }

    @Test
    public void adicionarTipoDadoSemTamanhoDeElementosNaColuna(){

        coluna.adicionar("Pratos[int]",1);
        Assertions.assertEquals(TipoDadosSql.INT, coluna.getTipo().getTipo());
    }

    @Test
    public void adicionarTipoDadoComTipoTodoEmMaiusculoNaString(){

        coluna.adicionar("Pratos[INT]",1);
        Assertions.assertEquals(TipoDadosSql.INT, coluna.getTipo().getTipo());
    }

    @Test
    public void adicionarTipoDadoENumeroElementosNaColuna(){

        coluna.adicionar("Pratos[varchar(10)]",1);
        Assertions.assertEquals(TipoDadosSql.VARCHAR, coluna.getTipo().getTipo());
        Assertions.assertEquals(10, coluna.getTipo().getNumeroElementosInteger());
    }

    @Test
    public void darTipoDadoSqlNaoEncontradoException(){



        Assertions.assertThrows(TextoColunaVaziaException.class,() -> coluna.adicionar("",1));
    }
}
