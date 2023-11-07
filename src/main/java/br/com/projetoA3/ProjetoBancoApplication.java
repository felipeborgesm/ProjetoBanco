package br.com.projetoA3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition(
    info =
        @Info(
            title = "API Sistema Bancario",
            version = "2.0",
            description =
                "Essa API simula um sistema bancário, permitindo a criação de um usuario, que pode criar uma conta"
                    + " e que pode fazer transações para outras contas de outros usuarios"))
public class ProjetoBancoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProjetoBancoApplication.class, args);
  }
}
