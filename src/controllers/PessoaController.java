package controllers;

import java.util.Scanner;

import entities.Pessoa;
import repositories.PessoaRepository;

public class PessoaController {

	//método para realizar o cadastro de pessoa no sistema
	public void cadastrarPessoa() {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
		
			System.out.println("\n *** CADASTRO DE PESSOA *** \n");
			
			Pessoa pessoa = new Pessoa();
			
			System.out.print("Informe o nome da pessoa.....: ");
			pessoa.setNome(scanner.nextLine());
			
			System.out.print("Informe o cpf da pessoa......: ");
			pessoa.setCpf(scanner.nextLine());
			
			System.out.print("Informe o email da pessoa....: ");
			pessoa.setEmail(scanner.nextLine());
			
			//gravando no banco de dados
			PessoaRepository pessoaRepository = new PessoaRepository();
			pessoaRepository.create(pessoa);
			
			System.out.println("\nPessoa cadastrada com sucesso.");
		}
		catch(Exception e) {
			
			System.out.println("\nErro: " + e.getMessage());
		}		
	}
	
}
