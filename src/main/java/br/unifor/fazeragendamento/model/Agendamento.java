package br.unifor.fazeragendamento.model;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private ServicoEnum servico;

    private String nomeFuncionario;

    private String nomePet;

    private String nomeCliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ServicoEnum getServico() {
        return servico;
    }

    public void setServico(ServicoEnum servico) {
        this.servico = servico;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    // Getters e Setters

    
}