package io.github.andregois.cad.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.github.andregois.cad.model.Endereco;

@Stateless
public class EnderecoRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<Endereco> listAll(){
		String sql = "select e from Endereco e ";
		TypedQuery<Endereco> query = em.createQuery(sql, Endereco.class);
		return query.getResultList();
	}

	public void salvar(Endereco e) {
		em.persist(e);
	}

	public void editar(Endereco e) {
		em.merge(e);
	}
	
	public void deletar(Endereco e) {
		Endereco end = em.find(Endereco.class, e.getId());
		if(end != null)
			em.remove(end);
	}
	
}
