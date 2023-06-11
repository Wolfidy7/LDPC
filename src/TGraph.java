import java.util.*;
import java.io.*;

public class TGraph {
    
    int n_r = 0; //nombre de lignes
    int w_r = 0; //poids des lignes
    int n_c = 0; //nombre de colonnes
    int w_c = 0; //poids des colonnes
    int[][] left;
    int[][] right;

    public TGraph(Matrix H, int wc, int wr){

        n_r = H.getRows();
        this.w_r = wr;
        n_c = H.getCols();
        this.w_c = wc;

        //initialisation des noeuds à 0
        left = new int[n_r][w_r + 1];
        right = new int[n_c][w_c + 1];
        for(int i=0; i<n_r; i++){
            left[i][0] = 0;
        }
        for(int j=0; j<n_c; j++){
            right[j][0] = 0;
        }
        //fin initialisation des noeuds
       
        //Completion de left
        int k=1;
        for(int i=0; i<n_r; i++){
            for(int j=0; j<n_c; j++){
                if (H.getElem(i, j) == 1){
                    left[i][k]= j;
                    k +=1;
                }
            }
            k=1;
        }
        //Completion de right
        int r=1;
        for(int i=0; i<n_c; i++){
            for(int j=0; j<n_r; j++){
                if (H.getElem(j, i) == 1){
                    right[i][r]= j;
                    r +=1;
                }
            }
            r=1;
        }
    }

    public void display(){

        //left
        System.out.println("left:");
        for(int i=0; i<n_r; i++){
            System.out.print(left[i][0] + "| " );
            for(int j=1; j<w_r+1; j++){
                System.out.print(left[i][j]+" ");
            }
            System.out.println();
        }

        //right
        System.out.println("right:");
        for(int i=0; i<n_c; i++){
            System.out.print(right[i][0] + "| " );
            for(int j=1; j<w_c+1; j++){
                System.out.print(right[i][j]+" ");
            }
            System.out.println();
        }

    }
    
    public Matrix decode(Matrix code, int rounds){

        //Initialisation
        int i=1;
        byte[][] x = new byte[1][n_c]; //mot décodé
        int[] count = new int[n_c];

        for(i=1; i<n_c; i++){
            right[i][0] = code.getElem(0, i);
        }
    
        //Boucle principale
        for(int r=0; r<rounds; r++){
            //Calcul des parités
            for(int k=0; k<n_r; k++){
                left[k][0] = 0;
                for(int l=1; l<w_r+1; l++){
                    left[k][0] = (left[k][0] + right[left[k][l]][0])%2;
                }
            }
            //Vérification
            for(int k=0; k<n_r; k++){
                if(left[k][0]!=0){
                    break;
                }
                else if(k==n_r-1){
                    i=1;
                    for(i=1; i<n_c; i++){
                        x[0][i] = (byte) left[k][0];
                        i+=1;
                    }
                    Matrix X = new Matrix(x);
                    return X;
                }
            }
            //Calcul du max
            int max = 0;
            for(i=1; i<n_c; i++){
                count[i] = 0;
                for(int l=1; l<w_c+1; l++){
                    count[i] = count[i] + left[right[i][l]][0];
                }

                if(count[i]> max){
                    max = count[i];
                }
            }
            //Renversement de bits
            for(i=1; i<n_c; i++){
                if(count[i]==max){
                    right[i][0] = 1 - right[i][0];
                }
            }



        }

        //Si aucune valeur retournée, échec
        byte[][] tab = new byte[1][1];
        tab[0][0] = -1;
        Matrix echec = new Matrix(tab);
        return echec; 
        
    }




}
