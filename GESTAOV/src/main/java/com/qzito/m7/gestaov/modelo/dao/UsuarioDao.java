/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qzito.m7.gestaov.modelo.dao;

import com.qzito.m7.gestaov.dao.conexao.Conexao;
import com.qzito.m7.gestaov.dao.conexao.ConexaoMysql;
import com.qzito.m7.gestaov.modelo.dominio.Perfil;
import com.qzito.m7.gestaov.modelo.dominio.usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Qzito 10
 */
public class UsuarioDao {
    
private final Conexao conexao;

    public UsuarioDao() {
        this.conexao = new ConexaoMysql();
    }
    
    public String salvar(usuario usuario) {
        return usuario.getId() == 0L ? adicionar(usuario) : editar(usuario);
    }
    
    private String adicionar(usuario usuario) {
        String sql ="INSERT INTO usuario(nome, usuario, senha, perfil, estado) VALUES(?, ?, ?, ?, ?)";
        
        usuario usuarioTemp = buscarUsuarioPeloUsuario(usuario.getUsuario());
        
        if(usuarioTemp != null) {
            return String.format("Error: Usuario %s já existe no banco de dados", usuario.getUsuario());
        }
        
        
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            
            preencherValoresPreparedStatement(preparedStatement, usuario);
            
            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ? "Usuario adicionado com sucesso" : "Não foi possível adicionar usuário";
           
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }
    
    private String editar(usuario usuario) {
        String sql ="UPDATE categoria SET nome = ?, usuario = ?, senha = ?, perfil = ?, estado = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            
            preencherValoresPreparedStatement(preparedStatement, usuario);
            
            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ? "Usuário editado com sucesso" : "Não foi possível editar usuário";           
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    
}

    private void preencherValoresPreparedStatement(PreparedStatement preparedStatement, usuario usuario) throws SQLException {
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getUsuario());
        preparedStatement.setString(3, usuario.getSenha());
        preparedStatement.setString(4, usuario.getPerfil().name());
        preparedStatement.setBoolean(5, usuario.isEstado());
        
        if(usuario.getId() != 0L) {
            preparedStatement.setLong(6, usuario.getId());
        }
        
    }
    
    public List<usuario> bucarTodosUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<usuario> usuarios = new ArrayList<>();
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            
            while (result.next()) {
                usuarios.add(getUsuario(result));
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error", e.getMessage()));
        }
        
        return usuarios;
    }
    
    private usuario getUsuario(ResultSet result) throws SQLException {
        usuario usuario = new usuario();
        
        usuario.setId(result.getLong("id"));
        usuario.setNome(result.getString("nome"));
        usuario.setUsuario(result.getString("usuario"));
        usuario.setSenha(result.getString("senha"));
        usuario.setPerfil(result.getObject("pergil", Perfil.class));
        usuario.setEstado(result.getBoolean("estado"));
        usuario.setDataHoraCriacao(result.getObject("data hora criacao", LocalDateTime.class));
        usuario.setUltimoLogin(result.getObject("ultimo login", LocalDateTime.class));
        
        return usuario;
        
    }
    
    public usuario buscarUsuarioPeloId(Long id) {
        String sql = String.format("SELECT * FROM usuario WHERE id == %d", id);
        
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            
            if (result.next()) {
                return getUsuario(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error", e.getMessage()));
        }
        
        return null;
    }
    
    public usuario buscarUsuarioPeloUsuario(String usuario) {
        String sql = String.format("SELECT * FROM usuario WHERE id == %s", usuario);
        
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            
            if (result.next()) {
                return getUsuario(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error", e.getMessage()));
        }
        
        return null;
    }
    
    
}

