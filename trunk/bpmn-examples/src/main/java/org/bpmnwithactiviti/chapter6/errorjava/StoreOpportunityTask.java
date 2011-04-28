package org.bpmnwithactiviti.chapter6.errorjava;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.bpmnwithactiviti.chapter6.ws.Customer;
import org.bpmnwithactiviti.chapter6.ws.CustomerServiceService;
import org.bpmnwithactiviti.chapter6.ws.SalesOpportunity;

public class StoreOpportunityTask implements JavaDelegate {
  
  private CustomerServiceService customerService = new CustomerServiceService();

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    CustomerVariable customer = (CustomerVariable) execution.getVariable("customer");
    String product = (String) execution.getVariable("product");
    Integer expectedQuantity = (Integer) execution.getVariable("quantity");
    String description = (String) execution.getVariable("description");
    System.out.println("Storing sales opportunity for customer " + customer.getCustomerId());
    Customer responseCustomer = customerService.getCustomerServicePort().storeSalesOpportunity(product, expectedQuantity, 
            description, Long.valueOf(customer.getCustomerId()));
    System.out.println("Stored sales opportunity for customer " + responseCustomer.getCustomerName());
    for(SalesOpportunity opportunity : responseCustomer.getSaleOpportunities()) {
      System.out.println("opportunity " + opportunity.getOpportunityId() + 
              ", product " + opportunity.getProduct());
    }
  }

}