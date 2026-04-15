package com.auction.service;

import com.auction.model.AuctionItem;
import com.auction.model.Bid;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class AuctionService {

    private final Map<String, AuctionItem> items = new LinkedHashMap<>();

    public void addItem(AuctionItem item) {
        items.put(item.getItemId(), item);
    }

    public Bid placeBid(String itemId, String bidderName, BigDecimal amount, LocalDateTime bidTime) {
        AuctionItem item = items.get(itemId);
        if (item == null) {
            throw new BidValidationException("Auction item not found");
        }

        validateBid(item, bidderName, amount, bidTime);

        Bid bid = new Bid(bidderName.trim(), amount, bidTime);
        item.addBid(bid);
        return bid;
    }

    public Optional<Bid> getHighestBid(String itemId) {
        AuctionItem item = items.get(itemId);
        if (item == null) {
            return Optional.empty();
        }
        return item.getHighestBid();
    }

    public String getHighestBidder(String itemId) {
        return getHighestBid(itemId)
                .map(Bid::getBidderName)
                .orElse("No bids placed");
    }

    public void validateBid(AuctionItem item, String bidderName, BigDecimal amount, LocalDateTime bidTime) {
        if (bidderName == null || bidderName.trim().isEmpty()) {
            throw new BidValidationException("Bidder name cannot be empty");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BidValidationException("Bid amount must be greater than zero");
        }

        if (bidTime == null) {
            throw new BidValidationException("Bid time cannot be null");
        }

        if (bidTime.isBefore(item.getStartTime())) {
            throw new BidValidationException("Auction has not started yet");
        }

        if (bidTime.isAfter(item.getEndTime())) {
            throw new BidValidationException("Auction has already ended");
        }

        BigDecimal currentMinimum = item.getHighestBid()
                .map(b -> b.getAmount().add(BigDecimal.ONE))
                .orElse(item.getBasePrice());

        if (amount.compareTo(currentMinimum) < 0) {
            throw new BidValidationException("Bid must be at least " + currentMinimum);
        }
    }
}
