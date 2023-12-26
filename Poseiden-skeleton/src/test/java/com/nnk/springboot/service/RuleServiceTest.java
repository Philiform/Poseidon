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

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.proxies.RuleProxy;

// TODO: Auto-generated Javadoc
/**
 * The Class RuleServiceTest.
 */
@ExtendWith(MockitoExtension.class)
public class RuleServiceTest {

	/** The service. */
	@InjectMocks
	private RuleService service;

	/** The rule connection proxy. */
	@Mock
	private RuleProxy proxy;

	/** The list rule. */
	List<Rule> listRule = new ArrayList<>();
	
	/** The rule 1. */
	private Rule rule1;
	
	/** The rule 2. */
	private Rule rule2;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		rule1 = new Rule();
		rule1.setId(1);
		rule1.setName("Name 1");
		rule1.setDescription("Description 1");
		rule1.setJson("Json 1");
		rule1.setTemplate("Template 1");
		rule1.setSqlStr("SqlStr 1");
		rule1.setSqlPart("SqlPart 1");

		rule2 = new Rule();

		listRule.add(rule1);
		listRule.add(rule2);

	}

	/**
	 * Test when get rule list then return list rule not empty.
	 */
	@Test
	void test_WhenGetRuleList_ThenReturnListRuleNotEmpty() {
		given(proxy.findAll()).willReturn(listRule);

		List<Rule> response = service.getRuleList();

		verify(proxy, times(1)).findAll();
		assertThat(response).isEqualTo(listRule);
	}

	/**
	 * Test given good new rule when save rule then return saved rule.
	 */
	@Test
	void test_GivenGoodNewRule_WhenSaveRule_ThenReturnSavedRule() {
		given(proxy.save(any())).willReturn(rule1);

		Optional<Rule> response = service.saveRule(rule1);

		verify(proxy, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(rule1));
	}

	/**
	 * Test given good rule id when get rule for update then return rule.
	 */
	@Test
	void test_GivenGoodRuleId_WhenGetRuleForUpdate_ThenReturnRule() {
		given(proxy.findById(any(Integer.class))).willReturn(rule1);

		Rule response = service.getRuleForUpdate(1);

		verify(proxy, times(1)).findById(any(Integer.class));
		assertThat(response).isEqualTo(rule1);
	}

	/**
	 * Test given bad rule id when get rule for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadRuleId_WhenGetRuleForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getRuleForUpdate(10));
		verify(proxy, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update rule when get rule for update then return updated rule.
	 */
	@Test
	void test_GivenGoodUpdateRule_WhenGetRuleForUpdate_ThenReturnUpdatedRule() {
		given(proxy.findById(any())).willReturn(rule1);

		Rule response = service.getRuleForUpdate(1);

		verify(proxy, times(1)).findById(any());
		assertThat(response).isEqualTo(rule1);
	}

	/**
	 * Test given bad update rule when get rule for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUpdateRule_WhenGetRuleForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getRuleForUpdate(10));
		verify(proxy, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update rule when update rule then return updated rule.
	 */
	@Test
	void test_GivenGoodUpdateRule_WhenUpdateRule_ThenReturnUpdatedRule() {
		given(proxy.save(any())).willReturn(rule1);

		Optional<Rule> response = service.updateRule(1, rule1);

		verify(proxy, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(rule1));
	}

	/**
	 * Test given good rule id when delere rule then delete rule.
	 */
	@Test
	void test_GivenGoodRuleId_WhenDelereRule_ThenDeleteRule() {
		service.deleteRule(1);

		verify(proxy, times(1)).deleteById(any(Integer.class));
	}

}
