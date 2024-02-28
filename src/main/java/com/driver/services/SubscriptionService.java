package com.driver.services;


import com.driver.EntryDto.SubscriptionEntryDto;
import com.driver.exception.NonUpgradableSubscription;
import com.driver.exception.UserNotFoundException;
import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.repository.SubscriptionRepository;
import com.driver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    public Integer getAmountToPay(SubscriptionType subscriptionType, int noOfScreens){
        int pricePlan, variable;
        if(subscriptionType == SubscriptionType.BASIC){
            pricePlan = 500;
            variable = 200;
        } else if(subscriptionType == SubscriptionType.PRO) {
            pricePlan = 800;
            variable = 250;
        } else {
            pricePlan = 1000;
            variable = 350;
        }

        return pricePlan + (variable * noOfScreens);
    }

    public Integer buySubscription(SubscriptionEntryDto subscriptionEntryDto){

        //Save The subscription Object into the Db and return the total Amount that user has to pay

        int amountPaid = getAmountToPay(subscriptionEntryDto.getSubscriptionType(), subscriptionEntryDto.getNoOfScreensRequired());

        Subscription subscription = new Subscription(
                subscriptionEntryDto.getSubscriptionType(),
                subscriptionEntryDto.getNoOfScreensRequired(),
                new Date(),
                amountPaid
        );

        User user = userRepository.findById(subscriptionEntryDto.getUserId()).get();
        subscription.setUser(user);
        user.setSubscription(subscription);

        subscriptionRepository.save(subscription);
        userRepository.save(user);

        return subscription.getTotalAmountPaid();
    }

    public Integer upgradeSubscription(Integer userId)throws Exception{

        //If you are already at an ElITE subscription : then throw Exception ("Already the best Subscription")
        //In all other cases just try to upgrade the subscription and tell the difference of price that user has to pay
        //update the subscription in the repository
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("Invalid user Id");
        }
        User user = optionalUser.get();
        Subscription subscription = user.getSubscription();
        Integer newAmountToPay;
        if(subscription.getSubscriptionType()==SubscriptionType.ELITE){
            throw new NonUpgradableSubscription("Already the best Subscription");
        }
        else if(subscription.getSubscriptionType()==SubscriptionType.BASIC){
            subscription.setSubscriptionType(SubscriptionType.PRO);
            newAmountToPay = getAmountToPay(SubscriptionType.PRO, subscription.getNoOfScreensSubscribed());

        } else {
            subscription.setSubscriptionType(SubscriptionType.ELITE);
            newAmountToPay = getAmountToPay(SubscriptionType.ELITE, subscription.getNoOfScreensSubscribed());
        }
        Integer amountDifference = newAmountToPay - subscription.getTotalAmountPaid();
        subscription.setTotalAmountPaid(newAmountToPay);
        subscriptionRepository.save(subscription);
        return amountDifference;
    }

    public Integer calculateTotalRevenueOfHotstar(){

        //We need to find out total Revenue of hotstar : from all the subscriptions combined
        //Hint is to use findAll function from the SubscriptionDb
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        int totalRevenue = 0;
        for(Subscription subscription: subscriptions){
            totalRevenue+= subscription.getTotalAmountPaid();
        }

        return totalRevenue;
    }

}
