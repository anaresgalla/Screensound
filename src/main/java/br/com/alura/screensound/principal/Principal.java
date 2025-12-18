package br.com.alura.screensound.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;
        
        while(opcao != 0) {
            var menu = """
                    *** SCREENSOUND MÚSICAS ***
                    
                    1 - Cadastrar Artista
                    2 - Cadastrar Música
                    3 - Listar Músicas
                    4 - Buscas Músicas por Artistas
                    5 - Pesquisar Dados sobre um Artista
                    
                    0 - Sair
                     
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            
            switch (opcao){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação...");
                    break;
                default:
                    System.out.println("Opção Inválida");
                    
                
            }
            
        }
    }

    private void pesquisarDadosDoArtista() {
    }

    private void buscarMusicasPorArtista() {
        
    }

    private void listarMusicas() {
        
    }

    private void cadastrarMusica() {
        
    }

    private void cadastrarArtista() {
    }
}
