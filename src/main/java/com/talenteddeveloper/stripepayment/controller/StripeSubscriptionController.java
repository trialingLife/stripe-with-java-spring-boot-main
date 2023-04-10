package com.talenteddeveloper.stripepayment.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.talenteddeveloper.stripepayment.model.SubscriptionDto;
import com.talenteddeveloper.stripepayment.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class StripeSubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/{subscription_id}")
    public String getById(@PathVariable("subscription_id") final String subscriptionId) throws StripeException {
        return subscriptionService.getById(subscriptionId);
    }

    @PostMapping()
    public String save(@RequestBody SubscriptionDto subscriptionDto) throws StripeException {
        System.out.println("Reached");
        return subscriptionService.save(subscriptionDto);
    }

}
