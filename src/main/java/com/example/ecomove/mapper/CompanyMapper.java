package com.example.ecomove.mapper;

import com.example.ecomove.dto.request.CompanyRequestDTO;
import com.example.ecomove.dto.response.CompanyResponseDTO;
import com.example.ecomove.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    // Convertit une Company (Model)
    // en CompanyResponseDTO
    public CompanyResponseDTO toResponseDTO(
            Company company) {

        if (company == null) return null;

        CompanyResponseDTO dto =
                new CompanyResponseDTO();

        dto.setId(company.getId());
        dto.setNom(company.getNom());
        dto.setAdresse(company.getAdresse());
        dto.setEmail(company.getEmail());
        dto.setTelephone(company.getTelephone());
        dto.setActif(company.isActif());

        // Nombre d'employés dans l'entreprise
        dto.setNombreEmployes(
                company.getEmployes() != null
                        ? company.getEmployes().size()
                        : 0);

        return dto;
    }

    // Convertit un CompanyRequestDTO
    // en Company (Model)
    public Company toModel(
            CompanyRequestDTO dto) {

        if (dto == null) return null;

        Company company = new Company();
        company.setNom(dto.getNom());
        company.setAdresse(dto.getAdresse());
        company.setEmail(dto.getEmail());
        company.setTelephone(dto.getTelephone());

        return company;
    }
}
