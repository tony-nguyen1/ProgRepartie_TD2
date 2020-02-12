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

    /*** Fait une multiplication entre 2 éléments des Matrix M et N et additionne le produit dans la Matrix résultat matrixResult */
    @Override
    public void run() {
        double tabResult[][] = matrixResult.getData();
        double prdtM, prdtN;

        if (this.id.equals("gauche"))
        {
            prdtM = M.getData()[startR][startC];

            if (startR == 0 && startC == 1) { prdtN = N.getData()[startR + 1][startC]; }
            else if (startR == 1 && startC == 0) { prdtN = N.getData()[startR - 1][startC]; }
            else { prdtN = N.getData()[startR][startC]; }

            tabResult[startR][startC] += prdtM * prdtN;
            //System.out.println("(" + startR + "," + startC + ") = " + prdtM + " * " + prdtN + " = " + prdtM * prdtN);
        }
        else if (this.id.equals("droite"))
        {
            //choix du 1er produit de la multiplication
            if (startC == 0) { prdtM = M.getData()[startR][startC + 1]; }
            else if (startC == 1) { prdtM = M.getData()[startR][startC - 1]; }
            else { prdtM = M.getData()[startR][startC]; }

            //choix du 2e produit de la multiplication
            if (startR == 0 && startC == 0) { prdtN = N.getData()[startR + 1][startC]; }
            else if (startR == 1 && startC == 1) { prdtN = N.getData()[startR - 1][startC]; }
            else { prdtN = N.getData()[startR][startC]; }

            tabResult[startR][startC] += prdtM * prdtN;
            //System.out.println("(" + startR + "," + startC + ") = " + prdtM + " * " + prdtN + " = " + prdtM * prdtN);
        }
        else
        {
            throw new RuntimeException("wrong Operande.id : " + this.id);
        }
    }

}
