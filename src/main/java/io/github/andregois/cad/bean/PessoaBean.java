package io.github.andregois.cad.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.github.andregois.cad.model.Cep;
import io.github.andregois.cad.model.Endereco;
import io.github.andregois.cad.model.Pessoa;
import io.github.andregois.cad.repository.PessoaRepository;
import io.github.andregois.cad.service.ViaCepService;

@Named
@ViewScoped
public class PessoaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(PessoaBean.class.getName());

	private Pessoa pessoa = null;
	private Endereco endereco = null;
	private Long idDoBotao;
	private Pessoa pessoaSelecionada;
	List<Pessoa> pessoas;
	
	@EJB
	private PessoaRepository repository;

	@Inject
	private EnderecoBean enderecoBean;
	
	@Inject
	FacesContext context;
	
	@PostConstruct
	public void init() {
		this.pessoa = new Pessoa();
		this.endereco = new Endereco();
		this.pessoaSelecionada = new Pessoa();
	}

	public void cadastrar() {
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
	
	public void buscarCep(String cep) {
		Cep request;
		try {
			request = ViaCepService.consultaCep(cep);
			enderecoBean.montarCepPorServico(request, this.endereco);
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "CEP não encontrado "+cep);
	        context.addMessage( null, mensagem);
		}		
	}
	
	public void deletar() {
		this.repository.deletar(pessoaSelecionada);
	}
	
	public void editar() {
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
