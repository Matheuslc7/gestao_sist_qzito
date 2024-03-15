/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qzito.m7.gestaov.modelo.dominio;

import java.math.BigDecimal;

/**
 *
 * @author Qzito 10
 */
public class VendaItem {
    
    private venda venda;
    private produto produto;
    private Integer quantidade;
    private BigDecimal total;
    private BigDecimal desconto;

    public VendaItem() {
    }

    public VendaItem(venda venda, produto produto, Integer quantidade, BigDecimal total, BigDecimal desconto) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.total = total;
        this.desconto = desconto;
    }

    public venda getVenda() {
        return venda;
    }

    public void setVenda(venda venda) {
        this.venda = venda;
    }

    public produto getProduto() {
        return produto;
    }

    public void setProduto(produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }
    
    
    
}
