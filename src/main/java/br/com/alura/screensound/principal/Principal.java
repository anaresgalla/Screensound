package br.com.alura.screensound.principal;

import br.com.alura.screensound.model.Artista;
import br.com.alura.screensound.model.Musica;
import br.com.alura.screensound.model.TipoArtista;
import br.com.alura.screensound.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

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
        System.out.println("Buscar músicas de que artista? ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();

        artistas.forEach(artista -> {
            System.out.println("Artista: " + artista.getNome());
            artista.getMusicas().forEach(musica ->
                    System.out.println("  - " + musica.getNome())
            );
        });

        //artistas.forEach(a -> a.getMusicas().forEach(System.out::println); //Lista por música
    }

    private void cadastrarMusica() {
        System.out.println("Cadastrar música de que artista?");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if(artista.isPresent()){
            System.out.println("Informe o título da música:");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void cadastrarArtista() {
        var cadastrarNovo = "S";

        while(cadastrarNovo.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome do artista: ");
            var nome = leitura.nextLine();
            System.out.println("Informe o tipo (Solo, Dupla ou Banda): ");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);
            System.out.println("Fazer outro cadastro? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
