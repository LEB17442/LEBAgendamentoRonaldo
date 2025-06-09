package br.unifor.fazeragendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FazeragendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FazeragendamentoApplication.class, args);
	}

}
