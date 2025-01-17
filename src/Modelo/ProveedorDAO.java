
package Modelo;
  
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

public class ProveedorDAO {
    Conexion cn = new Conexion(); 
    Connection con; 
    PreparedStatement ps;
    ResultSet rs;
   
     
    public boolean registrarProveedor(Proveedor pr) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        String sql = "INSERT INTO proveedor (dni, nombre, telefono, direccion, razon, fecha) VALUES(?, ?, ?, ?, ?,?)";
        try{
           con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getDni());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.setString(6, formato.format(pr.getFecha()));
            ps.execute();
            return true;
        } catch (SQLException e){
            System.out.println(e.toString());   
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                 System.out.println(e.toString());
            }
        }
    }


        public  List ListarProveedor(){
            List<Proveedor> Listapr = new ArrayList();
            String sql = "SELECT * FROM proveedor";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {                    
                    Proveedor pr = new Proveedor();
                     pr.setId(rs.getInt("id"));
                     pr.setDni(rs.getInt("dni"));
                     pr.setNombre(rs.getString("nombre"));
                     pr.setTelefono(rs.getInt("telefono"));
                     pr.setDireccion(rs.getString("direccion"));
                     pr.setRazon(rs.getString("razon"));
                     Listapr.add(pr);
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            return Listapr;
       }
       public boolean EliminarProveedor(int id){
           String sql = "DELETE FROM proveedor WHERE id = ? ";
           try {
               con = cn.getConnection();
               ps = con.prepareStatement(sql);
               ps.setInt(1, id);
               ps.execute();
               return true;
           } catch (SQLException e) {
               System.err.println(e.toString());
               return false;
           }finally{
                try {
                   con.close();
               } catch (Exception e) {
                    System.out.println(e.toString());
               
               }
          
           }
              
       }
       public boolean ModificarProveedor(Proveedor pr){
           String sql = "UPDATE proveedor SET dni=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id =?";
           try {
               con = cn.getConnection();
               ps = con.prepareStatement(sql);
               ps.setInt(1, pr.getDni());
               ps.setString(2, pr.getNombre());
               ps.setInt(3, pr.getTelefono() );
               ps.setString(4, pr.getDireccion());
               ps.setString(5, pr.getRazon());
               ps.setInt(6, pr.getId());
               ps.execute();
               return true;
           } catch (SQLException e) {
               System.out.println(e.toString());
               return false;
           }finally{
               try {
                   con.close();
               } catch (SQLException e) {
                   System.out.println(e.toString());
               
               }
               
           }
       
      }
       
}   

