/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qzito.m7.gestaov.dao.conexao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Qzito 10
 */
public interface Conexao {
    
    public Connection obterConexao() throws SQLException;
}
