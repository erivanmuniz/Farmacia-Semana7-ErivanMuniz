package br.com.alura.farmacia.testes;

import br.com.alura.farmacia.dao.FabricanteDao;
import br.com.alura.farmacia.dao.ProdutoDao;
import br.com.alura.farmacia.modelo.Fabricante;
import br.com.alura.farmacia.modelo.Produto;
import br.com.alura.farmacia.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {

        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(2);
        System.out.println("Busca por id mostrando o preço: " + p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDoFabricante("Bayer");
        todos.forEach(p2 -> System.out.println("Busca nome Fabricante " + p.getNome()));

        BigDecimal precoDoProduto = BigDecimal.valueOf(produtoDao.buscarPrecoDoProdutoComNome("Paracetamol"));
        System.out.println("Preco do Produto: " +precoDoProduto);
    }

    private static void cadastrarProduto() {
        Fabricante bayer = new Fabricante("Bayer");
        Produto paracetamol = new Produto("Paracetamol", "Analgésico", 20.88, bayer);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        FabricanteDao fabricanteDao = new FabricanteDao(em);

        em.getTransaction().begin();

        fabricanteDao.cadastrar(bayer);
        produtoDao.cadastrar(paracetamol);


        em.getTransaction().commit();
        em.close();

    }
}