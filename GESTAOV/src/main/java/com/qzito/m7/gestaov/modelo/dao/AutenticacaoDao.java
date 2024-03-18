/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qzito.m7.gestaov.modelo.dao;

import com.qzito.m7.gestaov.modelo.dominio.Perfil;
import com.qzito.m7.gestaov.modelo.dominio.usuario;
import com.qzito.m7.gestaov.modelo.exception.NegocioException;
import com.qzito.m7.gestaov.view.modelo.LoginDto;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Qzito 10
 */
public class AutenticacaoDao {
    
    private final UsuarioDao usuarioDao;
    
    public AutenticacaoDao() {
        this.usuarioDao = new UsuarioDao();
    }
    
    public boolean temPermissao(usuario usuario) {
        try {
            permissao(usuario);
            return true;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Usuario sem permissao", 0);
            return false;
        }
    }
    
    private void permissao(usuario usuario) {
        if(Perfil.ADMIN.equals(usuario.getPerfil())) {
            throw new NegocioException("Sem permissao para realizar essa ação.");
        }
    }
    
    public usuario login(LoginDto login) {
        usuario usuario = usuarioDao.buscarUsuarioPeloUsuario(login.getUsuario());
        
        if(usuario == null || !usuario.isEstado())
            return null;
        
        if(usuario.isEstado() && validaSenha(usuario.getSenha(), login.getSenha())) {
            return usuario;
        }
        
        return null;
    }

    private boolean validaSenha(String senhaUsuario, String senhaLogin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(senhaLogin, senhaUsuario);
    }
    
}
