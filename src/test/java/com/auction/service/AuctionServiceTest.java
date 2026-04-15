package com.auction.service;

import com.auction.model.AuctionItem;
import com.auction.model.Bid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuctionServiceTest {

    private AuctionService auctionService;
    private AuctionItem auctionItem;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        auctionService = new AuctionService();
        now = LocalDateTime.now();
        auctionItem = new AuctionItem(
                "A100",
                "Smart Phone",
                BigDecimal.valueOf(100),
                now.minusMinutes(30),
                now.plusMinutes(30)
        );
        auctionService.addItem(auctionItem);
    }

    @Test
    void shouldAcceptValidBid() {
        Bid bid = auctionService.placeBid("A100", "Rakesh", BigDecimal.valueOf(150), now);

        assertEquals("Rakesh", bid.getBidderName());
        assertEquals(BigDecimal.valueOf(150), bid.getAmount());
        assertEquals("Rakesh", auctionService.getHighestBidder("A100"));
    }

    @Test
    void shouldRejectEmptyBidderName() {
        BidValidationException exception = assertThrows(BidValidationException.class,
                () -> auctionService.placeBid("A100", "   ", BigDecimal.valueOf(150), now));

        assertEquals("Bidder name cannot be empty", exception.getMessage());
    }

    @Test
    void shouldRejectZeroOrNegativeBidAmount() {
        BidValidationException exception = assertThrows(BidValidationException.class,
                () -> auctionService.placeBid("A100", "Rakesh", BigDecimal.ZERO, now));

        assertEquals("Bid amount must be greater than zero", exception.getMessage());
    }

    @Test
    void shouldRejectBidBeforeAuctionStarts() {
        LocalDateTime beforeStart = now.minusHours(2);

        BidValidationException exception = assertThrows(BidValidationException.class,
                () -> auctionService.placeBid("A100", "Rakesh", BigDecimal.valueOf(150), beforeStart));

        assertEquals("Auction has not started yet", exception.getMessage());
    }

    @Test
    void shouldRejectBidAfterAuctionEnds() {
        LocalDateTime afterEnd = now.plusHours(2);

        BidValidationException exception = assertThrows(BidValidationException.class,
                () -> auctionService.placeBid("A100", "Rakesh", BigDecimal.valueOf(150), afterEnd));

        assertEquals("Auction has already ended", exception.getMessage());
    }

    @Test
    void shouldRejectBidLowerThanCurrentHighestBidPlusOne() {
        auctionService.placeBid("A100", "Rakesh", BigDecimal.valueOf(150), now);

        BidValidationException exception = assertThrows(BidValidationException.class,
                () -> auctionService.placeBid("A100", "Anu", BigDecimal.valueOf(150), now.plusMinutes(1)));

        assertEquals("Bid must be at least 151", exception.getMessage());
    }

    @Test
    void shouldReturnHighestBidAndBidder() {
        auctionService.placeBid("A100", "Rakesh", BigDecimal.valueOf(150), now);
        auctionService.placeBid("A100", "Anu", BigDecimal.valueOf(175), now.plusMinutes(1));

        assertEquals("Anu", auctionService.getHighestBidder("A100"));
        assertTrue(auctionService.getHighestBid("A100").isPresent());
        assertEquals(BigDecimal.valueOf(175), auctionService.getHighestBid("A100").get().getAmount());
    }
}
