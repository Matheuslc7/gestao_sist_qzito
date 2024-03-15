/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qzito.m7.gestaov.controller;

import com.qzito.m7.gestaov.dao.conexao.Conexao;
import com.qzito.m7.gestaov.dao.conexao.ConexaoMysql;
import com.qzito.m7.gestaov.modelo.dominio.categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Qzito 10
 */
public class Main {
    
    public static void main(String[] args) throws SQLException {
        
        String sql ="Select * from categoria";
        
        Conexao conexao = new ConexaoMysql();
        
        categoria categoria = new categoria(null,"Calca Skinny pt", "Inserção netbeans");
        
        String inserirSQL = "INSERT INTO categoria(nome, descricao) VALUES(?, ?)";
        
        PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(inserirSQL);
        
        preparedStatement.setString(1, categoria.getNome());
        preparedStatement.setString(2, categoria.getDescricao());
        
        int resultadoCadastro = preparedStatement.executeUpdate();
        
        System.out.println(resultadoCadastro);
        
        ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
        
        while (result.next()){
            System.out.println(result.getString("nome"));
        }
    }
    
}
