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
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Pessoa criarPessoa(Pessoa pessoa) throws Exception {
		try {
			return pessoaRepository.save(pessoa);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	public List<Pessoa> listarPessoas() throws Exception{
		try {
			return pessoaRepository.findAll();
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	public Pessoa listarPessoaPorId(Long id) throws Exception {
		try {
			return pessoaRepository.findById(id).get();
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	public Pessoa atualizarPessoa(Pessoa pessoa, Long id) {
			Pessoa pessoaAtualizada = pessoaRepository.findById(id).get();
			
			if(pessoaAtualizada == null) {
				return null;
			}
			atualizarDados(pessoa, pessoaAtualizada);
			return pessoaRepository.save(pessoaAtualizada);
	}

	private void atualizarDados(Pessoa pessoa, Pessoa pessoaAtualizada) {
		List<Endereco> enderecosNovos = new ArrayList<>();
		pessoaAtualizada.setNome(pessoa.getNome());
		pessoaAtualizada.setDataNascimento(pessoa.getDataNascimento());
		pessoaAtualizada.setEnderecos(null);
		pessoaRepository.save(pessoaAtualizada);
		atualizarEnderecos(pessoa, enderecosNovos);
		pessoaAtualizada.setEnderecos(enderecosNovos);
	}

	private void atualizarEnderecos(Pessoa pessoa, List<Endereco> enderecosNovos) {
		for(Endereco enderecoPessoa : enderecoRepository.findAll()) {
			if(pessoa.getId() == enderecoPessoa.getPessoa().getId()) {
				enderecoPessoa.setPessoa(null);
				enderecoRepository.save(enderecoPessoa);
				enderecoRepository.deleteById(enderecoPessoa.getId());
			}
		}
		for(Endereco enderecoNovo : pessoa.getEnderecos()) {
			enderecoNovo.setPessoa(pessoa);
			enderecosNovos.add(enderecoNovo);
		}
	}
}
