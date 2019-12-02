package com.koowakchai.errand.service.impl;

import com.koowakchai.errand.service.TErrandService;
import org.springframework.stereotype.Service;

@Service
public class TErrandServiceImpl implements TErrandService {
    @Override
    public double checkErrandFee(double distance, double weight, String errandType, String deliveryTime) throws Exception {
        double deliveryFee = 0;
        double startFee = 0;
        double distanceRate = 0;
        double specialTimeFee = 0;
        double weightFee = 0;
        String[] dateAndTime = deliveryTime.split(" ");
        String time =  dateAndTime[1];
        String strPattern = "^0+";
        int hour = Integer.valueOf(time.substring(0,2).replaceAll(strPattern,""));
        if (errandType.equals("procurement service")){
            startFee = 6;
            if (distance < 2){
                distanceRate = 1;
            }
            else{
                distanceRate = 1.2;
            }

            if ((hour > 0 && hour < 7) || (hour > 10 && hour < 24) ){
                specialTimeFee = 2;
            }
            return distance*distanceRate+startFee+specialTimeFee;
        }
        else{
            startFee = 4;
            if (distance < 2){
                distanceRate = 0.8;
            }
            else{
                distanceRate = 1;
            }
            if ((hour > 0 && hour < 7) || (hour > 10 && hour < 24)){
                specialTimeFee = 1;
            }
            if (6 < weight && weight < 10){
                weightFee += 1;
            }
            else if (weight >= 10){
                weightFee += 2;
            }
            return distance*distanceRate+startFee+specialTimeFee+weightFee;
        }
    }
}
