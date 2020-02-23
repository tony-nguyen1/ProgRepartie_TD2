package try1;

public class MatrixMultiplier extends Thread {

    static int nb = 1;
    private int perso;
    private static Matrix C;
    private Matrix A;
    private Matrix B;

    public MatrixMultiplier(Matrix first, Matrix second, int minXA, int minYA, int minXB, int minYB) {

        if (first.getN() != second.getM()) throw new RuntimeException("Illegal matrix dimensions.");

        MatrixMultiplier.C = new Matrix(first.getM(), second.getN());
        this.A = new Matrix(first.getM()/2, first.getN()/2);
        this.B = new Matrix(second.getM()/2, second.getN()/2);

        // copie profonde de la partie de A souhaitée
        for(int i = 0; i < first.getM()/2; i++){
            for (int j = 0; j < first.getN()/2; j++){
                this.A.getData()[i][j] = first.getData()[minXA + i][minYA + j];
            }
        }

        // copie profonde de la partie de B souhaitée
        for(int i = 0; i < second.getM()/2; i++){
            for (int j = 0; j < second.getN()/2; j++){
                this.B.getData()[i][j] = second.getData()[minXB + i][minYB + j];
            }
        }

        perso = nb;
        nb++;

        /*System.out.println("Matrice A de " + perso);
        this.A.show();
        System.out.println("Matrice B de " + perso);
        this.B.show();*/
    }

    @Override
    public void run() {
        if (this.B.getM() != this.A.getN()) throw new RuntimeException("Illegal matrix dimensions.");
        switch (perso){
            case 1: case 2:
                for (int i = 0; i < C.getM()/2; i++)
                    for (int j = 0; j < C.getN()/2; j++)
                        C.getData()[i][j] += A.getData()[i][j] * B.getData()[j][i];
                break;
            case 3: case 4:
                for (int i = 0; i < C.getM()/2; i++)
                    for (int j = 0; j < C.getN()/2; j++)
                        C.getData()[i][C.getN()/2 + j] += A.getData()[i][j] * B.getData()[j][i];
                break;
            case 5: case 6:
                for (int i = 0; i < C.getM()/2; i++)
                    for (int j = 0; j < C.getN()/2; j++)
                        C.getData()[C.getM()/2 + i][j] += A.getData()[i][j] * B.getData()[j][i];
                break;
            case 7: case 8:
                for (int i = 0; i < C.getM()/2; i++)
                    for (int j = 0; j < C.getN()/2; j++)
                        C.getData()[C.getM()/2 + i][C.getN()/2 + j] += A.getData()[i][j] * B.getData()[j][i];
             break;
                }
            }


    public static Matrix getC() {
        return C;
    }
}
