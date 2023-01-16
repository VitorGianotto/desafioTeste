package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Endereco;
import com.example.demo.Service.EnderecoService;

@RestController
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping("/criarEndereco/{pessoaId}")
	public Endereco criarEndereco(@RequestBody Endereco endereco, @PathVariable Long pessoaId) throws Exception {
		return enderecoService.criarEndereco(endereco, pessoaId);
	}
	
	@GetMapping("/listarEnderecosPorId/{pessoaId}")
	public List<Endereco> listarEnderecosPorId(@PathVariable Long pessoaId) throws Exception {
		return enderecoService.listarEnderecosPorId(pessoaId);
	}
}
