package com.example.ecomove.mapper;

import com.example.ecomove.dto.request.UserRequestDTO;
import com.example.ecomove.dto.response.UserResponseDTO;
import com.example.ecomove.entity.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Convertit un Utilisateur (Model)
    // en UserResponseDTO
    // Utilisé quand on retourne des données
    // au client
    public UserResponseDTO toResponseDTO(
            Utilisateur utilisateur) {

        if (utilisateur == null) return null;

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setEmail(utilisateur.getEmail());
        dto.setTelephone(utilisateur.getTelephone());
        dto.setRole(utilisateur.getRole());
        dto.setActif(utilisateur.isActif());

        // On récupère juste le nom de l'entreprise
        // pas tout l'objet Company
        if (utilisateur.getCompany() != null) {
            dto.setCompanyNom(
                    utilisateur.getCompany().getNom());
        }

        return dto;
    }

    // Convertit un UserRequestDTO
    // en Utilisateur (Model)
    // Utilisé quand on crée un utilisateur
    public Utilisateur toModel(
            UserRequestDTO dto) {

        if (dto == null) return null;

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setEmail(dto.getEmail());
        // Le mot de passe sera hashé dans le Service
        utilisateur.setMotDePasse(dto.getMotDePasse());
        utilisateur.setTelephone(dto.getTelephone());
        utilisateur.setRole(dto.getRole());

        return utilisateur;
    }
}
