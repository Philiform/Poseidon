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
//import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.api.domain.Trade;

// TODO: Auto-generated Javadoc
/**
 * The Class TradeTest.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TradeTest {

	/** The repository. */
	@Autowired
	private TradeRepository repository;

	/** The trade 1. */
	private Trade trade1;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		trade1 = new Trade();
		trade1.setId(0);
		trade1.setAccount("Account A1");
		trade1.setType("Type T1");
		trade1.setBuyQuantity(11.1);
	}

	/**
	 * Test when find all then return trade list.
	 */
	@Test
	void test_WhenFindAll_ThenReturnTradeList() {
		List<Trade> response = repository.findAll();

		assertThat(response.size()).isNotNull();
	}

	/**
	 * Test given good new trade when save trade then return saved trade.
	 */
	@Test
	void test_GivenGoodNewTrade_WhenSaveTrade_ThenReturnSavedTrade() {
		Trade response = repository.save(trade1);

		//tradeList size = 2
		trade1.setId(3);

		assertThat(response).isEqualTo(trade1);
	}

	/**
	 * Test given modified trade when update trade then return updated trade.
	 */
	@Test
	void test_GivenModifiedTrade_WhenUpdateTrade_ThenReturnUpdatedTrade() {
		Trade newTrade = repository.save(trade1);

		newTrade.setAccount("Account modified");

		Trade response = repository.save(newTrade);

		assertThat(response.getAccount()).isEqualTo("Account modified");
	}

	/**
	 * Test given trade id when delete trade then delete trade.
	 */
	@Test
	void test_GivenTradeId_WhenDeleteTrade_ThenDeleteTrade() {
		Integer id = trade1.getId();
		repository.delete(trade1);
		Optional<Trade> response = repository.findById(id);

		assertThat(response).isEmpty();
	}

}
