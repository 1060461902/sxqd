package edu.zjgsu.ito.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Company implements Serializable {
    private Integer id;

    private String companyName;

    private String address;

    /**
     * 负责人id
     */
    private Integer userId;

    private Date createTime;

    /**
     * 邮件
     */
    private String email;

    /**
     * 企业类型
     */
    private String type;

    /**
     * 标志
     */
    private String logo;

    /**
     * 网址
     */
    private String network;

    /**
     * 规模
     */
    private String size;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 标语
     */
    private String slogans;

    /**
     * 简介
     */
    private String intruction;

    /**
     * 0代表未审批，1代表已审批
     */
    private Boolean checked;

    /**
     * 0代表审批未通过，1代表审批通过
     */
    private Boolean pass;

    private String licence;

    private String taxRegistration;

    private String organizationCode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSlogans() {
        return slogans;
    }

    public void setSlogans(String slogans) {
        this.slogans = slogans;
    }

    public String getIntruction() {
        return intruction;
    }

    public void setIntruction(String intruction) {
        this.intruction = intruction;
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
        this.licence = licence;
    }

    public String getTaxRegistration() {
        return taxRegistration;
    }

    public void setTaxRegistration(String taxRegistration) {
        this.taxRegistration = taxRegistration;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Company other = (Company) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getNetwork() == null ? other.getNetwork() == null : this.getNetwork().equals(other.getNetwork()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getStage() == null ? other.getStage() == null : this.getStage().equals(other.getStage()))
            && (this.getSlogans() == null ? other.getSlogans() == null : this.getSlogans().equals(other.getSlogans()))
            && (this.getIntruction() == null ? other.getIntruction() == null : this.getIntruction().equals(other.getIntruction()))
            && (this.getChecked() == null ? other.getChecked() == null : this.getChecked().equals(other.getChecked()))
            && (this.getPass() == null ? other.getPass() == null : this.getPass().equals(other.getPass()))
            && (this.getLicence() == null ? other.getLicence() == null : this.getLicence().equals(other.getLicence()))
            && (this.getTaxRegistration() == null ? other.getTaxRegistration() == null : this.getTaxRegistration().equals(other.getTaxRegistration()))
            && (this.getOrganizationCode() == null ? other.getOrganizationCode() == null : this.getOrganizationCode().equals(other.getOrganizationCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getNetwork() == null) ? 0 : getNetwork().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getStage() == null) ? 0 : getStage().hashCode());
        result = prime * result + ((getSlogans() == null) ? 0 : getSlogans().hashCode());
        result = prime * result + ((getIntruction() == null) ? 0 : getIntruction().hashCode());
        result = prime * result + ((getChecked() == null) ? 0 : getChecked().hashCode());
        result = prime * result + ((getPass() == null) ? 0 : getPass().hashCode());
        result = prime * result + ((getLicence() == null) ? 0 : getLicence().hashCode());
        result = prime * result + ((getTaxRegistration() == null) ? 0 : getTaxRegistration().hashCode());
        result = prime * result + ((getOrganizationCode() == null) ? 0 : getOrganizationCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyName=").append(companyName);
        sb.append(", address=").append(address);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", email=").append(email);
        sb.append(", type=").append(type);
        sb.append(", logo=").append(logo);
        sb.append(", network=").append(network);
        sb.append(", size=").append(size);
        sb.append(", stage=").append(stage);
        sb.append(", slogans=").append(slogans);
        sb.append(", intruction=").append(intruction);
        sb.append(", checked=").append(checked);
        sb.append(", pass=").append(pass);
        sb.append(", licence=").append(licence);
        sb.append(", taxRegistration=").append(taxRegistration);
        sb.append(", organizationCode=").append(organizationCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}