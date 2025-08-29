import com.mysql.cj.conf.PropertyDefinitions;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
 
public class estudiantesServices {
    public estudiantesServices estudiantesServices;

    public void obtenerNumeroestudiantes(Connection conn) throws SQLException{
        String sql="SELECT * FROM estudiantes";
        var stm =conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int cont=0;
        while (rs.next()){
            System.out.print(".");
            cont++;
        }
        System.out.println("Cantidad de registros encontrados " + cont);
    }
 
    public void insertarestudiantes(Connection conn) throws SQLException{
        String sql="INSERT INTO estudiantes (Nombre, Apellido, Edad, Correo, Estado_Civil) VALUES ('DANIEL', 'CORONCORO', '19', 'ELNEGRITO@PRIPA', 'UNION_LIBRE')";
        var stm=conn.prepareStatement(sql);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro insertado de forma correcta");
        }
        else {
            System.out.println("Error en insercion");
        }
    }
 
    public void actualizarestudiantes(Connection conn) throws SQLException{
        String sql="UPDATE estudiantes SET Estado_Civil='CASADO' WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        int rs = stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro actualizado de forma correcta");
        }
        else {
            System.out.println("Error en la actualizacion");
        }
    }
 
    public void eliminarestudiantes(Connection conn) throws SQLException{
        String sql="DELETE FROM estudiantes WHERE ID=5";
        var stm=conn.prepareStatement(sql);
        int rs=stm.executeUpdate();
        if(rs>0) {
            System.out.println("Registro eliminado de forma correcta");
        }
        else {
            System.out.println("Error en la eliminacion");
        }
    }
 
    public void insertarestudianteConValores(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite nombre del estudiante: ");
        String Nombre = in.nextLine();
        System.out.println("Digite apellido del estudiante: ");
        String Apellido = in.nextLine();
        System.out.println("Digite la edad del estudiante: ");
        int Edad = in.nextInt();
        in.nextLine(); 
        System.out.println("Digite el correo del estudiante: ");
        String Correo = in.nextLine();
        System.out.println("Digite el estado civil del estudiante: ");
        String Estado_Civil = in.nextLine();
 
        String sql = "INSERT INTO estudiantes (Nombre, Apellido, Edad, Correo, Estado_Civil) VALUES (?,?,?,?,?)";
        try (var stm = conn.prepareStatement(sql)) {
            stm.setString(1, Nombre);
            stm.setString(2, Apellido);
            stm.setInt(3, Edad);
            stm.setString(4, Correo);
            stm.setString(5, Estado_Civil);
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Registro insertado de forma correcta");
            } else {
                System.out.println("Fallo en la insercion");
            }
        }
    }
 
    public void actualizarestudiantesConValores(Connection conn) throws SQLException{
        Scanner in =new Scanner(System.in);
        System.out.println("Digite el nuevo estado civil: ");
        String Estado_Civil=in.nextLine();
        System.out.println("Digite el ID deL etudiante a actualizar: ");
        int id= in.nextInt();
        String sql="UPDATE estudiantes SET Estado_Civil=? WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        //stm.setString(1, estadoCivil);
        //stm.setInt(2,id);
        stm.setObject(1, Estado_Civil);
        stm.setObject(2,id);
        int rs = stm.executeUpdate();
        if(rs>0){
            System.out.println("Registro actualizado de forma correcta");
        }
        else{
            System.out.println("Fallo en la actualizacion");
        }
        in.close();
    }
 
    public void eliminarestudiantesConValores(Connection conn) throws SQLException{
        Scanner in=new Scanner(System.in);
        System.out.print("Digite el id del estudiante a eliminar: ");
        int id=in.nextInt();
        String sql="DELETE FROM estudiantes WHERE ID=?";
        var stm=conn.prepareStatement(sql);
        stm.setObject(1,id);
        int rs=stm.executeUpdate();
        if(rs>0){
            System.out.println("Registro eliminado");
        }
        else {
            System.out.println("Eiminacion fallida");
        }
        in.close();
    }
 
    public void obtenerestudiantesConValores(Connection conn) throws SQLException{
        String sql="SELECT * FROM estudiantes";
        var stm=conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String Nombre = rs.getString("Nombre");
            String Apellido = rs.getString("Apellido");
            String Edad = rs.getString("Edad");
            String Correo = rs.getString("Correo");
            String Estado_Civil = rs.getString("Estado_Civil");
            System.out.println(String.format("Mi id es %d, mi nombre completo es %s %s, mi edad es %d, mi correo es %s y mi estado civil es %s",id,nombre,apellido,estadoCivil));
        }
        System.out.println("Consulta Finalizada");
    }
}