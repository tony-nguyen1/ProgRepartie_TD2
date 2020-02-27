package try2;
@SuppressWarnings("Duplicates")
public class Operande extends Thread {

    /*** La matrice résultat ***/
    private Matrix matrixResult;

    /*** Les matrices M et N ***/
    private Matrix M;
    private Matrix N;

    /*** Point d'origine de la sous-matrice ***/
    private int startR;
    private int startC;

    /*** Dimensions de la sous-matrice considéree ***/
    private int dimLargeur;
    private int dimLongueur;

    /*** Pour la reconnaitre ***/
    private String id;

    public Operande(Matrix result, int startR, int startC, int dimLargeur, int dimLongueur, Matrix m, Matrix n, String id) {
        this.matrixResult = result;
        M = m;
        N = n;
        this.startR = startR;
        this.startC = startC;
        this.dimLargeur = dimLargeur;
        this.dimLongueur = dimLongueur;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Operande{" +
                "startR=" + startR +
                ", startC=" + startC +
                ", dimLargeur=" + dimLargeur +
                ", dimLongueur=" + dimLongueur +
                ", id='" + id + '\'' +
                '}';
    }

    /*** Fait une multiplication entre 2 éléments des try1.Matrix M et N et additionne le produit dans la try1.Matrix résultat matrixResult */
    @Override
    public void run() {
        final int C1 = id.equals("gauche") ? 0 : dimLargeur;
        final int C2 = id.equals("gauche") ? 0 : dimLongueur;

        int c = 0, r = 0, k = 0;

        System.out.println(startR + dimLargeur);

        for (c = startC; c < startC + dimLongueur ; c++) {
            for (r = startR; r < startR + dimLargeur +1; r++) {
                for (k = 0; k < (M.getN() / 2); k++) {
                    //System.out.println("(" + c + "," + r + ") = M(" + c + "," + k + ") * N(" + k + "," + r + ")");
                    //System.out.println("" + M.getData()[c][k] * N.getData()[k][r] + " = " + M.getData()[c][k] + " * " + N.getData()[k][r]);

                    matrixResult.getData()[c][r] += (M.getData()[c][k+C1] * N.getData()[k+C2][r]);
                    Matrix.incrementeComplexity();
                    System.out.println("" + c + r + k);
                }
            }
        }
    }
}
