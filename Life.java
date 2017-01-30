/* Zaki Lindo
 * 1/30/2017
 * CS 450 - High Performance Computing
 * Dr. Lanz
 */
import java.util.*;	

public class Life {
 
 	public static void main (String [] args) {
 		Random rand = new Random();
 		int NI = 200;

		// Array Sizes
		int NJ = 200;
		int NSTEPS = 500;
		// Number of time steps
 		int i, j, n, im, ip, jm, jp, ni, nj, nsum, isum;
 		double x;
 		// allocate arrays
 		ni = NI + 2;
 		// add 2 for left and right ghost cells
 		nj = NJ + 2;
 		int [][] old = new int [ni][nj];
 		int [][] neww = new int [ni][nj];
 		// initialize elements of old to 0 or 1
 		for (i=1; i<=NI; i++) {
			for (j=1; j<=NJ; j++) {
				x = rand.nextDouble();
				if(x<0.5) {
					old[i][j] = 0;
				} else {
					old[i][j] = 1;
				}
			}
		}
		// time steps
		for (n=0; n<NSTEPS; n++) {
		/* corner boundary conditions */
		old[0][0] = old[NI][NJ];
		old[0][NJ+1] = old[NI][1];
		old[NI+1][NJ+1] = old[1][1];
		old[NI+1][0] = old[1][NJ];
		/* left-right boundary conditions */
		for (i=1; i<=NI; i++) {
			old[i][0] = old[i][NJ];
			old[i][NJ+1] = old[i][1];
		}
		/* top-bottom boundary conditions */
		for (j=1; j<=NJ; j++) {
			old[0][j] = old[NI][j];
			old[NI+1][j] = old[1][j];
		}
		for (i=1; i<=NI; i++) {
			for (j=1; j<=NJ; j++) {
				im = i-1;
				ip = i+1;
				jm = j-1;
				jp = j+1;
				nsum =  old[im][jp] + old[i][jp] + old[ip][jp]
				+ old[im][j ]              + old[ip][j ] 
				+ old[im][jm] + old[i][jm] + old[ip][jm];
				switch(nsum) {
					case 3:
					neww[i][j] = 1;
					break;
					case 2:
					neww[i][j] = old[i][j];
					break;
					default:
					neww[i][j] = 0;
				}
			}
		}
		// copy neww state into old state
		for (i=1; i<=NI; i++) {
			for (j=1; j<=NJ; j++) {
				old[i][j] = neww[i][j];
			}
		}
	}
	/*  Iterations are done; sum the number of live cells */
	isum = 0;
	for (i=1; i<=NI; i++) {
		for (j=1; j<=NJ; j++) {
			isum = isum + neww[i][j];
		}
	}
	System.out.println("\nNumber of live cells = " + isum);
 	}
 }