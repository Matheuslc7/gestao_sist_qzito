/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qzito.m7.gestaov.controller;

import com.qzito.m7.gestaov.modelo.dao.UsuarioDao;
import com.qzito.m7.gestaov.modelo.dominio.Perfil;
import com.qzito.m7.gestaov.modelo.dominio.usuario;
import java.time.LocalDateTime;

/**
 *
 * @author Qzito 10
 */
public class UsuarioTeste {
    
    public static void main(String[] args) {
        usuario usuario = new usuario(0L, "MYCHELA","12345678", "mychela", Perfil.PADRAO, null, null);
        
        UsuarioDao usuarioDao = new UsuarioDao();
        String mensagem = usuarioDao.salvar(usuario);
        System.out.println(mensagem);
    }
    
}
