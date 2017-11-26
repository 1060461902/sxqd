package edu.zjgsu.ito.model;

import java.util.Date;

public class Company {
    private String id;

    private String companyName;

    private String address;

    private String userId;

    private Date createTime;

    private String email;

    private String type;

    private String logo;

    private String network;

    private String size;

    private String stage;

    private String slogans;

    private String intruction;

    private Boolean checked;

    private Boolean pass;

    private String licence;

    private String taxRegistration;

    private String organizationCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage == null ? null : stage.trim();
    }

    public String getSlogans() {
        return slogans;
    }

    public void setSlogans(String slogans) {
        this.slogans = slogans == null ? null : slogans.trim();
    }

    public String getIntruction() {
        return intruction;
    }

    public void setIntruction(String intruction) {
        this.intruction = intruction == null ? null : intruction.trim();
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence == null ? null : licence.trim();
    }

    public String getTaxRegistration() {
        return taxRegistration;
    }

    public void setTaxRegistration(String taxRegistration) {
        this.taxRegistration = taxRegistration == null ? null : taxRegistration.trim();
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }
}