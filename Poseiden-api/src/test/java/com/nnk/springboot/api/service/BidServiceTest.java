package com.nnk.springboot.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.api.domain.Bid;
import com.nnk.springboot.api.repositories.BidRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class BidServiceTest.
 */
@ExtendWith(MockitoExtension.class)
public class BidServiceTest {

	/** The service. */
	@InjectMocks
	private BidService service;

	/** The bid connection repository. */
	@Mock
	private BidRepository repository;

	/** The list bid. */
	List<Bid> listBid = new ArrayList<>();

	/** The bid 1. */
	private Bid bid1;

	/** The bid 2. */
	private Bid bid2;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		bid1 = new Bid();
		bid1.setId(1);
		bid1.setAccount("Account A1");
		bid1.setType("Type T1");
		bid1.setBidQuantity(11.1);

		bid2 = new Bid();

		listBid.add(bid1);
		listBid.add(bid2);
	}

	/**
	 * Test when get bid list then return list bid not empty.
	 */
	@Test
	void test_WhenGetBidList_ThenReturnListBidNotEmpty() {
		given(repository.findAll()).willReturn(listBid);

		List<Bid> response = service.getBidList();

		verify(repository, times(1)).findAll();
		assertThat(response).isEqualTo(listBid);
	}

	/**
	 * Test given good new bid when save bid then return saved bid.
	 */
	void test_GivenGoodNewBid_WhenSaveBid_ThenReturnSavedBid() {
		given(repository.save(any())).willReturn(bid1);

		Optional<Bid> response = service.saveBid(bid1);

		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(bid1));
	}

	/**
	 * Test given good bid id when get bid for update then return bid .
	 */
	@Test
	void test_GivenGoodBidId_WhenGetBidForUpdate_ThenReturnBid() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(bid1));

		Bid response = service.getBidById(1);

		verify(repository, times(1)).findById(any(Integer.class));
		assertThat(response).isEqualTo(bid1);
	}

	/**
	 * Test given bad bid id when get bid for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadBidId_WhenGetBidForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getBidById(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update bid when get bid for update then return updated bid .
	 */
	@Test
	void test_GivenGoodUpdateBid_WhenGetBidForUpdate_ThenReturnUpdatedBid() {
		given(repository.findById(any())).willReturn(Optional.of(bid1));

		Bid response = service.getBidById(1);

		verify(repository, times(1)).findById(any());
		assertThat(response).isEqualTo(bid1);
	}

	/**
	 * Test given bad update bid when get bid for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUpdateBid_WhenGetBidForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getBidById(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update bid when update bid then return updated bid .
	 */
	@Test
	void test_GivenGoodUpdateBid_WhenUpdateBid_ThenReturnUpdatedBid() {
		given(repository.save(any())).willReturn(bid1);

		Optional<Bid> response = service.updateBid(1, bid1);

		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(bid1));
	}

	/**
	 * Test given good bid id when delere bid then delete bid .
	 */
	@Test
	void test_GivenGoodBidId_WhenDelereBid_ThenDeleteBid() {
		service.deleteBid(1);

		verify(repository, times(1)).deleteById(any());
	}

}
