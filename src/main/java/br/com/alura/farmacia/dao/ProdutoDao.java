package br.com.alura.farmacia.dao;

import br.com.alura.farmacia.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }
    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        produto = em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(int id) {
        return em.find(Produto.class, id);
    }

    //Método para buscar todos os produtos
    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }


    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }
    public List<Produto> buscarPorNomeDoFabricante(String nome) {
        return em.createNamedQuery("Produto.produtosPorFabricante", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public double buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        Double precoDouble = em.createQuery(jpql, Double.class)
                .setParameter("nome", nome)
                .getSingleResult();
        // Retorna o Double diretamente
        return precoDouble;
    }

    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
        String jpql = "SELECT p FROM Produto p WHERE 1=1 ";

        if (nome != null && !nome.trim().isEmpty()) {
            jpql += " AND p.nome = :nome ";
        }
        if (preco != null) {
            jpql += " AND p.preco <= :preco ";
        }
        if (dataCadastro != null) {
            jpql += " AND p.dataCadastro = :dataCadastro ";
        }

        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (preco != null) {
            query.setParameter("preco", preco.doubleValue());
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }

        return query.getResultList();
    }







}
