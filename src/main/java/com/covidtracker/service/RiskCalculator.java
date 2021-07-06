package com.covidtracker.service;

import com.covidtracker.model.SelfAssessmentRequest;

public class RiskCalculator {


    public int calculateRiskScore(SelfAssessmentRequest selfAssessmentRequest) {
        int risk = 5;
        if (!selfAssessmentRequest.isTravelHistory() && !selfAssessmentRequest.isContactWithCovidPatient() &&
                selfAssessmentRequest.getSymptoms().size() == 0) {
            risk =5;
        } else if ((selfAssessmentRequest.isTravelHistory() || selfAssessmentRequest.isContactWithCovidPatient()) && selfAssessmentRequest.getSymptoms().size() == 1) {
            risk = 50;
        } else if ((selfAssessmentRequest.isTravelHistory() || selfAssessmentRequest.isContactWithCovidPatient()) && selfAssessmentRequest.getSymptoms().size() == 2) {
            risk = 75;
        } else if ((selfAssessmentRequest.isTravelHistory() || selfAssessmentRequest.isContactWithCovidPatient()) && selfAssessmentRequest.getSymptoms().size() > 2) {
            risk = 95;
        }
        return risk;
    }

}