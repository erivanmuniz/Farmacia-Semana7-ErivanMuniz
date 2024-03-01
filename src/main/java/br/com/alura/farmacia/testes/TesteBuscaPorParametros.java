package br.com.alura.farmacia.testes;

import br.com.alura.farmacia.dao.FabricanteDao;
import br.com.alura.farmacia.dao.ProdutoDao;
import br.com.alura.farmacia.modelo.Fabricante;
import br.com.alura.farmacia.modelo.Produto;
import br.com.alura.farmacia.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TesteBuscaPorParametros {

    public static void main(String[] args) {
        //insereDadosNoBanco();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        List<Produto> produtos = produtoDao.buscarPorParametros("amoxicilina", BigDecimal.valueOf(57.25), LocalDate.now());
        produtos.forEach(p -> System.out.println(p.getNome() + " " +  p.getPreco() + " " +   LocalDate.now()));
    }

    private static void insereDadosNoBanco() {
        Fabricante ems = new Fabricante("EMS");
        Fabricante neoQuimica = new Fabricante("Neo Quimica");
        Fabricante euroFarma = new Fabricante("Euro Farma");

        Produto amoxicilina = new Produto("Amoxicilina", "Antibiótico", 57.25, ems);
        Produto torsilax  = new Produto("Torsilax", "Para dor muscular", 37.65, neoQuimica);
        Produto polaramine = new Produto("Polaramine", "Antialérgico", 19, euroFarma);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        FabricanteDao fabricanteDao = new FabricanteDao(em);

        em.getTransaction().begin();

        fabricanteDao.cadastrar(ems);
        fabricanteDao.cadastrar(neoQuimica);
        fabricanteDao.cadastrar(euroFarma);

        produtoDao.cadastrar(amoxicilina);
        produtoDao.cadastrar(torsilax);
        produtoDao.cadastrar(polaramine);

        em.getTransaction().commit();
        em.close();
    }

}

