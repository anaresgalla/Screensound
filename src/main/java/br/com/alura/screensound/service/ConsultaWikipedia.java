package br.com.alura.screensound.service;

import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaWikipedia {

    private static final WebClient client = WebClient.builder()
            .baseUrl("https://pt.wikipedia.org")
            .build();

    public static String obterInformacao(String artista) {

        try {
            String termo = URLEncoder.encode(artista.trim(), StandardCharsets.UTF_8);

            // 1️⃣ Buscar título correto
            WikipediaBusca busca = client.get()
                    .uri("/w/rest.php/v1/search/title?q={q}&limit=1", termo)
                    .retrieve()
                    .bodyToMono(WikipediaBusca.class)
                    .block();

            if (busca == null || busca.getPages().isEmpty()) {
                return "Artista não encontrado na Wikipedia.";
            }

            String tituloCorreto = busca.getPages().get(0).getTitle();

            // 2️⃣ Buscar resumo
            WikipediaResumo resumo = client.get()
                    .uri("/api/rest_v1/page/summary/{titulo}", tituloCorreto)
                    .retrieve()
                    .bodyToMono(WikipediaResumo.class)
                    .block();

            if (resumo == null || resumo.getExtract() == null) {
                return "Resumo não disponível para este artista.";
            }

            return limitarPorFrases(resumo.getExtract(), 3);

        } catch (Exception e) {
            return "Erro ao consultar a Wikipedia.";
        }
    }

    private static String limitarPorFrases(String texto, int maxFrases){
        if (texto == null || texto.isBlank()){
            return "Resumo indisponível.";
        }

        String[] frases = texto.split("(?<=[.!?])\\s+");

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < Math.min(frases.length, maxFrases); i++) {
            resultado.append(frases[i]).append(" ");
        }

        return resultado.toString().trim();
    }
}
