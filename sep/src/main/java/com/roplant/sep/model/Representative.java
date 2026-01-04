package com.roplant.sep.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Representative extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID representativeID;

    private String ntn;
    private String authorizationLevel;

    @Column(nullable = false)
    private BigDecimal representativeBalance = BigDecimal.ZERO;

    public UUID getRepresentativeID() { return representativeID; }
    public void setRepresentativeID(UUID representativeID) { this.representativeID = representativeID; }

    public String getNtn() { return ntn; }
    public void setNtn(String ntn) { this.ntn = ntn; }

    public String getAuthorizationLevel() { return authorizationLevel; }
    public void setAuthorizationLevel(String authorizationLevel) { this.authorizationLevel = authorizationLevel; }

    public BigDecimal getRepresentativeBalance() { return representativeBalance; }
    public void setRepresentativeBalance(BigDecimal representativeBalance) { this.representativeBalance = representativeBalance; }
}