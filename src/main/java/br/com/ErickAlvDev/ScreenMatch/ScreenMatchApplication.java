package br.com.ErickAlvDev.ScreenMatch;

import br.com.ErickAlvDev.ScreenMatch.model.DadosEpisodios;
import br.com.ErickAlvDev.ScreenMatch.model.DadosSerie;
import br.com.ErickAlvDev.ScreenMatch.model.DadosTemporada;
import br.com.ErickAlvDev.ScreenMatch.service.ConsumoApi;
import br.com.ErickAlvDev.ScreenMatch.service.ConverterDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();

		String endereco = "https://www.omdbapi.com/?t=gilmore+girls&apikey=e657f6cf";
		System.out.println(endereco);

		var json = consumoApi.obterDados(endereco);
		System.out.println(json);
		ConverterDados conversor = new ConverterDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=e657f6cf");
		DadosEpisodios dadosEpisodios = conversor.obterDados(json, DadosEpisodios.class);
		System.out.println(dadosEpisodios);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas() ; i++) {
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season="+ i + "&apikey=e657f6cf");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
	}
}
