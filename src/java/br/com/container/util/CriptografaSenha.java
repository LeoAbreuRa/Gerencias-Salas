/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class CriptografaSenha {

    public static String criptoSenha(String senha) {
        String retorno = "";
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            retorno = new BigInteger(1, m.digest()).toString(16);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CriptografaSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }

}
