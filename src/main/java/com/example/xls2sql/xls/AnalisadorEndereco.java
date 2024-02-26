package com.example.xls2sql.xls;

import java.io.File;
/**<p>Esta classe serve de suporte para a classe ColetorUsuario onde ela verificar se o endereço do arquivo xls
 * passado pelo usuário realmente existe e se o arquivo realmente é um xls/xlsx.</p>
 * @author Halan Silva
 * <p></p>
 * @see com.example.xls2sql.coletor.ColetorUsuario*/
public class AnalisadorEndereco {

    /**<p>Este método verifica se o endereço passado pelo usuário realmente existe e retorna o resultado</p>
     * @param endereco Deve ser o endereço no hd a verificar se existe*/
    private static boolean verificarEnderecoExiste(String endereco){
        File file = new File(endereco);
        return file.exists();
    }

    /**<p>Este método verifica se o endereço do arquivo passado pelo usuário tem a extensão xls ou xlsx e retorna
     * o resultado</p>
     * @param enderecoXls Deve ser o endereço do arquivo a verificar se é um xls*/
    private static  boolean verificarExtensaoXls(String enderecoXls){

        return enderecoXls.endsWith(".xls") || enderecoXls.endsWith(".xlsx");

    }

    /** <p>Este método verifica se o endereço passado pelo usuário é de algum arquivo existente no
     * computador e retorna o resultado</p>
     * @param endereco Deve ser o endereço no hd a verificar se é de um arquivo*/
    private static boolean verificarEnderecoEArquivo(String endereco) {
        File file = new File(endereco);
        return file.isFile();
    }

    /**<p>Este método chama os dois métodos anteriores fazendo a ponte entre eles e o usuário.</p>
     * <p>Ele pega o resultado dos dois e somente se os dois derem true ele retorna um true, ou seja
     * o arquivo deve existir e ter a extensão xls/xlsx</p>
     * @param enderecoXls Deve ser o endereço do arquivo a ser verificado*/
    public  static boolean verificarArquivoXls(String enderecoXls){
        return AnalisadorEndereco.verificarEnderecoEArquivo(enderecoXls) && AnalisadorEndereco.verificarExtensaoXls(enderecoXls);
    }

    /**<p>Este método verifica se o endereço é de uma pasta,fazendom a ponte entre os métodos
     * verificarEnderecoExiste que verifica se o endereço existe e o método verificarEnderecoEArquivo
     * que verificar se o endereço é de um arquivo. </p>
     * <p> Neste caso o resultado sendo verdadeiro no primeiro método e falso no segundo ele retorna verdade,
     * e em qualquer outra variação de resultado ele retorna falso.</p>
     * @param enderecoSql deve ser o endereço da pasta em que deseja salvar o arquivo sql.*/
    public static boolean verificarEnderecoSql(String enderecoSql) {
        return AnalisadorEndereco.verificarEnderecoExiste(enderecoSql) &&
                !AnalisadorEndereco.verificarEnderecoEArquivo(enderecoSql);
    }


}
