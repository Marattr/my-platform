package com.ihs.customerservice.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private Long id;
    private Long parentId;
    private Integer customerStatus;
    private Long customerCode;
    private boolean gsmValidated;
    private boolean emailValidated;
    private boolean addressValidated;
    private boolean enabled;
    private String emailAddress;
    private String gsmTel;
    private String tel1;
    private String tel2;
    private String contactName;
    private int exchangeTypeId;
    private String directiId;
    private String fax;
    private boolean hirt;
    private String notes;
    private Integer directiContactId;
    private Integer directiEuRegContactId;
    private Integer directiEuTechContactId;
    private String gsmValidationCode;
    private int role;
    private boolean emailCampaign;
    private boolean emailAnnouncement;
    private boolean domainSeller;
    private boolean otpEnabled;
    private String otpCredentialId;
    private String otpCredentialId2;
    private Date creationDate;
    private int otpCandidate;
    private boolean otpSent;
    private String otpSentDate;
    private Boolean kaspersky;
    private Integer directiCoContactId;
    private Integer directiCoopContactId;
    private Integer directiCaContactId;
    private Integer directiDeContactId;
    private Integer directiEsContactId;
    private Integer directiRuContactId;
    private boolean enterpriseCustomer;
    private Integer directiNlContactId;
    private boolean isCalled;
    private String zohoAccountId;
    private String calledDate;
    private String supportPinId;
    private Integer directiIstContactId;
    private String senderKingId;
    private boolean foreignCreditCardEnabled;
    private boolean ihsMoneyDisabled;
    private Date lastLoginTrialTime;
    private Integer loginTrialCount;
    private String ticketNote;
    private String passwdHash;
    private Boolean impersonateUser;
    private Date lastBlockageTime;
    private Date lastLoginTime;
}
