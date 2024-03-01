package br.com.alura.farmacia.dao;

import br.com.alura.farmacia.modelo.Fabricante;


import javax.persistence.EntityManager;

public class FabricanteDao {

    private EntityManager em;

    public FabricanteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Fabricante fabricante) {
        this.em.persist(fabricante);
    }
    public void atualizar(Fabricante fabricante) {
        this.em.merge(fabricante);
    }

    public void remover(Fabricante fabricante) {
        fabricante= em.merge(fabricante);
        this.em.remove(fabricante);
    }

    public Fabricante buscarFabricantePorId(int id) {
        return em.find(Fabricante.class, id);
    }
}
