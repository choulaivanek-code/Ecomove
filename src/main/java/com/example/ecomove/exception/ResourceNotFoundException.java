package com.example.ecomove.exception;

public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(
            String ressource, Long id) {
        super(ressource + " introuvable "
                + "pour l'id : " + id);
    }

    public ResourceNotFoundException(
            String ressource, String champ,
            String valeur) {
        super(ressource + " introuvable "
                + "pour " + champ
                + " : " + valeur);
    }
}
