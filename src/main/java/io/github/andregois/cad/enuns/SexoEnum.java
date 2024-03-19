package io.github.andregois.cad.enuns;

public enum SexoEnum {

	MASCULINO("M", "Masculino"), 
	FEMININO("F", "Feminino");

	private String codigo;
	private String descricao;

	SexoEnum(String codigo, String descricao) {
		System.out.println(codigo +" - "+descricao);
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	// Método auxiliar para converter código em enum
	public static SexoEnum fromCodigo(String codigo) {
		for (SexoEnum sexo : SexoEnum.values()) {
			if (sexo.codigo.equals(codigo)) {
				return sexo;
			}
		}
		throw new IllegalArgumentException("Código de sexo inválido: " + codigo);
	}
}
