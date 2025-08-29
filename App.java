import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
 
public class App {
     static String url= "jdbc:mysql://localhost:3306/taller_estudiantes";
     static String userName="root";
     static String password="root123";
 
    public static void main(String[] args){
        try(Connection conn= DriverManager.getConnection(url, userName, password)){
            estudiantesServices estudiantesServices=new estudiantesServices();
            Scanner sc = new Scanner(System.in);
            int opcion;
 
            do {
                System.out.println("MENÚ");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Actualizar Estudiante");
                System.out.println("3. Eliminar Estudiante");
                System.out.println("4. Consultar todos los estudiantes");
                System.out.println("5. Consultar estudiante por email");
                System.out.println("6. Salir");
                System.out.print("Escoja una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); 
 
                switch (opcion) {
                    case 1:
                        estudiantesServices.insertarestudianteConValores(conn);
                        break;
                    case 2:
                        estudiantesServices.actualizarestudianteConValores(conn);
                        break;
                    case 3:
                        estudiantesServices.eliminarestudianteConValores(conn);
                        break;
                    case 4:
                        estudiantesServices.obtenerestudianteConValores(conn);
                        break;
                    case 5:
                        System.out.print("Digite el Correo del estudiante: ");
                        String Correo = sc.nextLine();
                        estudiantesServices.consultarEstudiantePorEmail(conn, Correo);
                        break;
                    case 6:
                        System.out.println("¡VUELVA PRONTO!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcion != 6);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}