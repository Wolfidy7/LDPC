import java.util.*;
import java.io.*;

public class Matrix {
    private byte[][] data = null;
    private int rows = 0, cols = 0;
    
    public Matrix(int r, int c) {
        data = new byte[r][c];
        rows = r;
        cols = c;
    }
    
    public Matrix(byte[][] tab) {
        rows = tab.length;
        cols = tab[0].length;
        data = new byte[rows][cols];
        for (int i = 0 ; i < rows ; i ++)
            for (int j = 0 ; j < cols ; j ++) 
                data[i][j] = tab[i][j]; //copie de matrice
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public byte getElem(int i, int j) {
        return data[i][j];
    }
    
    public void setElem(int i, int j, byte b) {
        data[i][j] = b;
    }
    
    public boolean isEqualTo(Matrix m){
        if ((rows != m.rows) || (cols != m.cols))
            return false;
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                if (data[i][j] != m.data[i][j])
                    return false;
                return true;
    }
    
    public void shiftRow(int a, int b){
        byte tmp = 0;
        for (int i = 0; i < cols; i++){
            tmp = data[a][i];
            data[a][i] = data[b][i];
            data[b][i] = tmp;
        }
    }
    
    public void shiftCol(int a, int b){
        byte tmp = 0;
        for (int i = 0; i < rows; i++){
            tmp = data[i][a];
            data[i][a] = data[i][b];
            data[i][b] = tmp;
        }
    }
     
    public void display() {
        System.out.print("[");
        for (int i = 0; i < rows; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            
            System.out.print("[");
            
            for (int j = 0; j < cols; j++) {
                System.out.printf("%d", data[i][j]);
                
                if (j != cols - 1) {
                    System.out.print(" ");
                }
            }
            
            System.out.print("]");
            
            if (i == rows - 1) {
                System.out.print("]");
            }
            
            System.out.println();
        }
        System.out.println();
    }
    
    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                result.data[j][i] = data[i][j];
    
        return result;
    }
    
    public Matrix add(Matrix m){
        Matrix r = new Matrix(rows,m.cols);
        
        if ((m.rows != rows) || (m.cols != cols))
            System.out.printf("Erreur d'addition\n");
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                r.data[i][j] = (byte) ((data[i][j] + m.data[i][j]) % 2);
        return r;
    }
    
    public Matrix multiply(Matrix m){
        Matrix r = new Matrix(rows,m.cols);
        
        if (m.rows != cols)
            System.out.printf("Erreur de multiplication\n");
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                r.data[i][j] = 0;
                for (int k = 0; k < cols; k++){
                    r.data[i][j] =  (byte) ((r.data[i][j] + data[i][k] * m.data[k][j]) % 2);
                }
            }
        }
        
        return r;
    }

    public void addRow(int a, int b){
        for (int i = 0; i < cols; i++){
           data[b][i] = (byte) ((data[b][i] + data[a][i]) % 2);
        }
    }

    public void addCol(int a, int b){
        for (int i = 0; i < rows; i++){
            data[i][b] = (byte) ((data[i][b] += data[i][a]) % 2);
        }
    }

    public Matrix sysTransform(){

        for (int i = 0, j = cols-rows; i < rows && j < cols; i++, j++) { //première partie: échange des lignes
            for (int k = i; k<rows; k++){
                if (getElem(k, j) == (byte) 1){
                    shiftRow(i, k);
                    break;
                }
            }   
            for (int p= 0; p<rows; p++){
                if (getElem(p, j) == (byte) 1 && p!=i){
                    addRow(i, p);
                }
            }  
        }
        return this;
    }

    public Matrix genG(){

        Matrix G = new Matrix(cols-rows, cols);
    
        //récupération de la transposée de M
        Matrix M = new Matrix(rows, cols-rows);
        for(int i=0; i<rows ; i++){
            for(int j=0; j<cols-rows; j++){
                M.data[i][j] = data[i][j];
            }
        }
        Matrix transp_M = M.transpose();
        //FIN recupération transposée de M

        for(int i=0; i<cols-rows; i++){
            for(int j=0; j<cols; j++){
                if(i==j){
                    G.data[i][j] = (byte) 1;  //Mise en place de la matrice identité
                }
                else if(i<cols-rows && j<cols-rows){
                    G.data[i][j] = (byte) 0;
                }
                else{
                    G.data[i][j] = transp_M.data[i][j-(cols-rows)];//concaténation avec la transposée de M
                }
            }
        }

        return G;
    }

    public Matrix errGen(int w){
        
        Random r = new Random();
        Matrix e = new Matrix(1, this.cols);
        for(int i=0; i<w; i++){
            int n = r.nextInt(this.cols); //on récupère l'indice d'une colonne au hasard
            if(e.getElem(0, n) == 0){
                e.setElem(0, n, (byte)1);
            }
        }
        return e;
    }



}
