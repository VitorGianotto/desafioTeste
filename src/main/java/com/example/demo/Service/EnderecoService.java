package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Endereco;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.EnderecoRepository;
import com.example.demo.Repository.PessoaRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	PessoaRepository pessoaRepository;

	public Endereco criarEndereco(Endereco endereco, Long pessoaId) throws Exception {
		try {
			Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
			for(Endereco enderecoPessoa : pessoa.getEnderecos()) {
				enderecoRepository.deleteById(enderecoPessoa.getId());
			}
			endereco.setPessoa(pessoa);
			pessoa.getEnderecos().add(endereco);
			return enderecoRepository.save(endereco);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	public void deletarEndereco(Long id) throws Exception {
		try {
			enderecoRepository.deleteById(id);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	public List<Endereco> listarEnderecosPorId(Long pessoaId) throws Exception {
		try {
			List<Endereco> enderecosDaPessoa = new ArrayList<>();
			preencheEnderecos(pessoaId, enderecosDaPessoa);
			return enderecosDaPessoa;
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	private void preencheEnderecos(Long pessoaId, List<Endereco> enderecosDaPessoa) {
		for(Endereco endereco : enderecoRepository.findAll()) {
			if(endereco.getPessoa().getId().equals(pessoaId)) {
				enderecosDaPessoa.add(endereco);
			}
		}
	}
}
