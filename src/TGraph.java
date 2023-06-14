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
    
    public Matrix decode(Matrix code, int rounds) {
		 		 
		 	
            //initialisation
		 	for(int i=0; i<n_c;i++) {
	    		
	    		right[i][0] = code.getElem(0, i);
	    		
	    	}
		 	
		 	
	    	int verif = 0;
	    	byte [][] tab = new byte[1][code.getCols()];
	    	Matrix x;
	    	int [] count = new int[code.getCols()];
	    	
	    	
            //Boucle principale
	    	for(int i=0; i<rounds; i++) {
	    		
	    		//Calcul des parités
	    		for(int j=0; j<n_r; j++) {
	    			
	    			left[j][0] = 0;
	    			
	    			for(int k=1; k<w_r+1; k++) {
	    				left[j][0]= (left[j][0] + right[left[j][k]][0])%2;
	    				
	    			}
	    		}
	    			
	    		//Vérification
	    		for(int k=0; k<n_r; k++) {
    				if(left[k][0] != 0) {
    					verif = 0;
    					break; 
    				}
    				verif = 1;
	    		}
	    			
	    			if(verif==1) {
	    				

	    				for(int k=0; k<n_c; k++) {
	    					tab[0][k] = (byte)right[k][0];
	    					
	    				}
	    				
	    				x = new Matrix(tab);
	    				return x;
	    				
	    			}
	    			
                    //Calcul du max
                    int max = 0;
	    			for(int k=0; k<n_c; k++) {
	    				count[k] = 0;
	    				
	    				for(int l=1; l<w_c+1; l++) {
		    				count[k] = count[k] + left[right[k][l]][0];
		    			}
	    				
	    				if(count[k]> max) {
	    					max = count[k];
	    				}
	    				
	    			}
	    			
	    			//Renversement de bits
	    			for(int k=0; k<n_c;k++) {
	    				if(count[k] == max) {
	    					
	    					right[k][0] = 1 - right[k][0];
	    					
	    				}
	    			}
	    		
	    		}
	    			
	    	//Si aucune valeur n'est retournée, échec
	    	byte[][] err = new byte[1][n_c];
            for (int i=0; i<n_c; i++){
                err[0][i] = -1;
            }
            Matrix error =new Matrix(err);
			return error;
	    	
	    }
	    

}
