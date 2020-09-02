package dev.ihm;

import java.util.Scanner;

import org.springframework.stereotype.Controller;

import dev.entite.Plat;
import dev.service.IPlatService;

@Controller
public class Ihm {

    private IPlatService service;
    private Scanner scanner;

    public Ihm(IPlatService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void demarrer() {
        System.out.println("Saisie : ");
        String saisie = this.scanner.next();

        this.service.ajouterPlat(saisie, null);

        System.out.println("Affichage de la liste");
        for (Plat texte : this.service.listerPlats()) {
            System.out.println(texte);
        }

        System.out.println("Les plat");
        for (Plat plat : this.service.listerPlats()) {
            System.out.println("prix="+ plat.getPrixEnCentimesEuros() + " nom=" + plat.getNom());
        }
    }
}
