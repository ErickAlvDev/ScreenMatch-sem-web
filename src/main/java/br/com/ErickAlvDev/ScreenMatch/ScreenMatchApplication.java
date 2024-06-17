package br.com.ErickAlvDev.ScreenMatch;

import br.com.ErickAlvDev.ScreenMatch.model.DadosSerie;
import br.com.ErickAlvDev.ScreenMatch.service.ConsumoApi;
import br.com.ErickAlvDev.ScreenMatch.service.ConverterDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}
}
