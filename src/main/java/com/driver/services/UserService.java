package com.driver.services;


import com.driver.exception.UserNotFoundException;
import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.model.WebSeries;
import com.driver.repository.UserRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebSeriesRepository webSeriesRepository;


    public Integer addUser(User user){

        //Jut simply add the user to the Db and return the userId returned by the repository

        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }

    public Integer getAvailableCountOfWebSeriesViewable(Integer userId){

        //Return the count of all webSeries that a user can watch based on his ageLimit and subscriptionType
        //Hint: Take out all the Webseries from the WebRepository
        Optional<User> optionalUser = userRepository.findById(userId);

        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("Invalid user Id");
        }
        User user = optionalUser.get();
        Subscription subscription = user.getSubscription();
//        if(subscription.getSubscriptionType()==SubscriptionType.BASIC)
//            return webSeriesRepository.getAvailableCountOfWebSeriesViewableWithBasic(user.getAge());
//        else if(subscription.getSubscriptionType()==SubscriptionType.PRO)
//            return webSeriesRepository.getAvailableCountOfWebSeriesViewableWithPro(user.getAge());
//        else{
//            return webSeriesRepository.getAvailableCountOfWebSeriesViewableWithElite(user.getAge());
//        }

        List<WebSeries> webseries = webSeriesRepository.findAll();

        int count = 0;
        for(WebSeries webSeries: webseries){
            if(webSeries.getAgeLimit()>user.getAge()){
                continue;
            }
            if(subscription.getSubscriptionType().equals(SubscriptionType.ELITE)){
                count++;
            }
            else if(subscription.getSubscriptionType().equals(SubscriptionType.PRO) && !webSeries.getSubscriptionType().equals(SubscriptionType.ELITE)){
                count++;
            }
            else if (subscription.getSubscriptionType().equals(webSeries.getSubscriptionType())){
                count++;
            }
        }
        return count;
    }


}
