package com.demo.stripepayment.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.stripe.param.SubscriptionCreateParams;
import com.stripe.param.SubscriptionUpdateParams;
import com.demo.stripepayment.model.SubscriptionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    @Value("${stripe.apikey}")
    String stripeKey;

    public String getById(String subscriptionId) throws StripeException {
        Stripe.apiKey = stripeKey;
        Subscription subscription = Subscription.retrieve(subscriptionId);
        return subscription.toJson();
    }

    public String save(SubscriptionDto subscriptionDto) throws StripeException {
        Stripe.apiKey = stripeKey;
        SubscriptionCreateParams params =
                SubscriptionCreateParams
                        .builder()
                        .setCustomer("cus_NggxtqjPa5DNBh")
                        .addItem(
                                SubscriptionCreateParams.Item.builder().setPrice("price_1MvK4KSGuDLfrIng0OgO3ge5").build()
                        )
                        .setTrialPeriodDays(30L)
                        .build();

        Subscription subscription = Subscription.create(params);

        System.out.println("Subscription ::"+subscription);
        return subscription.toJson();
    }

    public String cancel(String subscriptionId) throws StripeException {
        Stripe.apiKey = stripeKey;
        Subscription resource = Subscription.retrieve(subscriptionId);
        SubscriptionUpdateParams params =
                SubscriptionUpdateParams
                        .builder()
                        .setTrialEnd(SubscriptionUpdateParams.TrialEnd.NOW)
                        .build();

        Subscription subscription = resource.update(params);
        return subscription.toJson();
    }
}
