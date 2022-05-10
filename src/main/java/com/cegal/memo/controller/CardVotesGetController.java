package com.cegal.memo.controller;

import com.cegal.memo.db.entity.Votes;
import com.cegal.memo.db.repo.VotesRepo;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class CardVotesGetController {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    private static VotesRepo repo = new VotesRepo();
    @FunctionName("getCardVotes")
    public HttpResponseMessage getVotes(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        List<Votes> votes = repo.read();
        Map<String, VoteResponse> voteCount = new HashMap<>();
        for (Votes v : votes) {
            if (voteCount.containsKey(v.getCardId())) {
                VoteResponse vote = voteCount.get(v.getCardId());
                vote.setCount(vote.getCount()+1);
            } else {
                voteCount.put(v.getCardId(), VoteResponse.builder()
                .cardId(v.getCardId())
                .count(1)
                .build());
            }
        }
        
        //Todo get from database
        return request.createResponseBuilder(HttpStatus.OK).body(voteCount.values()).build();

    }
}
