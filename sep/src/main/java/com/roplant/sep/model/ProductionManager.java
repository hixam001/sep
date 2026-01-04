package com.roplant.sep.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class ProductionManager extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ntn;
    private String eLink;
    private String status;
    private String authorizationLevel;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNtn() { return ntn; }
    public void setNtn(String ntn) { this.ntn = ntn; }

    public String geteLink() { return eLink; }
    public void seteLink(String eLink) { this.eLink = eLink; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAuthorizationLevel() { return authorizationLevel; }
    public void setAuthorizationLevel(String authorizationLevel) { this.authorizationLevel = authorizationLevel; }
}