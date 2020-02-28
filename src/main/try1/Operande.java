package main.try1;

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

    /*** Fait une multiplication entre 2 éléments des main.try1.Matrix M et N et additionne le produit dans la main.try1.Matrix résultat matrixResult */
    @Override
    public void run() {
        double tabResult[][] = matrixResult.getData();
        double facteurM, facteurN;

        Matrix.incrementeComplexity();

        if (this.id.equals("gauche"))
        {
            facteurM = M.getData()[startR][startC];

            if (startR == 0 && startC == 1) { facteurN = N.getData()[startR + 1][startC]; }
            else if (startR == 1 && startC == 0) { facteurN = N.getData()[startR - 1][startC]; }
            else { facteurN = N.getData()[startR][startC]; }
        }
        else if (this.id.equals("droite"))
        {
            //choix du 1er facteur de la multiplication
            if (startC == 0) { facteurM = M.getData()[startR][startC + 1]; }
            else if (startC == 1) { facteurM = M.getData()[startR][startC - 1]; }
            else { facteurM = M.getData()[startR][startC]; }

            //choix du 2e facteur de la multiplication
            if (startR == 0 && startC == 0) { facteurN = N.getData()[startR + 1][startC]; }
            else if (startR == 1 && startC == 1) { facteurN = N.getData()[startR - 1][startC]; }
            else { facteurN = N.getData()[startR][startC]; }
        }
        else
        {
            throw new RuntimeException("wrong main.try1.Operande.id : " + this.id);
        }

        //ajout du résultat dans la bonne case
        tabResult[startR][startC] += facteurM * facteurN;
        //System.out.println("(" + startR + "," + startC + ") = " + facteurM + " * " + facteurN + " = " + facteurM * facteurN);
    }

}
