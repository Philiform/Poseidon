package com.nnk.springboot.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nnk.springboot.api.domain.Bid;

// TODO: Auto-generated Javadoc
/**
 * The Class BidTest.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BidTest {

	/** The repository. */
	@Autowired
	private BidRepository repository;

	/** The bid 1. */
	private Bid bid1;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		bid1 = new Bid();
		bid1.setId(0);
		bid1.setAccount("Account A1");
		bid1.setType("Type T1");
		bid1.setBidQuantity(11.1);
	}

	/**
	 * Test when find all then return bid list.
	 */
	@Test
	void test_WhenFindAll_ThenReturnBidList() {
		List<Bid> response = repository.findAll();

		assertThat(response.size()).isNotNull();
	}

	/**
	 * Test given good new bid when save bid then return saved bid.
	 */
	@Test
	void test_GivenGoodNewBid_WhenSaveBid_ThenReturnSavedBid() {
		Bid response = repository.save(bid1);

		// bidList size = 2
		bid1.setId(3);

		assertThat(response).isEqualTo(bid1);
	}

	/**
	 * Test given modified bid when update bid then return updated bid.
	 */
	@Test
	void test_GivenModifiedBid_WhenUpdateBid_ThenReturnUpdatedBid() {
		Bid newBid = repository.save(bid1);

		newBid.setAccount("Account modified");

		Bid response = repository.save(newBid);

		assertThat(response.getAccount()).isEqualTo("Account modified");
	}

	/**
	 * Test given bid id when delete bid then delete bid.
	 */
	@Test
	void test_GivenBidId_WhenDeleteBid_ThenDeleteBid() {
		Integer id = bid1.getId();
		repository.delete(bid1);
		Optional<Bid> response = repository.findById(id);

		assertThat(response).isEmpty();
	}

}
