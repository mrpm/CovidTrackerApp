package com.covidtracker.controller;

import com.covidtracker.model.SelfAssessmentRequest;
import com.covidtracker.model.UserRequest;
import com.covidtracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidController {

    @Autowired
    TrackerService trackerService;

    @PostMapping("/registerUser")
    public int registerUser(@RequestBody UserRequest userRequest){

        int userId = trackerService.registerUser(userRequest);
        return userId;
    }

    @PostMapping("/registerAdmin")
    public int registerAdmin(@RequestBody UserRequest userRequest){

        int userId = trackerService.registerAdmin(userRequest);
        return userId;
    }

    @PostMapping("/selfAssessment")
    public int selfAssessment(@RequestBody SelfAssessmentRequest selfAssessment){

        int riskScore = trackerService.selfAssessment(selfAssessment);
        return riskScore;
    }
/*

    @PutMapping("/updateCovidResult")
    public int updateCovidResult(@RequestBody SelfAssessmentRequest selfAssessment){

        int riskScore = trackerService.selfAssessment(selfAssessment);
        return riskScore;
    }
*/


}
