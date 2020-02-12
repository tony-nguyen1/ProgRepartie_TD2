public class Operande extends Thread {

    /*** La matrice résultat ***/
    private static Matrix result;

    /*** Les matrices M et N ***/
    private Matrix M;
    private Matrix N;

    /*** Point d'origine de la sous-matrice ***/
    private int startR;
    private int startC;

    /*** Dimensions de la sous-matrice considéree ***/
    private int dimLargeur;
    private int dimLongueur;

    /*** Variables de calcul***/
    private double sommeGauche;
    private double sommeDroite;

    /*** Pour la reconnaitre ***/
    private String id;

    //TODO faire des variables pour la matrice M et les mêmes pour la matrice N

    /***
     * Fait le calcul ou continue de faire des threads
     */
    public void calcul() {
        Operande uneOperande;
        if (dimLargeur == 1 || dimLongueur == 1)
        {
            double terme1, terme2;
            //on fait la multiplication
            if (id.equals("gauche")){
                terme1 = M.getData()[0][0];
            } else if (id.equals("droite")) {

            }
        }
        else {
            //on crée un nouveau thread Operande
            uneOperande = new Operande();

            uneOperande.start();
        }

    }

}
