/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subclass;

import managerClass.Enseignant;

/**
 *
 * @author Noumodong
 */
public class TempEnseignant {

    public static Enseignant tempEnseignant;

    public TempEnseignant() {
    }

    public TempEnseignant(Enseignant tempEnseignant) {
        TempEnseignant.tempEnseignant = tempEnseignant;
    }

    public Enseignant getTempEnseignant() {
        return tempEnseignant;
    }

    public void setTempEnseignant(Enseignant tempEnseignant) {
        TempEnseignant.tempEnseignant = tempEnseignant;
    }

}
