package io.github.andregois.cad.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.github.andregois.cad.model.Endereco;
import io.github.andregois.cad.model.Pessoa;
import io.github.andregois.cad.repository.EnderecoRepository;
import io.github.andregois.cad.repository.PessoaRepository;

@Named
@ViewScoped
public class PessoaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa = null;
	private Endereco endereco = null;
	private Long idDoBotao;
	private Pessoa pessoaSelecionada;
	List<Pessoa> pessoas;
	
	@Inject
	private PessoaRepository repository;
	@Inject
	private EnderecoRepository enderecoRepository;
	
	@Inject
	FacesContext context;
	
	@PostConstruct
	public void init() {
		this.pessoa = new Pessoa();
		this.endereco = new Endereco();
		this.pessoaSelecionada = new Pessoa();
	}

	public void cadastrar() {
		System.out.println("Nome: "+this.pessoa.getNome());
		System.out.println(endereco.getCidade());
		enderecoRepository.salvar(endereco);
		
		System.out.println(endereco.getId());
		this.pessoa.setEndereco(endereco);
		this.repository.salvar(getPessoa());
		this.pessoa = new Pessoa();
		this.endereco = new Endereco();
	}
	
	public List<Pessoa> getPessoas() {
		pessoas = this.repository.listAll();
		if(pessoas != null) {
			return pessoas;
		}
		return null;
	}
	
	public void deletar() {
		this.repository.deletar(pessoaSelecionada);
	}
	
	public void editar() {
		System.out.println("TESTE");
		this.repository.editar(pessoaSelecionada);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getIdDoBotao() {
		return idDoBotao;
	}

	public void setIdDoBotao(Long idDoBotao) {
		this.idDoBotao = idDoBotao;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	
	
}
