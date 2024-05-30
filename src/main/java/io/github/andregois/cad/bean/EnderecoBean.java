package io.github.andregois.cad.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import io.github.andregois.cad.model.Cep;
import io.github.andregois.cad.model.Endereco;
import io.github.andregois.cad.repository.EnderecoRepository;

@Named
@ViewScoped
public class EnderecoBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private Endereco endereco = null;
	
	@EJB
	private EnderecoRepository enderecoRepository;
	
	@PostConstruct
	public void init() {
		this.setEndereco(new Endereco());
	}
	
	public void montarCepPorServico(Cep cep) {
		this.getEndereco().setCep(cep.getCep().replace("-", ""));
		this.getEndereco().setCidade(cep.getLocalidade());
		this.getEndereco().setEstado(cep.getUf());
		this.getEndereco().setLogradouro(cep.getLogradouro());
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void salvar(Endereco e) {
		enderecoRepository.salvar(e);
	}

	public void montarCepPorServico(Cep cep, Endereco e) {
		e.setCep(cep.getCep().replace("-", ""));
		e.setCidade(cep.getLocalidade());
		e.setEstado(cep.getUf());
		e.setLogradouro(cep.getLogradouro());
	}
}
