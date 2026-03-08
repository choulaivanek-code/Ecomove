package com.example.ecomove.service;

import com.example.ecomove.dto.request.UserRequestDTO;
import com.example.ecomove.dto.response.UserResponseDTO;
import com.example.ecomove.exception.AuthException;
import com.example.ecomove.exception.ResourceNotFoundException;
import com.example.ecomove.mapper.UserMapper;
import com.example.ecomove.entity.Company;
import com.example.ecomove.entity.Utilisateur;
import com.example.ecomove.repository.CompanyRepository;
import com.example.ecomove.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Récupérer tous les utilisateurs
    public List<UserResponseDTO> recupererTous() {
        return utilisateurRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un utilisateur par id
    public UserResponseDTO recupererParId(
            Long id) {

        Utilisateur utilisateur =
                utilisateurRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur", id));

        return userMapper.toResponseDTO(utilisateur);
    }

    // Modifier un utilisateur
    public UserResponseDTO modifier(
            Long id, UserRequestDTO dto) {

        Utilisateur utilisateur =
                utilisateurRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur", id));

        // Vérifier si le nouvel email
        // n'est pas déjà pris par quelqu'un
        // d'autre
        if (!utilisateur.getEmail()
                .equals(dto.getEmail())
                && utilisateurRepository
                .existsByEmail(dto.getEmail())) {
            throw new AuthException(
                    "Email déjà utilisé : "
                            + dto.getEmail());
        }

        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setTelephone(
                dto.getTelephone());
        utilisateur.setRole(dto.getRole());

        // Mettre à jour le mot de passe
        // seulement s'il est fourni
        if (dto.getMotDePasse() != null
                && !dto.getMotDePasse()
                .isEmpty()) {
            utilisateur.setMotDePasse(
                    passwordEncoder.encode(
                            dto.getMotDePasse()));
        }

        // Mettre à jour l'entreprise
        if (dto.getCompanyId() != null) {
            Company company =
                    companyRepository
                            .findById(dto.getCompanyId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Company",
                                            dto.getCompanyId()));
            utilisateur.setCompany(company);
        }

        return userMapper.toResponseDTO(
                utilisateurRepository
                        .save(utilisateur));
    }

    // Désactiver un utilisateur
    // On ne supprime jamais en BDD !
    public void desactiver(Long id) {

        Utilisateur utilisateur =
                utilisateurRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Utilisateur", id));

        utilisateur.setActif(false);
        utilisateurRepository.save(utilisateur);
    }

    // Récupérer les utilisateurs
    // d'une entreprise
    public List<UserResponseDTO>
    recupererParCompany(Long companyId) {

        return utilisateurRepository
                .findByCompanyId(companyId)
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
