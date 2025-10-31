package com.example.api.evo.models.payment;

public class FriendHelpRequestFactory {
    public FriendHelpRequest.Parameters defaultParameters(String friendPhoneNumber, String amount) {
        return FriendHelpRequest.Parameters.builder()
                .amount(amount)
                .phoneNum(friendPhoneNumber)
                .build();
    }

    public FriendHelpRequest.Params defaultParams(
            FriendHelpRequest.Parameters parameters, String userPhoneNumber, Long serviceId) {
        return FriendHelpRequest.Params.builder()
                .serviceId(serviceId)
                .phoneNumber(userPhoneNumber)
                .parameters(parameters)
                .build();
    }
}
