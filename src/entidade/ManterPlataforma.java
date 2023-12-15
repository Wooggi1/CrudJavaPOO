/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;


import controle.Plataforma;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author thiad
 */
public class ManterPlataforma extends DAO{
    public void inserir(Plataforma a) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO plataforma(id,nome,empresa) "
            + "values(null,?,?)";
            pst=(PreparedStatement) con.prepareStatement(query);
            pst.setString(1, a.getNome());
            pst.setString(2,a.getEmpresa());
            pst.execute();
            fecharBanco();
        } catch (Exception e){
            System.out.println("Erro " + e.getMessage());
        }
    }
    
    public void deletarPlataforma(Plataforma a) throws Exception{
        abrirBanco();
        String query = "delete from plataforma where id=?";
        pst=(PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, a.getID());
        pst.execute();
       // JOptionPane.showMessageDialog(null, "Jogo deletado com sucesso!");
        fecharBanco();
  }
  
  public ArrayList<Plataforma> PesquisarPlataformas () throws Exception {
    ArrayList<Plataforma> plataformas = new ArrayList<Plataforma>();
        try{
            abrirBanco();  
            String query = "select id, nome, empresa FROM plataforma";
            pst = (PreparedStatement) con.prepareStatement(query);
            java.sql.ResultSet tr = pst.executeQuery();
            Plataforma a ;
        while (tr.next()){               
            a = new Plataforma();
            a.setID(tr.getInt("id"));
            a.setNome(tr.getString("nome"));
            a.setEmpresa(tr.getString("empresa"));
            plataformas.add(a);
        } 
            fecharBanco();
        }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
        } 
    return plataformas;
  }
            
  public void PesquisarRegistro(Plataforma a) throws Exception {
    try {
        abrirBanco();
        String query = "select * FROM plataforma where id=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, a.getID());
        java.sql.ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                a.setID(tr.getInt("id"));
                a.setNome(tr.getString("nome"));
                a.setEmpresa(tr.getString("empresa"));
            } else {
                //JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
        fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
  }
            
  public void editarPlataforma(Plataforma a) throws Exception {
    abrirBanco();
    JOptionPane.showMessageDialog(null, a.getNome()+ a.getEmpresa());
    String query = "UPDATE plataforma set nome = ?,empresa = ? where id=?";
    pst = (PreparedStatement) con.prepareStatement(query);
    pst.setString(1, a.getNome());
    pst.setString(2, a.getEmpresa());
    pst.setInt(3, a.getID());
    pst.executeUpdate();
    fecharBanco();
  }
     
}
