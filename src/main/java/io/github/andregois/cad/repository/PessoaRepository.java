package io.github.andregois.cad.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.github.andregois.cad.model.Pessoa;

@Stateless
public class PessoaRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<Pessoa> listAll(){
		String sql = "select p from Pessoa p ";
		TypedQuery<Pessoa> query = em.createQuery(sql, Pessoa.class);
		return query.getResultList();
	}

	public void salvar(Pessoa p) {
		em.persist(p);
	}

	public void editar(Pessoa p) {
		em.merge(p);
	}
	
	public void deletar(Pessoa p) {
		Pessoa pessoa = em.find(Pessoa.class, p.getId());
		if(pessoa != null)
			em.remove(pessoa);
	}

}
