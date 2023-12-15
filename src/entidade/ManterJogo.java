/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

import controle.Jogo;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class ManterJogo extends DAO{
  public void inserir(Jogo a) throws Exception {
    try {
    abrirBanco();
    String query = "INSERT INTO jogo(id,nome,empresa,genero,plataforma_id) "
            + "values(null,?,?,?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, a.getNome());
    pst.setString(2,a.getEmpresa());
    pst.setString(3, a.getGenero());
    pst.setInt(4, a.getPlataforma_id());    
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }  
  }
  
  public void deletarJogo(Jogo a) throws Exception{
    abrirBanco();
    String query = "delete from jogo where id=?";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setInt(1, a.getID());
    pst.execute();
    JOptionPane.showMessageDialog(null, "Jogo deletado com sucesso!");
    fecharBanco();
  }
  
  //listando Alunos
  public ArrayList<Jogo> PesquisarTudo () throws Exception {
    ArrayList<Jogo> jogos = new ArrayList<Jogo>();
        try{
            abrirBanco();  
            String query = "select id, nome, empresa, genero FROM jogo";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Jogo a ;
        while (tr.next()){               
            a = new Jogo();
            a.setID(tr.getInt("id"));
            a.setNome(tr.getString("nome"));
            a.setEmpresa(tr.getString("empresa"));
            a.setGenero(tr.getString("genero"));
            jogos.add(a);
        } 
            fecharBanco();
        }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
        } 
    return jogos;
  }
            
  public void PesquisarRegistro(Jogo a) throws Exception {
    try {
        abrirBanco();
        String query = "select * FROM jogo where id=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, a.getID());
        ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                a.setID(tr.getInt("id"));
                a.setNome(tr.getString("nome"));
                a.setEmpresa(tr.getString("empresa"));
                a.setGenero(tr.getString("genero"));
                a.setPlataforma_id(tr.getInt("plataforma_id"));
            } else {
                //JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
        fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
  }
            
  public void editarJogo(Jogo a) throws Exception {
    abrirBanco();
    JOptionPane.showMessageDialog(null, a.getNome()+ a.getEmpresa()+ a.getGenero());
    String query = "UPDATE jogo set nome = ?,empresa = ?, genero = ?, plataforma_id = ? where id=?";
    pst = (PreparedStatement) con.prepareStatement(query);
    pst.setString(1, a.getNome());
    pst.setString(2, a.getEmpresa());
    pst.setString(3, a.getGenero());
    pst.setInt(4, a.getPlataforma_id());
    pst.setInt(5, a.getID());
    pst.executeUpdate();
    fecharBanco();
  }
            
}