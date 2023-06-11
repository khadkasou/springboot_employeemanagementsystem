package com.emp.employee.repository;

import com.emp.employee.model.PayrollReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepo extends MongoRepository<PayrollReport, String> {

//    List<PayrollReport> findByMonthAndYear(String month, int year);

    List<PayrollReport> save(List<PayrollReport> reports);
    PayrollReport findByUsername(String username);

}
