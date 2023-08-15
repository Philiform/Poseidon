package com.nnk.springboot.service;

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

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;
import com.nnk.springboot.service.DTO.BidDTO;
import com.nnk.springboot.utilities.Utilities;

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

	/** The utilities. */
	@Mock
	private Utilities utilities;

	/** The list bid. */
	List<Bid> listBid = new ArrayList<>();

	/** The list bid DTO. */
	List<BidDTO> listBidDTO = new ArrayList<>();

	/** The bid 1. */
	private Bid bid1;

	/** The bid 2. */
	private Bid bid2;

	/** The bid DTO 1. */
	private BidDTO bidDTO1;

	/** The bid DTO 2. */
	private BidDTO bidDTO2;

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

		bidDTO1 = new BidDTO();
		bidDTO1.setId(1);
		bidDTO1.setAccount("Account A1");
		bidDTO1.setType("Type T1");
		bidDTO1.setBidQuantity(11.1);

		bidDTO2 = new BidDTO();

		listBidDTO.add(bidDTO1);
		listBidDTO.add(bidDTO2);
	}

	/**
	 * Test when get bid DTO list then return list bid DTO not empty.
	 */
	@Test
	void test_WhenGetBidDTOList_ThenReturnListBidDTONotEmpty() {
		given(repository.findAll()).willReturn(listBid);
		given(utilities.convertBidToDto(any()))
			.willReturn(bidDTO1)
			.willReturn(bidDTO2);

		List<BidDTO> response = service.getBidDTOList();

		verify(repository, times(1)).findAll();
		verify(utilities, times(2)).convertBidToDto(any());
		assertThat(response).isEqualTo(listBidDTO);
	}

	/**
	 * Test given good new bid DTO when save bid DTO then return saved bid.
	 */
	void test_GivenGoodNewBidDTO_WhenSaveBidDTO_ThenReturnSavedBid() {
		given(utilities.convertDtoToBid(any())).willReturn(bid1);
		given(repository.save(any())).willReturn(bid1);

		Optional<Bid> response = service.saveBidDTO(bidDTO1);

		verify(utilities, times(1)).convertDtoToBid(any());
		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(bid1));
	}

	/**
	 * Test given good bid DTO id when get bid DTO for update then return bid DTO.
	 */
	@Test
	void test_GivenGoodBidDTOId_WhenGetBidDTOForUpdate_ThenReturnBidDTO() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(bid1));
		given(utilities.convertBidToDto(any())).willReturn(bidDTO1);

		BidDTO response = service.getBidDTOForUpdate(1);

		verify(repository, times(1)).findById(any(Integer.class));
		verify(utilities, times(1)).convertBidToDto(any());
		assertThat(response).isEqualTo(bidDTO1);
	}

	/**
	 * Test given bad bid DTO id when get bid DTO for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadBidDTOId_WhenGetBidDTOForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getBidDTOForUpdate(10));
		verify(repository, times(1)).findById(any(Integer.class));
		verify(utilities, times(0)).convertBidToDto(any());
	}

	/**
	 * Test given good update bid DTO when get bid DTO for update then return updated bid DTO.
	 */
	@Test
	void test_GivenGoodUpdateBidDTO_WhenGetBidDTOForUpdate_ThenReturnUpdatedBidDTO() {
		given(repository.findById(any())).willReturn(Optional.of(bid1));
		given(utilities.convertBidToDto(any())).willReturn(bidDTO1);

		BidDTO response = service.getBidDTOForUpdate(1);

		verify(repository, times(1)).findById(any());
		verify(utilities, times(1)).convertBidToDto(any());
		assertThat(response).isEqualTo(bidDTO1);
	}

	/**
	 * Test given bad update bid DTO when get bid DTO for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUpdateBidDTO_WhenGetBidDTOForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getBidDTOForUpdate(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update bid DTO when update bid DTO then return updated bid DTO.
	 */
	@Test
	void test_GivenGoodUpdateBidDTO_WhenUpdateBidDTO_ThenReturnUpdatedBidDTO() {
		given(utilities.convertDtoToBid(any())).willReturn(bid1);
		given(repository.save(any())).willReturn(bid1);

		Optional<Bid> response = service.updateBidDTO(1, bidDTO1);

		verify(utilities, times(1)).convertDtoToBid(any());
		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(bid1));
	}

	/**
	 * Test given good bid DTO id when delere bid DTO then delete bid DTO.
	 */
	@Test
	void test_GivenGoodBidDTOId_WhenDelereBidDTO_ThenDeleteBidDTO() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(bid1));

		service.deleteBid(1);

		verify(repository, times(1)).findById(any(Integer.class));
		verify(repository, times(1)).delete(any(Bid.class));
	}

	/**
	 * Test given bad bid DTO id when delere bid DTO then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadBidDTOId_WhenDelereBidDTO_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteBid(10));
		verify(repository, times(1)).findById(any(Integer.class));
		verify(repository, times(0)).delete(any(Bid.class));
	}

}
