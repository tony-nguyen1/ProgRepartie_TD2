package main.try2;
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

    /*** Fait une multiplication entre 2 éléments des main.try1.Matrix M et N et additionne le produit dans la main.try1.Matrix résultat matrixResult */
    @Override
    public void run() {
        final int C1 = id.equals("gauche") ? 0 : M.getN()/2;
        final int C2 = id.equals("gauche") ? 0 : M.getN()/2;

        for (int c = startC; c < startC + dimLongueur; c++) {
            for (int r = startR; r < startR + dimLargeur; r++) {
                for (int k = 0; k < (M.getN() / 2); k++) {
                    System.out.println("(" + c + "," + r + ") = M(" + c + "," + (k+C1) + ") * N(" + (k+C2) + "," + r + ")");
                    //System.out.println("" + M.getData()[c][k] * N.getData()[k][r] + " = " + M.getData()[c][k] + " * " + N.getData()[k][r]);

                    maj(c,r,(M.getData()[c][k+C1] * N.getData()[k+C2][r]));
                }
            }
        }
    }

    public synchronized void maj(int i, int j, double val){ //TODO ici ça marche pas
        matrixResult.getData()[i][j] += val;
    }
}
