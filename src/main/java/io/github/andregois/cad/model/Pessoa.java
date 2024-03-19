package io.github.andregois.cad.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.github.andregois.cad.enuns.SexoEnum;

@Entity
@Table(name = "tb_pessoas")
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "seq_pes", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer idade;
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	@OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
	
	public Pessoa() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	public List<SexoEnum> getSexoEnumValues() {
        return Arrays.asList(SexoEnum.values());
    }
}
