package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Model.Endereco;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.EnderecoRepository;
import com.example.demo.Repository.PessoaRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DesafioTesteApplicationTests {
	
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

	@Test
	@Order(1)
	public void testeCriarPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Nome Teste");
		pessoa.setDataNascimento(new Date());
		pessoa.setEnderecos(new ArrayList<>());
		pessoaRepository.save(pessoa);
		assertNotNull(pessoaRepository.findAll());
	}
	
	@Test
	@Order(2)
	public void testeAtualizarPessoa() {
		Pessoa pessoa = pessoaRepository.findById(1L).get();
		pessoa.setNome("Nome Atualizado");
		pessoaRepository.save(pessoa);
		assertEquals("Nome Atualizado", pessoaRepository.findById(1L).get().getNome());
	}
	
	@Test
	@Order(3)
	public void testeConsultarPessoaPorId() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Nome Dois");
		pessoa.setDataNascimento(new Date());
		List<Endereco> enderecos = new ArrayList<>();
		Endereco end1 = new Endereco();
		end1.setCep("cepTeste");
		end1.setCidade("cidadeTeste");
		end1.setLogradouro("lograTeste");
		end1.setNumero("numteste");
		end1.setPessoa(pessoa);
		enderecos.add(end1);
		pessoa.setEnderecos(enderecos);
		pessoaRepository.save(pessoa);
		assertNotNull(pessoaRepository.findById(2L));
	}
	
	@Test
	@Order(4)
	public void testeListarTodasAsPessoas() {
		assertThat(pessoaRepository.findAll().size() > 1);
	}
	
	@Test
	@Order(5)
	public void testeListarEnderecosDaPessoa() {
		List<Endereco> enderecos = new ArrayList<>();
		for(Endereco endereco : enderecoRepository.findAll()) {
			if(endereco.getPessoa().getId().equals(2L)) {
				enderecos.add(endereco);
			}
		}
		assertThat(enderecos.size() == 2);
	}

}
