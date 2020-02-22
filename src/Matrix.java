import java.sql.SQLOutput;

/******************************************************************************
 *  Compilation:  javac Matrix.java
 *  Execution:    java Matrix
 *                  https://introcs.cs.princeton.edu/java/95linear/Matrix.java
 *  A bare-bones immutable data type for M-by-N matrices.
 *
 ******************************************************************************/

final public class Matrix{
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final double[][] data;   // M-by-N array
    private static int complexity;

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matrix(Matrix A) { this(A.data); }

    public double[][] getData() { return data; }

    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // create and return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");

        Matrix.complexity = 0;
        Matrix C = new Matrix(this.M, B.N);

        for (int c = 0; c < A.M; c++) {
            for (int r = 0; r < B.N; r++) {
                for (int k = 0; k < A.N; k++) {
                    C.data[c][r] += (A.data[c][k] * B.data[k][r]);
                    Matrix.complexity++;
                }
            }
        }
        return C;
    }

    public static void incrementeComplexity() {
        Matrix.complexity++;
    }

    /***
     * Pré-requis :
     * this et B, 2 Matrix de largeur N = 2 et longeur M = 2, 2*2
     *
     * Fait un produit matricielle avec des Threads
     *
     * @param B la matrice droite à multiplier
     * @return le produit de this et B
     */
    public Matrix prdtMatricielle(Matrix B) {
        Matrix result = new Matrix(this.M, B.N); //initialisé à 0.000
        Matrix A = this;

        //dimensions des sous matrices
        int lar, lon;
        lon = A.M/2;
        lar = A.N/2;

        //Création des 8 threads
        Operande op1_gauche, op2_gauche, op3_gauche, op4_gauche, op1_droite, op2_droite, op3_droite, op4_droite;
        op1_gauche = new Operande(result, 0,          0, lar, lon, A, B, "gauche");
        op2_gauche = new Operande(result,0,       A.N/2, lar, lon, A, B, "gauche");
        op3_gauche = new Operande(result, A.M/2,      0, lar, lon, A, B, "gauche");
        op4_gauche = new Operande(result, A.M/2,          lar,  lar, lon, A, B, "gauche");
        op1_droite = new Operande(result, 0,          0, lar, lon, A, B, "droite");
        op2_droite = new Operande(result,0,       A.N/2, lar, lon, A, B, "droite");
        op3_droite = new Operande(result, A.M/2,      0, lar, lon, A, B, "droite");
        op4_droite = new Operande(result, A.M/2, A.N/2,  lar, lon, A, B, "droite");

        //démarrage des threads
        op1_gauche.start();
        op2_gauche.start();
        op3_gauche.start();
        op4_gauche.start();
        op1_droite.start();
        op2_droite.start();
        op3_droite.start();
        op4_droite.start();

        //pour assurer la fin du traitement de TOUS les threads
        try {
            op1_gauche.join();
            op2_gauche.join();
            op3_gauche.join();
            op4_gauche.join();

            op1_droite.join();
            op2_droite.join();
            op3_droite.join();
            op4_droite.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%9.4f ", data[i][j]);
            System.out.println();
        }
    }

    // print complexity of the multiplication to standard output
    static public void showComp() {
        System.out.print(" Multiplications :  " + Matrix.complexity);
        System.out.println();
    }

    // test client
    public static void main(String[] args) {
        Matrix A = new Matrix(new double[][]{
                        {1.0, 2.0},
                        {3.0,4.0}});
        Matrix B = new Matrix(new double[][]{
                {5.0, 6.0},
                {7.0,8.0}});

        System.out.println("Matrix A");
        A.show();

        System.out.println("Matrix B");
        B.show();

        long a = System.currentTimeMillis();
        Matrix C = A.prdtMatricielle(B);
        long b = System.currentTimeMillis();

        System.out.println("Matrix C");
        C.show();

        Matrix.showComp();
        System.out.println(b - a + " ms");

    }
}