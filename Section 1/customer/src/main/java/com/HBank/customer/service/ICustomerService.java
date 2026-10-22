package com.HBank.customer.service;

import com.HBank.customer.dto.CustomerDto;
import com.example.hbankbom.dto.MobileNumberUpdateDto;
public interface ICustomerService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createCustomer(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerDto fetchCustomer(String mobileNumber);

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateCustomer(CustomerDto customerDto);

    /**
     * @param customerId - Input Customer ID
     * @return boolean indicating if the delete of Customer details is successful or not
     */
    boolean deleteCustomer(String customerId);
    boolean updateMobileNumber(MobileNumberUpdateDto mobileNumberUpdateDto);


    boolean rollbackMobileNumber(MobileNumberUpdateDto mobileNumberUpdateDto);

}
