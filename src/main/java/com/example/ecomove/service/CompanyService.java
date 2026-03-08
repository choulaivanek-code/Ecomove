package com.example.ecomove.service;

import com.example.ecomove.dto.request.CompanyRequestDTO;
import com.example.ecomove.dto.response.CompanyResponseDTO;
import com.example.ecomove.exception.AuthException;
import com.example.ecomove.exception.ResourceNotFoundException;
import com.example.ecomove.mapper.CompanyMapper;
import com.example.ecomove.entity.Company;
import com.example.ecomove.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    // Créer une entreprise partenaire
    public CompanyResponseDTO creer(
            CompanyRequestDTO dto) {

        // Vérifier que l'email n'existe pas
        if (companyRepository
                .existsByEmail(dto.getEmail())) {
            throw new AuthException(
                    "Email entreprise déjà utilisé : "
                            + dto.getEmail());
        }

        // Vérifier que le nom n'existe pas
        if (companyRepository
                .existsByNom(dto.getNom())) {
            throw new AuthException(
                    "Entreprise déjà enregistrée : "
                            + dto.getNom());
        }

        Company company =
                companyMapper.toModel(dto);

        return companyMapper.toResponseDTO(
                companyRepository.save(company));
    }

    // Récupérer toutes les entreprises
    public List<CompanyResponseDTO>
    recupererToutes() {

        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Récupérer une entreprise par id
    public CompanyResponseDTO recupererParId(
            Long id) {

        Company company = companyRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company", id));

        return companyMapper
                .toResponseDTO(company);
    }

    // Modifier une entreprise
    public CompanyResponseDTO modifier(
            Long id, CompanyRequestDTO dto) {

        Company company = companyRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company", id));

        company.setNom(dto.getNom());
        company.setAdresse(dto.getAdresse());
        company.setEmail(dto.getEmail());
        company.setTelephone(dto.getTelephone());

        return companyMapper.toResponseDTO(
                companyRepository.save(company));
    }

    // Désactiver une entreprise
    public void desactiver(Long id) {

        Company company = companyRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company", id));

        company.setActif(false);
        companyRepository.save(company);
    }
}
