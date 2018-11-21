/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sys515
 */
public class LoginController implements ActionListener {

    private LoginView view;
    Boolean rol=false;
      Conexion con = new Conexion();
    Connection Consulta = con.conexion();
    public LoginController(LoginView view)
    {
        this.view=view;
        addListener();
    }
    private void mensaje(String mensaje)
    {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(view, mensaje);
    }
    private void addListener()
    {
        view.getAceptar().addActionListener(this);
        view.getCanelar().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==view.getAceptar())
        {
             String user=view.getUsuario().getText();
                    String contraña=view.getPass().getText();
            if(user.trim().length()<=0 && contraña.trim().length()<=0)
            {
                mensaje("Ingresa tu Usuario y Contraseña"); 
            }
            else if(user.trim().length()<=0 )
            {
                mensaje("Ingresa tu Usuario");
            }
            else if(contraña.trim().length()<=0)
            {
                mensaje("Ingresa tu Contraseña");
            }
            else
            {
                try {
                    
                   String privlegios="";
                    String id="";
                   int c=0;
                    Statement sx = Consulta.createStatement();
                    ResultSet Ca = sx.executeQuery("select Privilegios,id from Usuarios where Usuario='"+user+"' && Contraseña='"+contraña+"'");
                    while(Ca.next())
                    {
                        privlegios=Ca.getString(1);
                        id=Ca.getString(2);
                        c++;
                    }
                    if(c==0)
                    {
                        mensaje("Ingrese un Usuario Valido");
                    }
                    else
                    {
                        if(privlegios.equals("Administrador"))
                        {
                            rol=true;
                            
                            view.setRol(id);
                            view.setVisible(false);
                        }
                        else
                        {
                            mensaje("Ingrese Un usuario con Permiso Administrativo");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            return;
        }
        if(e.getSource()==view.getCanelar())
        {
            view.setVisible(false);
            return;
        }
    }

    
    
    
}
