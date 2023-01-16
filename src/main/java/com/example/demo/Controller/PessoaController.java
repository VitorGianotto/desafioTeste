package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Pessoa;
import com.example.demo.Service.PessoaService;

@RestController
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;

	@PostMapping("/criarPessoa")
	public Pessoa criarPessoa(@RequestBody Pessoa pessoa) throws Exception {
		return pessoaService.criarPessoa(pessoa);
	}
	
	@GetMapping("/listarPessoas")
	public List<Pessoa> listarPessoas() throws Exception {
		return pessoaService.listarPessoas();
	}
	
	@GetMapping("/listarPessoaPorId/{id}")
	public Pessoa listarPessoaPorId(@PathVariable Long id) throws Exception {
		return pessoaService.listarPessoaPorId(id);
	}
	
	@PutMapping("/atualizarPessoa/{id}")
	public Pessoa atualizarPessoa(@RequestBody Pessoa pessoa, @PathVariable Long id) throws Exception {
		return pessoaService.atualizarPessoa(pessoa, id);
	}
}
