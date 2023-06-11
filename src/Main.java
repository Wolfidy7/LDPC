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

         //***EXERCICE 2***
        
         System.out.println("*****EXERCICE 2*****");
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

        //***FIN EXERCICE 2*** 

        //** EXERCICE 3**

            System.out.println("*****EXO 3*****");
            Matrix xr = loadMatrix("matrix-15-20-3-4", 15, 20);
            System.out.println("Matrice de contrôle:");
            xr.display();

            xr.addRow(0, 1);
            System.out.println("Addition lignes 1 et 2:");
            xr.display();

            xr.addCol(0,1);
            System.out.println("Addition colonnes 1 et 2:");
            xr.display(); 
            
        //***FIN EXERCICE 3*** 

        //***DEBUT EXO 4***
        System.out.println("*****EXO 4*****");
        Matrix hh = loadMatrix("matrix-15-20-3-4", 15, 20);
        System.out.println("Matrice H initiale:");
        hh.display();
        System.out.println("Forme systématique:");
        Matrix etape1 =  hh.sysTransform();
        etape1.display();

        //***FIN EXO 4***
        
        //***DEBUT EXO 5***
        System.out.println("*****EXO 5*****");
        Matrix h1 = loadMatrix("matrix-15-20-3-4", 15, 20);
        Matrix sys =  h1.sysTransform();
        h1.display();
        Matrix G = sys.genG();
        System.out.println("Matrice génératrice G:");
        G.display();
        //***FIN EXO 5***

        // ***EXO 6***
        System.out.println("*****EXO 6*****");
        Matrix ht = loadMatrix("matrix-15-20-3-4", 15, 20);
        Matrix etap1 =  ht.sysTransform();
        Matrix _G = etap1.genG();
        //_G.display();
        byte[][] tab_u = {{1,0,1,0,1}};
        Matrix u = new Matrix(tab_u);
        Matrix _x = u.multiply(_G);
        System.out.println("encodage de u=10101:");
        _x.display();
        //***FIN EXO 6***

        //*** DEBUT EXO 7 ***
        System.out.println("***EXO 7***");
        Matrix hbase = loadMatrix("matrix-15-20-3-4", 15, 20);
        //byte[][] tabi ={{1,0,1,0},{0,1,0,1}};
        //Matrix hbase= new Matrix(tabi);

        TGraph Tann = new TGraph(hbase, 3, 4);
        System.out.println("GRAPHE DE TANNER de H:");
        Tann.display();
        //*** FIN EXO 7 ***

        //DEBUT EXO 9
        byte[][] x  = {{0,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,0,0,0,1}};
        byte[][] e1 ={{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        byte[][] e2 = {{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        byte[][] e3 ={{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};
        byte[][] e4 = {{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
        Matrix X = new Matrix(e3);
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
