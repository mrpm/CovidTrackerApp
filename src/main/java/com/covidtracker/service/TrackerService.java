package com.covidtracker.service;

import com.covidtracker.dao.UserRepository;
import com.covidtracker.model.SelfAssessmentRequest;
import com.covidtracker.model.User;
import com.covidtracker.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TrackerService {

    @Autowired
    UserRepository userRepository;

    AtomicInteger userId = new AtomicInteger(1);

    public Integer generateUserId(){
        return userId.getAndIncrement();
    }


    public Integer registerUser(UserRequest userRequest){

        User user = new User();
        user.setName(userRequest.getName());
     //   user.setUserId(generateUserId().intValue());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPinCode(userRequest.getPinCode());
        userRepository.save(user);

        return user.getUserId();
    }

    public Integer registerAdmin(UserRequest userRequest){

        User user = new User();
        user.setName(userRequest.getName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPinCode(userRequest.getPinCode());
       // user.setUserId(generateUserId().intValue());
        user.setAdmin(true);
        userRepository.save(user);

        return user.getUserId();
    }

    public Integer selfAssessment(SelfAssessmentRequest selfAssessmentRequest){

        User user = userRepository.getById(selfAssessmentRequest.getUserId());

        user.setTravelHistory(selfAssessmentRequest.isTravelHistory());
        user.setContactWithCovidPatient(selfAssessmentRequest.isContactWithCovidPatient());
        RiskCalculator riskCalculator = new RiskCalculator();
        int riskScore = riskCalculator.calculateRiskScore(selfAssessmentRequest);
        user.setRiskPercentage(riskScore);
        userRepository.save(user);
        return riskScore;
    }


}
