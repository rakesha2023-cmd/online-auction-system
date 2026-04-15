package com.auction;

import com.auction.model.AuctionItem;
import com.auction.service.AuctionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AuctionApplication {
    public static void main(String[] args) {
        AuctionService auctionService = new AuctionService();

        LocalDateTime now = LocalDateTime.now();
        AuctionItem laptop = new AuctionItem(
                "A101",
                "Gaming Laptop",
                BigDecimal.valueOf(500),
                now.minusHours(1),
                now.plusHours(2)
        );

        auctionService.addItem(laptop);
        auctionService.placeBid("A101", "Ravi", BigDecimal.valueOf(550), now);
        auctionService.placeBid("A101", "Priya", BigDecimal.valueOf(600), now.plusMinutes(5));

        System.out.println("Highest bidder: " + auctionService.getHighestBidder("A101"));
        System.out.println("Highest bid: " + auctionService.getHighestBid("A101").map(b -> b.getAmount().toString()).orElse("No bids"));
    }
}
