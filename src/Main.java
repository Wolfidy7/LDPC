import java.util.*;
import java.io.*;

public class Main {
    
    public static Matrix loadMatrix(String file, int r, int c) {
        byte[] tmp =  new byte[r * c];
        byte[][] data = new byte[r][c];
        try {
            FileInputStream fos = new FileInputStream(file);
            fos.read(tmp);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < r; i++)
            for (int j = 0; j< c; j++)
                data[i][j] = tmp[i * c + j];
            return new Matrix(data);
    }
    
    public static void main(String[] arg){
        
        byte[][] tab = {{1,0,0},{0,1,0},{0,0,1}};

        /* **EXERCICE 2***
        
            byte[][] tab1 = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
            };
            byte[][] tab2 = {
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 2, 1, 0}
            };
            
            Matrix m1 = new Matrix(tab1);
            Matrix m2 = new Matrix(tab2);

            Matrix sum = m1.add(m2);
            System.out.println("Somme:");
            sum.display();

            Matrix mul = m1.multiply(m2);
            System.out.println("Multiplication:");
            mul.display();

            Matrix transp = m2.transpose();
            System.out.println("Transposition:");
            transp.display();

        ***FIN EXERCICE 2*** */

        /* ** EXERCICE 3**

            Matrix hbase = loadMatrix("matrix-15-20-3-4", 15, 20);
            hbase.display();

            hbase.addRow(0, 1);
            System.out.println("Addition lignes:");
            hbase.display();

            hbase.addCol(0,1);
            System.out.println("Addition colonnes:");
            hbase.display();
            
        FIN EXERCICE 3 */

        /*Matrix m = new Matrix(tab);
        m.display();
        
        Matrix hbase = loadMatrix("matrix-15-20-3-4", 15, 20);
        hbase.display();*/

        /*DEBUT EXO 4
        Matrix hbase = loadMatrix("matrix-15-20-3-4", 15, 20);
        hbase.display();
        System.out.println("TEST MATRICE TRIANGULAIRE SUPERIEURE");
        Matrix etape1 =  hbase.sysTransform();
        etape1.display();

        FIN EXO 4*/
        
        /*DEBUT EXO 5
        Matrix hbase = loadMatrix("matrix-15-20-3-4", 15, 20);
        Matrix etape1 =  hbase.sysTransform();
        hbase.display();
        Matrix G = etape1.genG();
        G.display();
        FIN EXO 5*/

        /*
        Matrix hbase = loadMatrix("matrix-15-20-3-4", 15, 20);
        Matrix etape1 =  hbase.sysTransform();
        Matrix G = etape1.genG();
        G.display();
        byte[][] tab_u = {{1,0,1,0,1}};
        Matrix u = new Matrix(tab_u);
        Matrix x = u.multiply(G);
        System.out.println("encodage:");
        x.display();
        EXO 6*/

        /* DEBUT EXO 7
        Matrix hbase = loadMatrix("matrix-15-20-3-4", 15, 20);
        //byte[][] tabi ={{1,0,1,0},{0,1,0,1}};
        //Matrix hbase= new Matrix(tabi);

        TGraph Tanner = new TGraph(hbase, 3, 4);
        Tanner.display();
        FIN EXO 7 */

        //DEBUT EXO 9
        byte[][] x = {{1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,0,0,0,1}};
        Matrix X = new Matrix(x);
        Matrix H = loadMatrix("matrix-15-20-3-4", 15, 20);
        TGraph Tanner = new TGraph(H, 3, 4);
        Matrix decodage = Tanner.decode(X, 100);
        decodage.display();

        //FIN EXO 9

        /*Matrix H = loadMatrix("matrix-15-20-3-4", 15, 20);
        byte[][] x = {{1,0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0,0,1}};
        Matrix E1 = new Matrix(x);

        Matrix f = H.multiply(E1.transpose());
        f.display();*/


    }
}
