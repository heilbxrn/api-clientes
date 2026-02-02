package br.com.cotiinformatica.api_clientes;

import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiClientesApplication implements CommandLineRunner {

	@Autowired
	private ConnectionFactory connectionFactory;

	public static void main(String[] args) {

		SpringApplication.run(ApiClientesApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		try {
			connectionFactory.getConnection();
			System.out.println("\nCONEXÂO COM O BANCO DE DADOS REALIZADA COM SUCESSO!");
		}
		catch(Exception e) {
			System.out.println("\nFALHA AO CONECTAR COM O BANCO DE DADOS!");
			System.out.println(e.getMessage());
		}
	}
}