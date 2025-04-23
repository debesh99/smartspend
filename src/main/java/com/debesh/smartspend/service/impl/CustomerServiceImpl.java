package com.debesh.smartspend.service.impl;

import com.debesh.smartspend.entity.Customer;
import com.debesh.smartspend.exceptions.InvalidCutomerCredentialException;
import com.debesh.smartspend.model.CustomerInputModel;
import com.debesh.smartspend.model.CustomerOutputModel;
import com.debesh.smartspend.model.EmailModel;
import com.debesh.smartspend.repository.CustomerRepository;
import com.debesh.smartspend.service.CustomerService;
import com.debesh.smartspend.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;


    @Override
    public CustomerOutputModel registerCustomer(CustomerInputModel customerInputModel) throws InvalidCutomerCredentialException {
        Customer customer = modelMapper.map(customerInputModel, Customer.class);
        customerRepository.save(customer);
        CustomerOutputModel customerOutputModel = modelMapper.map(customer, CustomerOutputModel.class);

//      Send welcome email
        EmailModel emailModel = new EmailModel();
        emailModel.setRecipient(customerOutputModel.getEmail());
        emailModel.setSubject("Welcome to Smart Spend!");
        emailModel.setMsgBody("Dear " + customerOutputModel.getFirstName() + ",\n\n" +
                "Welcome to Smart Spend! We are thrilled to have you join our community of savvy spenders.\n\n" +
                "Smart Spend is more than just a personal finance application; it’s your companion on the journey to better financial health. With our easy-to-use platform, you can effortlessly keep track of your incomes and expenses, providing you with a clear and comprehensive view of your financial situation.\n\n" +
                "Our tools are designed to help you take control of your expenditures, enabling you to make informed decisions about your money. Whether you’re looking to save for a big purchase, manage monthly bills, or simply gain insights into your spending habits, Smart Spend is here to assist you every step of the way.\n\n" +
                "We encourage you to explore all the features we offer, from budgeting tools to spending alerts, and to customize your experience to meet your personal financial goals. Our mission is to empower you to achieve financial freedom and make every dollar count.\n\n" +
                "Thank you for choosing Smart Spend. We look forward to helping you reach your financial dreams!\n\n" +
                "Best regards,\n" +
                "The Smart Spend Team");

        emailService.sendEmail(emailModel);

        return  customerOutputModel;
    }
}
