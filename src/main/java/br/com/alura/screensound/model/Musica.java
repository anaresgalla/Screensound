    package br.com.alura.screensound.model;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "musicas")
    public class Musica {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titulo;

        @ManyToOne
        private Artista artista;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return titulo;
        }

        public void setNome(String nome) {
            this.titulo = nome;
        }

        public Artista getArtista() {
            return artista;
        }

        public void setArtista(Artista artista) {
            this.artista = artista;
        }

        @Override
        public String toString() {
            return "Música: '" + titulo + '\'' +
                    " - Artista: " + artista;
        }
    }
