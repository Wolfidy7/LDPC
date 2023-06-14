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
        System.out.println("*****EXO 9*****");
        byte[][] __x  = {{1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,0,0,0,1}};
        byte[][] _e1 ={{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        byte[][] _e2 = {{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        byte[][] _e3 ={{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};
        byte[][] _e4 = {{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};

        Matrix e1 = new Matrix(_e1);
        Matrix e2 = new Matrix(_e2);
        Matrix e3 = new Matrix(_e3);
        Matrix e4 = new Matrix(_e4);

        Matrix x = new Matrix(__x);
        Matrix y1 = x.add(e1);
        Matrix y2 = x.add(e2);
        Matrix y3 = x.add(e3);
        Matrix y4 = x.add(e4);
        Matrix H = loadMatrix("matrix-15-20-3-4", 15, 20);
        TGraph Tanner = new TGraph(H, 3, 4);

        Matrix _syst =  H.sysTransform();
        Matrix __G = _syst.genG();
        
        Matrix decodage = Tanner.decode(x, 100);
        System.out.println("x avant correction:");
        x.display();
        System.out.println("x après correction:");
        decodage.display();


        Matrix __H = loadMatrix("matrix-15-20-3-4", 15, 20);

        
        System.out.println("y1:");
        y1.display();
        System.out.println("Syndrome de y1:");
        (__H.multiply(y1.transpose())).display();
        System.out.println("Correction x1 de y1:");
        Matrix x1 = Tanner.decode(y1, 100);
        x1.display();
        System.out.print("x1=x ? ");
        System.out.println(x.isEqualTo(x1));
        System.out.println("--------------------------");


        System.out.println("y2:");
        y2.display();
        System.out.println("Syndrome de y2:");
        (__H.multiply(y2.transpose())).display();
        System.out.println("Correction x2 de y2:");
        Matrix x2 = Tanner.decode(y2, 100);
        x2.display();
        System.out.print("x2=x ? ");
        System.out.println(x.isEqualTo(x2));
        System.out.println("--------------------------");


        System.out.println("y3:");
        y3.display();
        System.out.println("Syndrome de y3:");
        (__H.multiply(y3.transpose())).display();
        System.out.println("Correction x3 de y3:");
        Matrix x3 = Tanner.decode(y3, 100);
        x3.display();
        System.out.print("x3=x ? ");
        System.out.println(x.isEqualTo(x3));
        System.out.println("--------------------------");


        System.out.println("y4:");
        y4.display();
        System.out.println("Syndrome de y4:");
        (__H.multiply(y4.transpose())).display();
        System.out.println("Correction x4 de y4:");
        Matrix x4 = Tanner.decode(y4, 100);
        x4.display();
        System.out.print("x4=x ? ");
        System.out.println(x.isEqualTo(x4));
        System.out.println("--------------------------");

        //FIN EXO 9

        //DEBUT EXO 11
        System.out.println("*****EXO 11*****");
        Matrix H2 = loadMatrix("matrix-2048-6144-5-15", 2048, 6144);
        TGraph Graph_ = new TGraph(H2, 5, 15);
        Matrix G_= (H2.sysTransform()).genG();
        //G_.display();

        //Génération de x et encodage à l'aide de G
        byte[][] u_ = new byte[1][4096];
        for(int i=0; i<4096; i++){
            if ((i%2)==0){
                u_[0][i]=1;
            }
            else{
                u_[0][i]=0;
            }
        }
        Matrix u__ = new Matrix(u_);

        Matrix x__ = u__.multiply(G_);

        System.out.println("Encodage de x = u*G:");
        x__.display();

        //Graphe de Tanner
        System.out.println("Graphe de Tanner:");
        Graph_.display();

        //FIN EXO 11

        //***EXO 12 ***
        System.out.println("*****EXO 12*****");
        
        int poids=2;
        System.out.println("Vecteur ligne de poids " + poids);
        Matrix you = new Matrix(1,15);
        you.errGen(poids).display();

    }
}
