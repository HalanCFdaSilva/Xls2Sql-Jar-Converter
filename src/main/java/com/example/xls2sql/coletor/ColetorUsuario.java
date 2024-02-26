package com.example.xls2sql.coletor;

import com.example.xls2sql.xls.AnalisadorEndereco;
import com.example.xls2sql.coletor.domain.InfoUsuario;

import java.util.Scanner;

/**<p>Esta classe é a que faz toda a interação com o usuário pegando as informações necessárias do usuário e
 * as guardando na classe InfoUsuario</p>
 * @author Halan Silva
 * <p></p>
 * @see InfoUsuario*/
public class ColetorUsuario {

    /**<p>Parâmetro em que são guardados os dados pegos por essa classe e da onde é pega toda informação vinda
     * do usuário.</p>
     * @see InfoUsuario*/
    private final InfoUsuario infoUsuario;



    public ColetorUsuario() {
        this.infoUsuario = new InfoUsuario();

    }

    /**<p>Método principal que agrega os métodos internos e coleta as informações do usuário com a ajuda da classe
     * Scanner</p>
     * @see  <a href= https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html>Scanner</a>*/
    public void coletar(){

        Scanner scanner = new Scanner(System.in);

        this.pegarNomeBancoDeDados(scanner);
        this.pegarNomeTabelaSql(scanner);

        this.pegarEnderecoXls(scanner);
        this.pegarEnderecoSalvarSql(scanner);

        scanner.close();


    }

    /**<p>Método que coleta do usuário e salva na variável InfoUsuario o nome do banco de dados onde ficara a tabela
     * gerada pelo programa.</p>
     * @param scanner  Recebe uma instância da classe Scanner que será usada para pega as informações escritas pelo usuário.
     * <p></p>
     * @see  <a href= https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html>Scanner</a>
     * @see InfoUsuario*/
    private void pegarNomeBancoDeDados(Scanner scanner) {
        System.out.println("Digite o nome do banco de dados");
        infoUsuario.setNomeBancoDados(scanner.nextLine());
    }

    /**<p>Método que coleta e salva o nome da tabela na variável infoUsuário conforme escrito pelo usuário.</p>
     * @param scanner  Recebe uma estância da classe Scanner que será usada para pegar
     *                        as informações escritas pelo usuário.
     * <p></p>
     * @see  <a href= https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html>Scanner</a>
     * @see InfoUsuario*/

    private void pegarNomeTabelaSql(Scanner scanner) {
        System.out.println("Digite o nome da tabela sql que deseja usar");
        infoUsuario.setNomeTabela(scanner.nextLine());

    }

    /**<p>Método que coleta do usuário e faz as verificações e salva na variável InfoUsuario os dados passados pelo usuário
     * sobre o local do arquivo xls/xlsx.</p>
     * @param scanner  Recebe uma instância da classe Scanner que será usada para pegar
     *                        as informações escritas pelo usuário.
     * <p></p>
     * @see  <a href= https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html>Scanner</a>
     * @see InfoUsuario*/
    private void pegarEnderecoXls(Scanner scanner) {

        System.out.println("Digite o endereço do arquivo xls");
        String enderecoXls = scanner.nextLine();
        while (!AnalisadorEndereco.verificarArquivoXls(enderecoXls)){
            System.out.println("não foi possível encontrar o endereço digite de novo");
            enderecoXls = scanner.nextLine();
        }
        infoUsuario.setEnderecoXls(enderecoXls);
    }



    /**<p>Método que pega coleta faz as verificações e salva na variável InfoUsuario os dados passados pelo usuário sobre
     *  a pasta em que deseja salvar o arquivo Sql.</p>
     * @param scanner  Recebe uma instância da classe Scanner que será usada para pegar
     *                        as informações escritas pelo usuário.
     * <p></p>
     * @see  <a href= https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html>Scanner</a>
     * @see InfoUsuario*/
    private void pegarEnderecoSalvarSql(Scanner scanner) {
        System.out.println("Digite o endereço da pasta em que quer salvar o arquivo SQL");
        String enderecoDoSql = scanner.nextLine();
        while (!AnalisadorEndereco.verificarEnderecoSql(enderecoDoSql)){
            System.out.println("não foi possível identificar a pasta ha ser salva.\n Por favor digite novamente.");
            enderecoDoSql = scanner.nextLine();
        }
        infoUsuario.setEnderecoSql(enderecoDoSql);
    }




    /** Método que retorna a instância da variável infoUsuário*/

    public InfoUsuario getInfoUsuario() {
        return infoUsuario;
    }
}
