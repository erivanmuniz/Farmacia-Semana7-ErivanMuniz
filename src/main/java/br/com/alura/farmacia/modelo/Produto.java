package br.com.alura.farmacia.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@NamedQuery(name = "Produto.produtosPorFabricante",  query= "SELECT p FROM Produto p WHERE p.fabricante.nome = :nome")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private LocalDate dataCadastro =  LocalDate.now();
    private boolean ativo;


    @ManyToOne(fetch = FetchType.LAZY)
    private Fabricante fabricante;

    public Produto() {
    }

    public Produto(String nome, String descricao, double preco, Fabricante fabricante) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.fabricante = fabricante;
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
