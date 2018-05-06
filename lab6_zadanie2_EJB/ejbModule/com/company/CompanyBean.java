package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class CompanyBean
 */
@Singleton
@LocalBean
public class CompanyBean implements Serializable {

    /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<String> companies;

    /**
     * Default constructor. 
     */
  public CompanyBean() {      
    // TODO Auto-generated constructor stub
  }
  
  @PostConstruct
  private void onInit() {
      this.setCompanies(new ArrayList<>());
  }
  
  public void addCompany(String company) {
    System.out.println("CompaniesBean: adding " + company);
    companies.add(company);
  }

  public List<String> getCompanies() {
    return companies;
  }

  public void setCompanies(List<String> companies) {
    this.companies = companies;
  }

}
