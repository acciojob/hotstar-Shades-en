package com.driver.controllers;


import com.driver.EntryDto.SubscriptionEntryDto;
import com.driver.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping("/buy")
    public Integer buySubscription(SubscriptionEntryDto subscriptionEntryDto){

        //We need to buy subscription and save its relevant subscription to the db and return the finalAmount

        return subscriptionService.buySubscription(subscriptionEntryDto);
    }


    @PutMapping("/upgradeSubscription/{userId}")
    public Integer upgradeSubscription(@PathVariable("userId")Integer userId){

        //In this function you need to upgrade the subscription to  its next level
        //ie if You are A BASIC subscriber update to PRO and if You are a PRO upgrade to ELITE.
        //Incase you are already an ELITE member throw an Exception
        //and at the end return the difference in fare that you need to pay to get this subscription done.
        try{
            return subscriptionService.upgradeSubscription(userId);
        }catch (Exception e){
            return -1;
        }
    }

    @GetMapping("/calculateTotalRevenue")
    public Integer getTotalRevenue(){

        //Calculate the total Revenue of hot-star from all the Users combined...
        return subscriptionService.calculateTotalRevenueOfHotstar();

    }

}
