package com.example.ecomove.dto.response;

public class CarbonReportResponseDTO {

    private Long userId;
    private String nomUtilisateur;
    private String nomCompany;
    private Double totalEconomieCo2Kg;
    private Double equivalentArbres;
    private Integer nombreTrajets;
    private Double totalKilometres;
    private String periode;

    // GETTERS
    public Long getUserId() { return userId; }
    public String getNomUtilisateur() { return nomUtilisateur; }
    public String getNomCompany() { return nomCompany; }
    public Double getTotalEconomieCo2Kg() { return totalEconomieCo2Kg; }
    public Double getEquivalentArbres() { return equivalentArbres; }
    public Integer getNombreTrajets() { return nombreTrajets; }
    public Double getTotalKilometres() { return totalKilometres; }
    public String getPeriode() { return periode; }

    // SETTERS
    public void setUserId(Long userId) { this.userId = userId; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }
    public void setNomCompany(String nomCompany) { this.nomCompany = nomCompany; }
    public void setTotalEconomieCo2Kg(Double totalEconomieCo2Kg) { this.totalEconomieCo2Kg = totalEconomieCo2Kg; }
    public void setEquivalentArbres(Double equivalentArbres) { this.equivalentArbres = equivalentArbres; }
    public void setNombreTrajets(Integer nombreTrajets) { this.nombreTrajets = nombreTrajets; }
    public void setTotalKilometres(Double totalKilometres) { this.totalKilometres = totalKilometres; }
    public void setPeriode(String periode) { this.periode = periode; }
}