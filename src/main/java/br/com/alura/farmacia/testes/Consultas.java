package br.com.alura.farmacia.testes;

import br.com.alura.farmacia.dao.ProdutoDao;
import br.com.alura.farmacia.modelo.Produto;
import br.com.alura.farmacia.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class Consultas {

    public static void main(String[] args) {


        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();

        // Testando o método que busca todos os produtos
        /*List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(p -> System.out.println("Consulta pelo método buscar todos: " + p.getNome()));

        // Testando busca por id
        Produto p = produtoDao.buscarPorId(1);
        System.out.println("Consulta pelo id do Produto: " + p.getPreco());

        // Testando o método para atualizar o produto
        Produto produto = produtoDao.buscarPorId(1);
        produto.setDescricao("Analgésico");
        produto.setNome("Nimesulida");
        produto.setPreco(20.00);
        produtoDao.atualizar(produto);

        // Testando método que busca produto por nome
        List<Produto> todos1 = produtoDao.buscarPorNome("Nimesulida");
        todos1.forEach(p2 -> System.out.println("Consulta pelo nome do produto: " + p2.getNome()));

        // Testando método que busca produto por nome do Fabricante
        List<Produto> todos2 = produtoDao.buscarPorNomeDoFabricante("Bayer");
        todos2.forEach(p3 -> System.out.println("Consulta pelo nome do Fabricante: " + p3.getNome()));*/

        // Testando método que remove produto por ID
        Produto produtoParaRemover = produtoDao.buscarPorId(1); // Supondo que você queira remover o produto com ID 1
        produtoDao.remover(produtoParaRemover);

        em.getTransaction().commit();
        em.close();

    }
}
